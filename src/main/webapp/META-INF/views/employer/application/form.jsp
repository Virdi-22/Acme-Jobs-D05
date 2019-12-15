<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h4><acme:message  code="employer.application.form.label.reference"/> <acme:print value="${jobReference}"/></h4><br>
<h4><acme:message code="employer.application.form.label.jobInfo"/> <acme:print value="${jobInfo}"/></h4><br>
<acme:form readonly="true">
	<acme:form-textbox readonly="true" code="employer.application.form.label.reference" path="reference"/>
	<acme:form-textbox readonly="true" code="employer.application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="employer.application.form.label.status" path="status"/>
	<acme:form-textbox readonly="true" code="employer.application.form.label.statement" path="statement"/>
	<acme:form-textbox readonly="true" code="employer.application.form.label.skills" path="skills"/>
	<acme:form-textarea readonly="true" code="employer.application.form.label.qualifications" path="qualifications"/>
	<acme:form-return code="employer.application.form.button.return"/>
	
	<acme:form-submit test="${command == 'show' }"
		code= "employer.application.form.button.accept"
		action= "/employer/application/update-accept"/>
		
	<acme:form-submit test="${command == 'show' }"
		code= "employer.application.form.button.reject"
		action= "/employer/application/update-reject"/>
</acme:form>
	
	
