<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.companyRecord.form.label.companyName" path="companyName"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.ceoName" path="ceoName"/>
	<acme:form-textarea code="administrator.companyRecord.form.label.activitiesDescription" path="activitiesDescription"/>
	<acme:form-url code="administrator.companyRecord.form.label.webSite" path="webSite"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.contactPhone" path="contactPhone"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.contactEmail" path="contactEmail"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.stars" path="stars"/>
	<acme:form-textbox code="administrator.companyRecord.form.label.incorporated" path="isIncorporated"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.companyRecord.form.button.update" action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.companyRecord.form.button.delete" action="/administrator/company-record/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.companyRecord.form.button.create" action="/administrator/company-record/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.companyRecord.form.button.update" action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.companyRecord.form.button.delete" action="/administrator/company-record/delete"/>
	
	
	<acme:form-return code="administrator.companyRecord.form.button.return"/>
</acme:form>
