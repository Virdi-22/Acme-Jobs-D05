<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="false">
	<acme:form-textbox code="employer.job.form.label.reference" path="reference"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="employer.job.form.label.description" path="description"/>
	
	<jstl:if test="${finalMode == 'false'}">
		<acme:form-submit test="${command == 'show'}"
			code="employer.job.form.button.update"
			action="/employer/job/update"/>
	</jstl:if>
	<acme:form-submit test="${command == 'show'}"
		code="employer.job.form.button.delete"
		action="/employer/job/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="employer.job.form.button.create"
		action="/employer/job/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="employer.job.form.button.update"
		action="/employer/job/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="employer.job.form.button.delete"
		action="/employer/job/delete"/>
		
	<jstl:if test="${finalMode == 'false'}">
		<acme:form-submit test="${command != 'create'}" method="get" code="employer.job.form.button.create-duty" action="/employer/duty/create?jobId=${jobId}"/>
	</jstl:if>
	
	<acme:form-submit test="${command != 'create'}" method="get" code="employer.job.form.button.linkDuties" action="/employer/duty/list-by-job?jobId=${jobId}"/>
	
	<acme:form-return code="employer.job.form.button.return"/>
</acme:form>