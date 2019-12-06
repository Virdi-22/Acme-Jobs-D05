<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.companyRecord.form.label.companyName" path="companyName"/>
	<acme:form-textbox code="anonymous.companyRecord.form.label.sector" path="sector"/>
	<acme:form-textbox code="anonymous.companyRecord.form.label.ceoName" path="ceoName"/>
	<acme:form-textarea code="anonymous.companyRecord.form.label.activitiesDescription" path="activitiesDescription"/>
	<acme:form-url code="anonymous.companyRecord.form.label.webSite" path="webSite"/>
	<acme:form-textbox code="anonymous.companyRecord.form.label.contactPhone" path="contactPhone"/>
	<acme:form-textbox code="anonymous.companyRecord.form.label.contactEmail" path="contactEmail"/>
	<acme:form-textbox code="anonymous.companyRecord.form.label.stars" path="stars"/>
	<acme:form-textbox code="anonymous.companyRecord.form.label.incorporated" path="isIncorporated"/>
	
	<acme:form-return code="anonymous.companyRecord.form.button.return"/>
</acme:form>