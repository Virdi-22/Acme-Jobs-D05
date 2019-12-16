<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create' }">
	<h4><acme:message code="authenticated.messageThread.form.contextData1"/> <acme:print value="${usersInvolved}"/></h4><br>
	<acme:form-moment code="authenticated.messageThread.form.label.moment" path="moment"/>
	</jstl:if>
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title"/>
	
	<acme:form-submit test="${command == 'show'}" method="get" code="authenticated.messageThread.form.button.linkMessages" action="/authenticated/message/list-by-message?messageThreadId=${id}"/>
	<acme:form-submit test="${command == 'show'}" method="get" code="authenticated.messageThread.form.button.linkParticipants" action="/authenticated/participant/list-by-message-thread?messageThreadId=${id}"/>
	<acme:form-submit test="${command == 'create'}" code= "authenticated.messageThread.form.button.create" action= "/authenticated/message-thread/create"/>
	
	<jstl:if test="${(command == 'show' && isMine == true)}">
	<acme:form-submit method="get" code= "authenticated.messageThread.form.button.createParticipant" action= "/authenticated/participant/create?messageThreadId=${id}"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'show'}" method="get" code= "authenticated.messageThread.form.button.createMessage" action= "/authenticated/message/create?messageThreadId=${id}"/>
	
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
</acme:form>