<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="auditor.duty.list.label.title" path="title" width="20%"/>
	<acme:list-column code="auditor.duty.list.label.description" path="description" width="80%"/>
</acme:list>

<acme:form>
	<acme:form-return code="auditor.duty.list.button.return"/>
</acme:form>