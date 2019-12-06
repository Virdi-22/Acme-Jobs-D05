<!--  -->
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.investor-record.list.label.name" path="name" width="50%"/>
	<acme:list-column code="authenticated.investor-record.list.label.stars" path="stars" width="50%"/>
</acme:list>

<!-- 	<acme:list-column code="authenticated.investor-record.list.label.sector" path="sector" width="40%"/> -->