<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<h4><acme:message code="auditor.auditRecord.contextData1"/> <acme:print value="${jobReference}"/></h4><br>
	<acme:form-hidden path="jobReference"/>
	<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="auditor.auditRecord.form.label.status" path="status" readonly="true"/>
		<acme:form-moment code="auditor.auditRecord.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="auditor.auditRecord.form.label.body" path="body"/>
	<jstl:if test="${command == 'create'}">
		<acme:form-submit code="auditor.auditRecord.form.button.create" action="/auditor/audit-record/create?jobId=${jobId}"/>
	</jstl:if>
	
	<jstl:if test="${status == false || command == 'update'}">
		<acme:form-submit code="auditor.auditRecord.form.button.update" action="/auditor/audit-record/update"/>
	</jstl:if>
	
	<jstl:if test="${status == false || command == 'publish'}">
		<acme:form-submit code="auditor.auditRecord.form.button.publish" action="/auditor/audit-record/publish"/>
	</jstl:if>
	
	<jstl:if test="${command == 'show' || command == 'delete'}">
		<acme:form-submit code="auditor.auditRecord.form.button.delete" action="/auditor/audit-record/delete"/>
	</jstl:if>
	
	<acme:form-return code="auditor.auditRecord.form.button.return"/>
</acme:form>