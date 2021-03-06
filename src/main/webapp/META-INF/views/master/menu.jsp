<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.manu.anonymous.company-record.list" action="/anonymous/company-record/list"/>
			<acme:menu-suboption code="master.manu.anonymous.company-record.list-top" action="/anonymous/company-record/list-top"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.investor-record.list" action="/anonymous/investor-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.investor-record.list-top" action="/anonymous/investor-record/list-top"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.announcement.list"  action="/anonymous/announcement/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.announcement.list"  action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.authenticated.company-record.list" action="/authenticated/company-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.investor-record.list" action="/authenticated/investor-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.request.list"  action="/authenticated/_-request/list"/>
			<acme:menu-suboption code="master.menu.authenticated.offer.list" action="/authenticated/offer/list"/>
			<acme:menu-suboption code="master.menu.authenticated.challenge.list" action="/authenticated/challenge/list"/>
			<acme:menu-suboption code="master.menu.authenticated.job.list" action="/authenticated/job/list"/>
			<acme:menu-suboption code="master.menu.authenticated.message-thread.list" action="/authenticated/message-thread/list-mine"/>
			<acme:menu-suboption code="master.menu.authenticated.message-thread.create" action="/authenticated/message-thread/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.challenge.list" action="/administrator/challenge/list"/>
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.announcement.list"  action="/administrator/announcement/list"/>
      		<acme:menu-suboption code="master.menu.administrator.announcement.create"  action="/administrator/announcement/create"/>
      		<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.nonComercialBanner.list"  action="/administrator/non-comercial-banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.nonComercialBanner.create"  action="/administrator/non-comercial-banner/create"/>
			 <acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.investor-record.list"  action="/administrator/investor-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.investor-record.create"  action="/administrator/investor-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.company-record.list"  action="/administrator/company-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.company-record.create"  action="/administrator/company-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.credit-card.list"  action="/administrator/credit-card/list"/>
			<acme:menu-suboption code="master.menu.administrator.credit-card.create"  action="/administrator/credit-card/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.configuration.show" action="/administrator/configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.provider.request.list" action="/provider/_-request/list"/>
			<acme:menu-suboption code="master.menu.provider.request.create" action="/provider/_-request/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.consumer.offer.list" action="/consumer/offer/list"/>
			<acme:menu-suboption code="master.menu.consumer.offer.create" action="/consumer/offer/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.job.list-mine" action="/employer/job/list-mine"/>
			<acme:menu-suboption code="master.menu.employer.job.create" action="/employer/job/create"/>
			<acme:menu-suboption code="master.menu.employer.application.list" action="/employer/application/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.application.list" action="/worker/application/list"/>
			<acme:menu-suboption code="master.menu.worker.job.list" action="/worker/job/list"/>		
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.job.list-auditored" action="/auditor/job/list-auditored"/>
			<acme:menu-suboption code="master.menu.auditor.job.list-not-auditored" action="/auditor/job/list-not-auditored"/>
			<acme:menu-suboption code="master.menu.auditor.auditRecord.list-mine" action="/auditor/audit-record/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.comercialBanner.list-mine" action="/sponsor/comercial-banner/list-mine"/>
			<acme:menu-suboption code="master.menu.sponsor.nonNomercialBanner.list-mine" action="/sponsor/non-comercial-banner/list-mine"/>
			<acme:menu-suboption code="master.menu.sponsor.nonNomercialBanner.create" action="/sponsor/non-comercial-banner/create"/>
			<acme:menu-suboption code="master.menu.sponsor.credit-card.list" action="/sponsor/credit-card/list"/>
			<acme:menu-suboption code="master.menu.sponsor.credit-card.create" action="/sponsor/credit-card/create"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create" access="!hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update" access="hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create" access="!hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update" access="hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-auditor" action="/authenticated/auditor-request/create" access="!hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create" access="!hasRole('Sponsor')"/>
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update" access="hasRole('Sponsor')"/>
      <acme:menu-suboption code="master.menu.user-account.auditor-requests" action="/administrator/auditor-request/list" access="hasRole('Administrator')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>


