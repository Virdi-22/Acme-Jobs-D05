<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.comercialBanner.form.label.slogan" path="slogan"/>
	<h5><acme:message  code="administrator.comercialBanner.form.label.referenceCreditCardNumber"/> <acme:print value="${creditCardNumber}"/></h5><br>
	<h5><acme:message  code="administrator.comercialBanner.form.label.referenceExpirationDate"/> <acme:print value="${expirationDate}"/></h5><br>
	<acme:form-url code="administrator.comercialBanner.form.label.target" path="target"/>

	<acme:form-submit test="${command == 'show' }"
		code= "administrator.comercialBanner.form.button.update"
		action= "/administrator/comercial-banner/update"/>
	<acme:form-submit test="${command == 'show' }"
		code= "administrator.comercialBanner.form.button.delete"
		action= "/administrator/comercial-banner/delete"/>
	<acme:form-submit test="${command == 'create' }"
		code= "administrator.comercialBanner.form.button.create"
		action= "/administrator/comercial-banner/create?creditCardId=${creditCardId}"/>
	<acme:form-submit test="${command == 'update' }"
		code= "administrator.comercialBanner.form.button.update"
		action= "/administrator/comercial-banner/update"/>
	<acme:form-submit test="${command == 'delete' }"
		code= "administrator.comercialBanner.form.button.delete"
		action= "/administrator/comercial-banner/delete"/>
		
	<acme:form-return code="administrator.comercialBanner.form.button.return"/>
</acme:form>
