<%--
  Created by IntelliJ IDEA.
  User: whydk
  Date: 2015/10/18
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/tags/taglib.jsp" %>
<html>
<head>
    <title>system index</title>
</head>
<body>
<p>Hi <shiro:guest>Guest</shiro:guest><shiro:user><shiro:principal/></shiro:user>!

</body>
</html>
