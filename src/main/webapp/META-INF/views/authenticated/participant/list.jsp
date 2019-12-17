<%----%>
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.participant.list.label.messageThreadName" path="messageThreadName" width="40%"/>
	<acme:list-column code="authenticated.participant.list.label.authenticatedName" path="authenticatedName" width="40%"/>
	<acme:list-column code="authenticated.participant.list.label.isOwner" path="isOwner" width="20%"/>
</acme:list>

<acme:form>
	<acme:form-return code="authenticated.participant.list.button.return"/>
</acme:form>