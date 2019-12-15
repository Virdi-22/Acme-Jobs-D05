<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.creditCard.form.label.creditCardNumber" path="creditCardNumber"/>
	<acme:form-textbox code="administrator.creditCard.form.label.brand" path="brand"/>
	<acme:form-textbox code="administrator.creditCard.form.label.expirationDate" placeholder="MM/YYYY" path="expirationDate"/>

	<acme:form-submit test="${command == 'show' }"
		code= "administrator.creditCard.form.button.update"
		action= "/administrator/credit-card/update"/>
	<acme:form-submit test="${command == 'show' }"
		code= "administrator.creditCard.form.button.delete"
		action= "/administrator/credit-card/delete"/>
	<acme:form-submit test="${command == 'create' }"
		code= "administrator.creditCard.form.button.create"
		action= "/administrator/credit-card/create"/>
	<acme:form-submit test="${command == 'update' }"
		code= "administrator.creditCard.form.button.update"
		action= "/administrator/credit-card/update"/>
	<acme:form-submit test="${command == 'delete' }"
		code= "administrator.creditCard.form.button.delete"
		action= "/administrator/credit-card/delete"/>
	<acme:form-submit test="${command != 'create'}" method="get" code="administrator.creditCard.form.button.linkBanner" action="/administrator/comercial-banner/list-by-credit?creditCardId=${id}"/>
	<acme:form-submit test="${command != 'create'}" method="get" code="administrator.creditCard.form.button.create-banner" action="/administrator/comercial-banner/create?creditCardId=${id}"/>
	<acme:form-return code="administrator.creditCard.form.button.return"/>
</acme:form>
