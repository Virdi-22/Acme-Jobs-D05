<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<h4><acme:message code="worker.application.form.contextData1"/> <acme:print value="${jobReference}"/></h4><br>
	<acme:form-textbox code="worker.application.form.label.reference" path="reference"/>
	<acme:form-textbox code="worker.application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="worker.application.form.label.status" path="status"/>
	<acme:form-textbox code="worker.application.form.label.statement" path="statement"/>
	<acme:form-textbox code="worker.application.form.label.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications"/>
	<jstl:if test="${status == 'Rejected'}">
		<acme:form-textbox code="worker.application.form.label.reasonRejected" path="reasonRejected"/>
	</jstl:if>
	<acme:form-return code="worker.application.form.button.return"/>
</acme:form>