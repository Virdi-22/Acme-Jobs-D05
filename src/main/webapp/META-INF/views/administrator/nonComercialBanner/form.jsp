<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.nonComercialBanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.nonComercialBanner.form.label.jingle" path="jingle"/>



	<acme:form-url code="administrator.nonComercialBanner.form.label.target" path="target"/>

	<acme:form-submit test="${command == 'show' }"
		code= "administrator.nonComercialBanner.form.button.update"
		action= "/administrator/non-comercial-banner/update"/>
	<acme:form-submit test="${command == 'show' }"
		code= "administrator.nonComercialBanner.form.button.delete"
		action= "/administrator/non-comercial-banner/delete"/>
	<acme:form-submit test="${command == 'create' }"
		code= "administrator.nonComercialBanner.form.button.create"
		action= "/administrator/non-comercial-banner/create"/>
	<acme:form-submit test="${command == 'update' }"
		code= "administrator.nonComercialBanner.form.button.update"
		action= "/administrator/non-comercial-banner/update"/>
	<acme:form-submit test="${command == 'delete' }"
		code= "administrator.nonComercialBanner.form.button.delete"
		action= "/administrator/non-comercial-banner/delete"/>
		
	<acme:form-return code="administrator.nonComercialBanner.form.button.return"/>
</acme:form>