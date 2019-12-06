<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.companyRecord.list.label.companyName" path="companyName" width="50%"/>
	<acme:list-column code="administrator.companyRecord.list.label.stars" path="stars" width="50%"/>
</acme:list>

<!-- <acme:list-column code="administrator.companyRecord.list.label.webSite" path="webSite" width="30%"/>
	<acme:list-column code="administrator.companyRecord.list.label.contactPhone" path="contactPhone" width="20%"/>
	<acme:list-column code="administrator.companyRecord.list.label.contactEmail" path="contactEmail" width="20%"/> -->