<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<h4><acme:message code="authenticated.messageThread.form.contextData1"/> <acme:print value="${usersInvolved}"/></h4><br>
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.messageThread.form.label.moment" path="moment"/>
	
	<acme:form-submit method="get" code="authenticated.messageThread.form.button.linkMessages" action="/authenticated/message/list-by-message?messageThreadId=${messageThreadId}"/>
	
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
</acme:form>