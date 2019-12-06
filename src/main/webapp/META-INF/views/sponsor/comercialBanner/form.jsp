<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.comercialBanner.form.label.slogan" path="slogan"/>
	<acme:form-textbox code="sponsor.comercialBanner.form.label.creditCardNumber" path="creditCardNumber"/>
	<acme:form-textbox code="sponsor.comercialBanner.form.label.holder" path="holder"/>
	<acme:form-textbox code="sponsor.comercialBanner.form.label.brand" path="brand"/>
	<acme:form-textbox placeholder="MM/YYYY" code="sponsor.comercialBanner.form.label.expirationDate" path="expirationDate"/>
	<acme:form-url code="sponsor.comercialBanner.form.label.target" path="target"/>
		
	<acme:form-return code="sponsor.comercialBanner.form.button.return"/>
</acme:form>
