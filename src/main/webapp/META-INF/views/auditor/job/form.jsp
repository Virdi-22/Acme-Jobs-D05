<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="auditor.job.form.label.title" path="title"/>
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="auditor.job.form.label.salary" path="salary"/>
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="auditor.job.form.label.description" path="description"/>
	
	<acme:form-submit method="get" code="auditor.job.form.button.linkDuties" action="/auditor/duty/list-by-job?jobId=${id}"/>
	
	<acme:form-submit method="get" code="auditor.job.form.button.linkToAuditRecords" action="/authenticated/audit-record/list-by-job?jobId=${id}"/>
	
	<acme:form-submit method="get" code="auditor.job.form.button.createAuditRecord" action="/auditor/audit-record/create?jobId=${id}"/>
	
	<acme:form-return code="auditor.job.form.button.return"/>
</acme:form>