<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.nonComercialBanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.nonComercialBanner.form.label.jingle" path="jingle"/>



	<acme:form-url code="sponsor.nonComercialBanner.form.label.target" path="target"/>
	
	<acme:form-submit test="${command == 'show' }"
		code= "sponsor.nonComercialBanner.form.button.update"
		action= "/sponsor/non-comercial-banner/update"/>
	<acme:form-submit test="${command == 'show' }"
		code= "sponsor.nonComercialBanner.form.button.delete"
		action= "/sponsor/non-comercial-banner/delete"/>
	<acme:form-submit test="${command == 'create' }"
		code= "sponsor.nonComercialBanner.form.button.create"
		action= "/sponsor/non-comercial-banner/create"/>
	<acme:form-submit test="${command == 'update' }"
		code= "sponsor.nonComercialBanner.form.button.update"
		action= "/sponsor/non-comercial-banner/update"/>
	<acme:form-submit test="${command == 'delete' }"
		code= "sponsor.nonComercialBanner.form.button.delete"
		action= "/sponsor/non-comercial-banner/delete"/>
		
	<acme:form-return code="sponsor.nonComercialBanner.form.button.return"/>
</acme:form>