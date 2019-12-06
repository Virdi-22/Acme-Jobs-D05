<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox placeholder="OXXXX-99999" code="consumer.offer.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="consumer.offer.form.label.title" path="title"/>
	<acme:form-textarea code="consumer.offer.form.label.description" path="description"/>
	<acme:form-money placeholder="EUR 100" code="consumer.offer.form.label.minMoney" path="minMoney"/>
	<acme:form-money placeholder="EUR 200" code="consumer.offer.form.label.maxMoney" path="maxMoney"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="consumer.offer.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="consumer.offer.form.label.deadline" path="deadline"/>
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="consumer.offer.form.label.checkbox" path="check"/>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}"
		code="consumer.offer.form.button.create"
		action="/consumer/offer/create"/>
	<acme:form-return code="consumer.offer.form.button.return"/>
</acme:form>