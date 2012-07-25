<%--
  Created by IntelliJ IDEA.
  User: sam-sun
  Date: 7/25/12
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
<body>
    <g:if test="${error == 'True'}">
        验证错误
    </g:if>
    <g:form action="login" controller="person">
        <g:textField name="username" rows="" cols=""/>
        <g:passwordField name="password"/>
        <input type="submit" value="提交"/>
    </g:form>
</body>
</html>