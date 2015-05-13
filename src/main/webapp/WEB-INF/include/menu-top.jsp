<%@page pageEncoding="UTF-8" session="true"%>

<div class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a href="" class="navbar-brand"><fmt:message key="dip.ui.title.short"/></a>
        </div>
        <div class="navbar-collapse collapse">
            <c:if test="${dip:isAuthenticated(pageContext.request)}">
                <p class="navbar-text navbar-right"><a href="user-profile?id=${requestScope.userId}" class="navbar-link">${requestScope.userName} <span class="glyphicon glyphicon glyphicon-user"></span></a></p>
            </c:if>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <c:if test="${dip:isAuthenticated(pageContext.request)}">
                    <c:if test="${dip:hasRole(pageContext.request, 'SuperAdmin')
                                or dip:hasRole(pageContext.request, 'SchoolAdmin')}">
                        <li><a href="users"><fmt:message key="dip.ui.users.title"/></a></li>
                    </c:if>
                </c:if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${!dip:isAuthenticated(pageContext.request)}" >
                    <li>
                        <a id="aLoginLink" class="navbar-link" href="login">Вход</a>
                    </li>
                </c:if>
                <c:if test="${dip:isAuthenticated(pageContext.request)}" >
                    <li>
                        <a class="navbar-link" href="logout">Выход</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<div class="clear"></div>