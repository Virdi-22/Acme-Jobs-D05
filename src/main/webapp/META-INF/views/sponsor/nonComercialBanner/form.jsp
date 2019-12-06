<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.nonComercialBanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.nonComercialBanner.form.label.jingle" path="jingle"/>



	<acme:form-url code="sponsor.nonComercialBanner.form.label.target" path="target"/>
		
	<acme:form-return code="sponsor.nonComercialBanner.form.button.return"/>
</acme:form>