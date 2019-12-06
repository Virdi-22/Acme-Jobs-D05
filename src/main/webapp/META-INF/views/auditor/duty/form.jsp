<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="auditor.duty.form.label.title" path="title"/>
	<acme:form-textarea code="auditor.duty.form.label.description" path="description"/>
	<acme:form-textbox code="auditor.duty.form.label.percentage" path="percentage"/>
	
	<acme:form-return code="auditor.duty.form.button.return"/>
</acme:form>