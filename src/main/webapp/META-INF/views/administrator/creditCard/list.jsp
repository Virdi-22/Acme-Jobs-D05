<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.creditCard.list.label.creditCardNumber" path="creditCardNumber" width="50%"/>
	<acme:list-column code="administrator.creditCard.list.label.expirationDate" path="expirationDate" width="50%"/>
</acme:list>