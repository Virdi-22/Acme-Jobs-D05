<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.comercialBanner.list.label.slogan" path="slogan" width="20%"/>
	<acme:list-column code="administrator.comercialBanner.list.label.target" path="target" width="60%"/>
</acme:list>