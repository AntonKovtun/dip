<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8" session="true"%>

<html>
<head>
    <%@include file="../include/header.jsp"%>
    <title>Вход - dip</title>

    <script src="${applicationScope.resourceServerUrl}js/page/login.js"></script>
</head>

<body style="">
<%@include file="../include/menu-top.jsp"%>

<div class="f-container min_H_400">

    <form id="formLoginDlg" class="form-horizontal" name="formLoginDlg" method="post" >
        <div class="form-group marB_10">
            <label class=" col-sm-2 control-label"></label>
            <div class="col-sm-3">
                <h3>Вход в систему</h3>
            </div>
        </div>

        <div class="form-group marB_10">
            <label for="inpLoginEmail" class=" col-sm-2 control-label">E-mail</label>
            <div class="col-sm-3">
                <input id="inpLoginEmail" value="dip@dip.com" class="form-control width_150" type="text" autocomplete="on" placeholder="Email address"/>
            </div>
        </div>

        <div class="clear"></div>

        <div class="form-group marB_5">
            <label for="inpLoginPassword" class=" col-sm-2 control-label">Пароль</label>
            <div class="col-sm-3">
                <input id="inpLoginPassword" value="123" class="form-control width_150" type="password" placeholder="Password"/>
<span id = 'errLog' class='alert alert-danger' role ='alert' style="display: none;">Неверные E-mail или пароль</span>
            </div>
        </div>

        <div class="clear"></div>

        <div class="form-group marB_10">
            <label for="inpLoginKeep" class=" col-sm-2 control-label">Запомнить меня</label>
            <div class="col-sm-8">
                <input id="inpLoginKeep" type="checkbox"/>
            </div>
        </div>

        <div class="clear"></div>

        <div class="form-group marB_10">
            <label for="btnInput" class="marR_10 col-sm-2 control-label"></label>
            <!--<button type="submit" id="btnCompanyEditSaveDlg" class="btn marR_10 btn-primary marT_5 ">Вход</button> -->
            <button type="button" id="btnInput" class="btn marR_10 btn-primary marT_5 ">Вход</button>
            <br>



        </div>
    </form>
</div> <!-- /container -->
<%@include file="../include/menu-bottom.jsp"%>

</body>
</html>
