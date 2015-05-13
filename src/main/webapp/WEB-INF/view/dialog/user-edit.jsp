<%@page pageEncoding="UTF-8" session="true"%>


		<div id="userEditDlg" class="modal" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content" style="">
		
		<div class="modal-header"> 
			<button class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="headerEdit">Добавить пользователя</h4>
		</div>
			
			<div class="modal-body">
				<form class="form-horizontal">
			<input type="hidden" id="userIdDlg">
			<input type="hidden" id="organizationSelectIdDlg" value="${cookie.organizationId.value}">

		<div class="form-group">
 			<label for="userTypeDlg" class="col-sm-3 control-label">Тип пользователя</label>
				<div class="col-sm-8">
					<select id ="userTypeDlg" class="changeUserType selectpicker" name = "userTypeSelect">
                        <c:if test="${sulin:hasRole(pageContext.request, 'SuperAdmin')}" >
                            <option value="SuperAdmin">Администратор</option>
                            <option value="RegistrarAccounts">Регистратор аккаунтов</option>
                            <option value="Onlooker">Наблюдатель</option>
                            <option value="ResponsibleForPayment">Ответственный за оплату</option>
                            <option value="SchoolAdmin">Школьный администратор</option>
                            <option value="OperatorMarks">Наборщик оценок</option>
                            <option value="Teacher">Учитель</option>
                            <option value="Parent">Родитель</option>
                            <option value="Scholar">Ученик</option>
                            <option value="Other">Другой</option>
                        </c:if>

                        <c:if test="${sulin:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                            <option value="RegistrarAccounts">Регистратор аккаунтов</option>
                            <option value="Onlooker">Наблюдатель</option>
                            <option value="ResponsibleForPayment">Ответственный за оплату</option>
                            <option value="SchoolAdmin">Школьный администратор</option>
                            <option value="OperatorMarks">Наборщик оценок</option>
                            <option value="Teacher">Учитель</option>
                            <option value="Parent">Родитель</option>
                            <option value="Scholar">Ученик</option>
                            <option value="Other">Другой</option>
                        </c:if>

                        <c:if test="${sulin:hasRole(pageContext.request, 'SchoolAdmin')}" >
                            <option value="ResponsibleForPayment">Ответственный за оплату</option>
                            <option value="SchoolAdmin">Школьный администратор</option>
                            <option value="OperatorMarks">Наборщик оценок</option>
                            <option value="Teacher">Учитель</option>
                            <option value="Parent">Родитель</option>
                            <option value="Scholar">Ученик</option>
                            <option value="Other">Другой</option>
                        </c:if>
					</select>
				</div>
		</div>
			
		
		<div class="form-group">
			<label for="userNameDlg" class="col-sm-3 control-label">ФИО</label>
			<div class="col-sm-5">
		 		<input type="text" id="userNameDlg" class="form-control">
		 	</div>
		</div>
		 
		 
		 <div class="form-group">
			<label for="userEmailDlg" class="col-sm-3 control-label">Email</label>
			<div class="col-sm-5">
		 		<input type="email" id="userEmailDlg" class="form-control">

                <label>
                    <input type="checkbox" id="isFake" value="fake">Недействительный Email
                </label>
		 	</div>
		</div>
		
		<div class="form-group">
			<label for="userPasswordDlg" class="col-sm-3 control-label">Пароль</label>
			<div class="col-sm-5">
		 		<input type="password" id="userPasswordDlg" class="form-control" data-toggle="password">
		 	</div>
		</div>
		
		<div class="form-group">
			<label for="userPhoneDlg" class="col-sm-3 control-label">Телефон</label>
			<div class="col-sm-5">
		 		<input type="tel" id="userPhoneDlg" class="form-control">
		 	</div>
		</div>
		

        <div class="form-group" id = "Organization" hidden = "true">
            <label for="organizationNameDlg" class="col-sm-3 control-label">Организация</label>
            <div class="col-sm-8" id = "or">
                <select id="organizationNameDlg" class="changeOrganization selectpicker">
                    <c:if test="${! empty requestScope.listOrg}">
                        <c:if test="${sulin:hasRole(pageContext.request, 'SuperAdmin')
                                        or sulin:hasRole(pageContext.request, 'RegistrarAccounts')}" >
                            <c:forEach var="item" items="${requestScope.listOrg}">
                                <c:choose>
                                    <c:when test="${cookie.organizationId.value == item.key}">
                                        <option selected value="${item.key}">${item.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${item.key}">${item.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                        <c:if test="${!(sulin:hasRole(pageContext.request, 'SuperAdmin')
                                        or sulin:hasRole(pageContext.request, 'RegistrarAccounts'))}" >
                            <%--<c:choose>--%>
                                <c:if test="${cookie.organizationId.value != null && cookie.organizationId.value != 'disabled'}">
                                    <c:forEach var="item" items="${requestScope.listOrg}">
                                        <c:if test="${cookie.organizationId.value == item.key}">
                                            <option selected value="${item.key}">${item.value}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <%--<c:otherwise>--%>
                                    <%--&lt;%&ndash;<option value="disabled">--Выберите организацию--</option>&ndash;%&gt;--%>
                                    <%--<c:forEach var="item" items="${requestScope.listOrg}">--%>
                                        <%--<option value="${item.key}">${item.value}</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        </c:if>
                    </c:if>
                </select>
                <button type="button" class="btn btn-default" id = "addOrg" ><span class="glyphicon glyphicon-plus"></span></button>
            </div>
        </div>
        
	<div class="form-group" id = "Group" hidden = "true">
            <label for="groupNameDlg" class="col-sm-3 control-label">Класс</label>
            <div class="col-sm-8">
                <select id="groupNameDlg" class="selectpicker" data-size="5" data-live-search="true">
                    <option value="disabled">--Выберите организацию--</option>
                </select>
            </div>
        </div>
        
		 <div class="form-group">
		 	<label for="userDescriptionDlg" class="col-sm-3 control-label">Примечание</label>
		 	<div class="col-sm-5">
		 		<input type="text" id="userDescriptionDlg" class="form-control">
		 	</div>
		 </div>
        
		 <div class="form-group">
		 	<label for="userStatusDlg" class="col-sm-3 control-label">Статус</label>
				<div class="col-sm-8">
					<select id ="userStatusDlg" class="selectpicker">
			    		<option value="Active" selected> Активная</option>
						<option value="Inactive"> Неактивная</option>
					</select>
				</div>
		</div>

	 <div class="modal-footer">
         <button id="userSaveDlg" data-dismiss="modal" class="btn btn-primary">Сохранить</button>
         <button id="userCancelDlg" data-dismiss="modal" class="btn btn-default" aria-hidden="true">Отмена</button>
	 </div>
				</form>
			</div>
		</div>
	</div>
</div>
