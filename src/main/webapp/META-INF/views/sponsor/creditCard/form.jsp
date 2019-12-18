<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.creditCard.form.label.creditCardNumber" path="creditCardNumber"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.brand" path="brand"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.holder" path="holder"/>
	<acme:form-textbox code="sponsor.creditCard.form.label.expirationDate" placeholder="MM/YYYY" path="expirationDate"/>
	<acme:form-submit test="${command == 'create'}" code="sponsor.creditCard.form.button.create" action="/sponsor/credit-card/create"/>
	<acme:form-submit test="${command == 'show' }" code="sponsor.credtCard.form.button.delete" action="/sponsor/credit-card/delete"/>
	<acme:form-submit test="${command != 'create'}" 
		method="get" code="sponsor.creditCard.form.button.create-banner" action="/sponsor/comercial-banner/create?creditCardId=${id}"/>
	<acme:form-return code="sponsor.creditCard.form.button.return"/>
</acme:form>
