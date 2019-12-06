<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.spam-configuration.form.label.spam-words-listing" path="spamWordsListing"/>
	<acme:form-textbox code="administrator.spam-configuration.form.label.spam-threshold" path="spamThreshold"/>
	
	<acme:form-submit code="administrator.configuration.form.button.update" action="/administrator/configuration/update"/>
	<acme:form-return code="administrator.spam-configuration.form.button.return"/>
</acme:form>