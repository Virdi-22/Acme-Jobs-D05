<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<h4><acme:message code="authenticated.message.form.contextData1"/> <acme:print value="${userName}"/></h4>
	<h4><acme:message code="authenticated.message.form.contextData2"/> <acme:print value="${messageThread}"/></h4>
	<acme:form-textbox code="authenticated.message.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>
	<acme:form-textbox code="authenticated.message.form.label.tags" path="tags"/>
	<acme:form-moment code="authenticated.message.form.label.moment" path="moment"/>
	
	<acme:form-submit code= "authenticated.message.form.button.create" action= "/authenticated/message/create?messageThreadId=${messageThreadId}"/>
	<acme:form-return code="authenticated.message.form.button.return"/>
</acme:form>