var x = null;
var isFake = null;

$(document).ready(function() {

    $("#userPhoneDlg").mask("+79999999999");
//    $("#userPasswordDlg").password();

//	bindEditClick($(".userEdit"));
//	bindDeleteClick($(".userDelete"));
//    bindUserProfileClick($(".userProfile"));
//    bindDeleteOrgClick($(".rm"));

	$("#userSaveDlg").click(function(){
		var orgList = new Array();
		
		if($("#userTypeDlg").children("option:selected").attr("value") == "Scholar") {
			orgList.push($("#organizationNameDlg").children("option:selected").attr("value"));
		} else{
		$("#or").children("p").map(function(index, element){
	    	orgList.push($(element).attr("orgId"));
	    });
	};
//        if($("#isFake:checked")) isFake = true;
//        else isFake = false;
        if ($("#isFake").prop("checked")) isFake = true;
        else isFake = false;

		var wm = new UserWebModel(
				$("#userIdDlg").val(),
				$("#userNameDlg").val(),		
				$("#userEmailDlg").val(),
				$("#userPasswordDlg").val(),
				$("#userPhoneDlg").val(),
				orgList,
				$("#groupNameDlg").children("option:selected").attr("value"),
				$("#userDescriptionDlg").val(),
				$("#userTypeDlg").children("option:selected").attr("value"),
				$("#userStatusDlg").children("option:selected").attr("value"),
                isFake


		);

        $.postJSON("user", wm, function(data) {
		if (data && !hasError(data)) {
			if (x == 0){
                showNotification("Сохранение выполнено успешно", "success");
        	}
        	else if (x == 1){
                showNotification("Изменение сохранено", "success");
        	}
            viewTable();
        	$("#userEditDlg").modal("hide");
            return false;
            
        }
    });
});

    $("#add").click(function(){
        x = 0;
        fillUserEditDialog();
        $("#or").children("p").remove(); //reset organizaionList
        $("#organizationNameDlg option").prop("disabled", false); // activated all select options
        $('#organizationNameDlg').selectpicker('refresh');
//        $("#userTypeDlg /*option:selected").prop(userId, $("#selUserType").val());
        $("#userEditDlg").modal({
            backdrop : true
        });
	});
    
    $(".changeOrganization").change(function() {
        $.getJSON("groupSearch", {organizationIdList:$(".changeOrganization option:selected").val()}, function(data) {
            if (data && !hasError(data)) {
                $("#groupNameDlg").html(function(){
                    var str = "<option value=\"disabled\">--Выберите класс--</option>";
                    for(var i = 0; i<data.value.length; i++) {
                        str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                    }
                    return str;
                });
                $('#groupNameDlg').selectpicker('refresh');
                return false;
            }}
        )
    });

    $("#selUserType").change(function() {
        $("#selGroupPage").prop('selectedIndex',0);
        $("#selGroupPage").selectpicker('refresh');
        if ($("#selUserType option:selected").val() == "Scholar"
            || $("#selUserType option:selected").val() == "Parent"
            || $("#selUserType option:selected").val() == (!"disabled")){
            $("#selGroupPage").attr("disabled", false);
            $("#selGroupPage").selectpicker('refresh');
        }
        else
        {
            $("#selGroupPage").prop("disabled", true);
            $("#selGroupPage").selectpicker('refresh');
           viewTable();
        }
    });

//    $("#selOrganizationForMenu").change(function() {
//        $("#selUserType").html(function() {
//            str += "<option selected" + '--Выберите тип пользователя--' + "</option>";
//            str += "<option value=" + 'SuperAdmin' + ">" + 'Главный администратор' + "</option>";
//            str += "<option value=" + 'SchoolAdmin' + ">" + 'Школьный администратор' + "</option>";
//            str += "<option value=" + 'RegistrarAccounts' + ">" + 'Регистратор аккаунтов' + "</option>";
//            str += "<option value=" + 'Teacher' + ">" + 'Учитель' + "</option>";
//            str += "<option value=" + 'Parent' + ">" + 'Родитель' + "</option>";
//            str += "<option value=" + 'Scholar' + ">" + 'Ученик' + "</option>";
//            str += "<option value=" + 'Other' + ">" + 'Другой' + "</option>";
//            return str;
//        })
//    });

//    $("#selUserType").change(function() {
//
//        $.getJSON("groupSearch", {organizationIdList:$(".changeOrganization option:selected").val()}, function(data) {
//                if (data && !hasError(data)) {
//                    $("#groupNameDlg").html(function(){
//                        var str = "<option value=\"disabled\">--Выберите класс--</option>";
//                        for(var i = 0; i<data.value.length; i++) {
//                            str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
//                        }
//                        return str;
//                    });
//                    return false;
//                }}
//        )
//    });
    
    changeUserType();


     
    $("#addOrg").click(function(){          
        if ($("#organizationNameDlg option:selected").val() != "disabled" && $("#organizationNameDlg option:selected").prop('disabled') == 0){
            var theText = "";
            theText += "<p orgId = " + $("#organizationNameDlg option:selected").val() + ">" + $("#organizationNameDlg option:selected").text() +
            "<button class = 'btn btn-link rm'><span class='glyphicon glyphicon-remove'></span></button>" + "</p>";
    //        "<input type = 'button' class = 'btn btn-link rm' value = 'remove'/>" + "</p>";
            $("#or").append(theText).slideDown();
            $("#organizationNameDlg option:selected").prop('disabled',true);
            $("#organizationNameDlg").val("disabled");
            $('#organizationNameDlg').selectpicker('refresh');
            bindDeleteOrgClick( $('#or p .rm'));
        };
    });

    $(".userProfile").click(function() {
        window.location.href="user-profile?id=" + $(this).parents("tr").attr("trId");
    });

    $("#selGroupPage").change(function() {
        viewTable();
    });
});



function bindEditClick(elements){
	elements.on("click", function() {
		x = 1;
		fillUserEditDialog( $(this).parents("tr") ); 
	    	$("#or").children("p").remove(); // reset organizaionList
		    $("#organizationNameDlg option").prop("disabled", false); // activated all select options
            $('#organizationNameDlg').selectpicker('refresh');
		    /*
		     * add all organization to organizationList for next editing
		     * */
	    	if ($(this).parents("tr").children("td.userType").attr("userType") != "Scholar"){
	    		$(this).parents("tr").children("td.organizationName").children("p").map(function(index, element){
	    			var pStr = "";
	    				pStr += "<p orgId = " + $(element).attr("orgId") + ">" + $(element).text() + 
	    		        "<button class = 'btn btn-link rm'><span class='glyphicon glyphicon-remove'></span></button>" + "</p>";
	    				$("#organizationNameDlg option[value = " + $(element).attr("orgId")	 + "]").prop('disabled', true);
                        $('#organizationNameDlg').selectpicker('refresh');
	    		        $("#or").append(pStr).slideDown();
	    		        bindDeleteOrgClick( $('#or p .rm'));
	    	    });
	    	}
	       $("#userEditDlg").modal({
	          backdrop : true
	       });
	});
};

function bindDeleteOrgClick(elements){
	elements.on("click", function(){
		$("#organizationNameDlg option[value = " + $(this).parent("p").attr("orgId") + "]").prop('disabled', false);
		$(this).parent("p").remove();
//        showNotification("Удаление выполнено", "warning");
		$("#organizationNameDlg").val("disabled");
        $('#organizationNameDlg').selectpicker('refresh');
	});
}

function bindUserProfileClick(elements){
    elements.on("click", function() {
        window.location.href="user-profile?id=" + $(this).parents("tr").attr("trID");
    });
}


function bindDeleteClick(elements){
    elements.on("click", function() {
        var userId = $(this).parents("tr").attr("trId");
        $.deleteJSON("user", {id:userId}, function(data) {
            if (data && !hasError(data)){
                viewTable();
                showNotification("Удаление выполнено", "warning");
                return false;
            }
        });
    });
}

function changeUserType(){ 
	$("#userTypeDlg").change(function (){
    	if ($("#userTypeDlg").children("option:selected").attr("value") == "SuperAdmin"
            || $("#userTypeDlg").children("option:selected").attr("value") == "Parent"
            || $("#userTypeDlg").children("option:selected").attr("value") == "Onlooker"
            || $("#userTypeDlg").children("option:selected").attr("value") == "RegistrarAccounts"){
            $("#Organization").hide();
            $("#Group").hide();
            $("#or p").remove();
            setSelectedItemInSelectObj($("#organizationNameDlg"), $("#organizationSelectIdDlg").val());
            $("#organizationNameDlg option:disabled").attr('disabled', false);
            $('#organizationNameDlg').selectpicker('refresh');
    	} else $("#Organization").show();
    	
    	if($("#userTypeDlg").children("option:selected").attr("value") == "Scholar"){
            $("#Group").show();
            $("#addOrg").hide();
    	} else {
            $("#Group").hide();
            $("#addOrg").show();
    	}
    });
}

function chooseUserType(){
    if ($("#userTypeDlg").children("option:selected").attr("value") == "SuperAdmin"
        || $("#userTypeDlg").children("option:selected").attr("value") == "Parent"
        || $("#userTypeDlg").children("option:selected").attr("value") == "Onlooker"
        || $("#userTypeDlg").children("option:selected").attr("value") == "RegistrarAccounts"){
        $("#Organization").hide();
        $("#Group").hide();
    } else $("#Organization").show();

    if($("#userTypeDlg").children("option:selected").attr("value") == "Scholar"){
        $("#Group").show();
        $("#addOrg").hide();
    } else {
        $("#Group").hide();
        $("#addOrg").show();
    }
}

function UserWebModel(id, name, email, password, phone, /*organizationId,*/ organizationIdList, groupId, description, userType, status, isFake) {
this.id = id;
this.name = name;
this.email = email;
this.password = password;
this.phone = phone;
//this.organizationId = organizationId;
this.organizationIdList = organizationIdList;
this.groupId = groupId;
this.description = description;
this.userType = userType;
this.status = status;
this.isFake = isFake;
}

function fillUserEditDialog(tr) {
if (tr) {
    $("#headerEdit").text("Редактировать пользователя");
	$("#userIdDlg").val(tr.children("td.edit").attr("userId"));
    $("#userNameDlg").val(tr.children("td.name").text());
    $("#userDescriptionDlg").val(tr.children("td.description").text());
    $("#userEmailDlg").val(tr.children("td.email").text());

    $.getJSON("userPassword", {id:$("#userIdDlg").val()}, function(data) {
        if (data && !hasError(data)) {
            $("#userPasswordDlg").val(data.value);
            return false;
            }
        });
//            $("#userPasswordDlg").val(tr.children("td.password").text());
	$("#userPhoneDlg").val(tr.children("td.phone").text());
	setSelectedItemInSelectObj($("#userTypeDlg"), tr.children("td.userType").attr("userType"));
	if($("#userTypeDlg").children("option:selected").attr("value") == "Scholar"){
	setSelectedItemInSelectObj($("#organizationNameDlg"), tr.children("td.organizationName").children("p").attr("orgId"));
    
	$.getJSON("groupSearch", {organizationIdList:$(".changeOrganization option:selected").val()}, function(data) {
        if (data && !hasError(data)) {
            $("#groupNameDlg").html(function(){
                var str = "<option value=\"disabled\">--Выберите группу--</option>";
                for(var i = 0; i<data.value.length; i++) {
                    str += "<option value=" + data.value[i].id + ">" + data.value[i].name + "</option>";
                }
                return str;
            });
            setSelectedItemInSelectObj($("#groupNameDlg"), tr.children("td.groupName").children("p").attr("groupId"));
            return false;
        }
   });
}

    setSelectedItemInSelectObj($("#userStatusDlg"), tr.children("td.status").attr("status"));
    if(tr.children("td.email").attr("isFake") == "true"){
        $("#isFake").prop("checked", true)
    }
    else{
        $("#isFake").prop("checked", false)
    }
    
    chooseUserType();

}
else{
    $("#headerEdit").text("Добавить пользователя");
	$("#userIdDlg").val(-1); 
    $("#userNameDlg").val("");
    $("#userDescriptionDlg").val("");
    $("#userEmailDlg").val("");

    $.getJSON("userPassword", {id:$("#userIdDlg").val()}, function(data) {
        if (data && !hasError(data)) {
            $("#userPasswordDlg").val(data.value);
            return false;
        }
    });

	$("#userPhoneDlg").val("");
    setSelectedItemInSelectObj($("#organizationNameDlg"), $("#organizationSelectIdDlg").val());

//    if ($("#userType").val() == "Scholar") {
//        $("#organizationNameDlg").trigger("change");
//    }
//    if($("#organizationNameDlg").children("option[value='disabled']").val() == "disabled") {
//        setSelectedItemInSelectObj($("#organizationNameDlg"), "disabled");
//    } else {
//        $("#organizationNameDlg").trigger("change");
//    }
    $("#groupNameDlg").html("<option value=\"disabled\">--Выберите организацию--</option>");
    setSelectedItemInSelectObj($("#groupNameDlg"), $("#selGroupPage").val());
	setSelectedItemInSelectObj($("#userTypeDlg"), $("#selUserType").val());
    setSelectedItemInSelectObj($("#userStatusDlg"), "Active");
    $("#isFake").prop("checked", false);

    chooseUserType();

    if($("#userTypeDlg").children("option:selected").attr("value") == "Scholar"){
        $("#organizationNameDlg").trigger("change");
    }
}
}


function viewTable() {
    var groupId = $("#selGroupPage").children("option:selected").attr("value");
    var userType = $("#selUserType").children("option:selected").attr("value");

    $.getJSON("userSearch", {groupId:groupId, userType:userType}, function(data) {
            if (data && !hasError(data)) {
                $("#tblUsers").children("tbody").html(function() {
                    var strtable = "";
                    for(var i = 0; i<data.value.length; i++) {
                        strtable += "<tr trID = " + data.value[i].id + ">";
                        if (!($("#userType").val() == "Onlooker" || $("#userType").val() == "ResponsibleForPayment" || $("#userType").val() == "OperatorMarks")) {
                            strtable += "<td class = 'edit' userId =" + data.value[i].id + ">" +
                                "<div style='min-width: 160px'>" +
                                "<button type='button' class='btn btn-default userEdit' title='Редактировать'>" +
                                "<span class='glyphicon glyphicon-pencil'></span>" +
                                "</button>"
                                +
                                "&nbsp"
                                +
                                "<button type='button' class='btn btn-default userDelete' title='Удалить'>" +
                                "<span class='glyphicon glyphicon-trash'></span>" +
                                "</button>"
                                +
                                "&nbsp"
                                +
                                "<button type='button' class='btn btn-default userProfile' title='Перейти в профайл'>" +
                                "<span class='glyphicon glyphicon glyphicon-user'></span>";
                            if (data.value[i].userType == "Scholar") {
                                strtable += "&nbsp&nbsp" +
                                    "<span span class='badge' title='Количество родителей'>" + data.value[i].userList.length + "</span>";
                            }
                            if (data.value[i].userType == "Parent") {
                                strtable += "&nbsp&nbsp" +
                                    "<span span class='badge' title='Количество детей'>" + data.value[i].userList.length + "</span>";
                            }
                            strtable += "</button>" +
                                "</div>" +
                                "</td>";
                        }
                        strtable += "<td class = 'name'>" +  data.value[i].name + "</td>";
                        strtable += "<td isFake=" + data.value[i].isFake + " class = 'email'>" +  data.value[i].email + "</td>";
                        strtable += "<td class = 'phone'>" +  data.value[i].phone + "</td>";

                        /*
                         * draw organizationList
                         */
                        strtable += "<td class = 'organizationName' >";
                        for (var j = 0; j<data.value[i].organizationList.length; j++) {
                            strtable += "<p orgId=" + data.value[i].organizationList[j].id + ">" + data.value[i].organizationList[j].name + "</p>";
                        }
                        strtable += "</td>";

                        /*
                         * draw group
                         */
                        if(data.value[i].groupList != ""){
                            strtable += "<td class = 'groupName'>";
                            strtable += "<p groupId=" + data.value[i].groupList[0].id + ">" + data.value[i].groupList[0].name + "</p>";
                            strtable += "</td>";
                        } else {
                            strtable += "<td class = 'groupName'>";
                            strtable += "</td>";
                        }

                        strtable += "<td class = 'description'>" +  data.value[i].description + "</td>";
                        var usType = $("#selUserType option[value = '" + data.value[i].userType + "']");
                        strtable += "<td class = 'userType' userType = " + data.value[i].userType +">" + usType.text() + "</td>";
                        strtable += "<td class = 'status' status = " + data.value[i].status +">" + data.value[i].status + "</td>";
                        strtable += "</tr>";
                    }
                    return strtable;
                });
                bindEditClick( $('#tblUsers .userEdit') );
                bindDeleteClick( $('#tblUsers .userDelete') );
                bindUserProfileClick( $('#tblUsers .userProfile') );
                bindDeleteOrgClick($(".rm"));
                $('#tblUsers').dataTable();
                return false;
            }}
    )
}