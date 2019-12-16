<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="messageThreadId"/>
	
	<jstl:if test="${command != 'create' }">
	<h4><acme:message code="authenticated.participant.form.contextData1"/> <acme:print value="${messageThreadName}"/></h4><br>
	<h4><acme:message code="authenticated.participant.form.contextData2"/> <acme:print value="${usersInvolved}"/></h4><br>
	<acme:form-textbox code="authenticated.participant.form.label.is-owner" path="isOwner"/>
	</jstl:if>
	
	<jstl:if test="${(command == 'show' && isMine == true) || command == 'delete'}">
	<acme:form-submit code= "authenticated.participant.form.button.delete" action= "/authenticated/participant/delete"/>
	</jstl:if>
	
	<jstl:if test="${command == 'create' }">
		<acme:form-textbox code="authenticated.participant.form.label.user" path="userName"/>
	<acme:form-submit code= "authenticated.participant.form.button.create" action= "/authenticated/participant/create"/>
	</jstl:if>
	
	<acme:form-return code="authenticated.participant.form.button.return"/>
</acme:form>