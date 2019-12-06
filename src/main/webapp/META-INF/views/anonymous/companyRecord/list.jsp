<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.companyRecord.list.label.companyName" path="companyName" width="50%"/>
	<acme:list-column code="anonymous.companyRecord.list.label.stars" path="stars" width="20%"/>
</acme:list>