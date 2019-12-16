<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.comercialBanner.form.label.slogan" path="slogan"/>
	<h5><acme:message code="sponsor.comercialBanner.form.label.referenceCreditCardNumber: "/> <acme:print value="${creditCardNumber}"/></h5><br>
	<h5><acme:message code="sponsor.comercialBanner.form.label.referenceExpirationDate: "/> <acme:print value="${expirationDate}"/></h5><br>
	<acme:form-textbox code="sponsor.comercialBanner.form.label.holder" path="holder"/>
	<acme:form-textbox code="sponsor.comercialBanner.form.label.brand" path="brand"/>
	<acme:form-url code="sponsor.comercialBanner.form.label.target" path="target"/>
		
	<acme:form-return code="sponsor.comercialBanner.form.button.return"/>
</acme:form>
