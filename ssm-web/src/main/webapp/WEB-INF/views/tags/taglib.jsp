<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="ashiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>

<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="fnc" uri="/WEB-INF/tlds/fnc.tld" %>
<%--<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>--%>
<%--<%@ taglib prefix="act" tagdir="/WEB-INF/tags/act" %>--%>
<%--<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/cms" %>--%>

<%--<%--%>
    <%--//    String path = request.getContextPath();--%>
<%--//    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";--%>
<%--%>--%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxa" value="${ctx}${fns:getAdminPath()}"/>
<c:set var="ctxs" value="${ctx}/static"/>
<c:set var="ctxsa" value="${ctx}/static/adminlte"/>
