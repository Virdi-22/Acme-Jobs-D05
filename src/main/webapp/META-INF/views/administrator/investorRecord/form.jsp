<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.investor-record.form.label.name" path="name"/>
	<acme:form-textbox code="authenticated.investor-record.form.label.stars" path="stars"/>
	<acme:form-textbox code="authenticated.investor-record.form.label.sector" path="sector"/>
	<acme:form-textarea code="authenticated.investor-record.form.label.text" path="text"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.investorRecord.form.button.update" action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.investorRecord.form.button.delete" action="/administrator/investor-record/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.investorRecord.form.button.create" action="/administrator/investor-record/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.investorRecord.form.button.update" action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.investorRecord.form.button.delete" action="/administrator/investor-record/delete"/>
	<acme:form-return code="authenticated.investor-record.form.button.return"/>
</acme:form>