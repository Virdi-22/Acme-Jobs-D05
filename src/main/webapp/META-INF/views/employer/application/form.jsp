<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<h4><acme:message code="employer.application.form.label.reference"/> <acme:print value="${jobReference}"/></h4><br>
	<h4><acme:message code="employer.application.form.label.jobInfo"/> <acme:print value="${jobInfo}"/></h4><br>
	<acme:form-textbox readonly="true" code="employer.application.form.label.reference" path="reference"/>
	<acme:form-textbox readonly="true" code="employer.application.form.label.creationMoment" path="creationMoment"/>
	<jstl:if test="${status == 'Pending'}">
		<acme:form-select code="employer.application.form.label.status" path="status">
			<acme:form-option code="employer.application.form.label.statusPending" value="Pending" selected="true"/>
			<acme:form-option code="employer.application.form.label.statusAccepted" value="Accepted"/>
			<acme:form-option code="employer.application.form.label.statusRejected" value="Rejected"/>
		</acme:form-select>
	</jstl:if>
	<jstl:if test="${status == 'Accepted' || status == 'Rejected'}">
		<acme:form-textbox readonly="true" code="employer.application.form.label.status" path="status"/>
	</jstl:if>
	<acme:form-textbox readonly="true" code="employer.application.form.label.statement" path="statement"/>
	<acme:form-textbox readonly="true" code="employer.application.form.label.skills" path="skills"/>
	<acme:form-textarea readonly="true" code="employer.application.form.label.qualifications" path="qualifications"/>

	<jstl:if test="${status == 'Pending' || status == 'Rejected'}">
		<acme:form-textbox readonly="false" code="employer.application.form.label.reasonRejected" path="reasonRejected"/>
	</jstl:if>
	
	<jstl:if test="${status == 'Pending' || command == 'update'}">
		<acme:form-submit test="${command == 'show' || command == 'update'}" code="employer.application.form.button.update" action="/employer/application/update"/>
	</jstl:if>
	
	<acme:form-return code="employer.application.form.button.return"/>
	
</acme:form>
	
	
