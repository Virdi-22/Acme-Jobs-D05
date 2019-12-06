<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form readonly="true">

<h2>
	<acme:message code="administrator.dashboard.form.title.application-statuses"/>
	<br>
</h2>

	<acme:form-panel code="administrator.dashboard.form.label.announcements"/>
	<acme:form-integer code="administrator.dashboard.form.label.total" path="totalNumberOfAnnouncements"/>
	<br>
		
	<acme:form-panel code="administrator.dashboard.form.label.company-records"/>
	<acme:form-integer code="administrator.dashboard.form.label.total" path="totalNumberOfCompanyRecords"/>
	<br>
	
	<acme:form-panel code="administrator.dashboard.form.label.investor-records"/>
	<acme:form-integer code="administrator.dashboard.form.label.total" path="totalNumberOfInvestorRecords"/>
	<br>
	
	<acme:form-panel code="administrator.dashboard.form.label.reward-active-requests"/>
	<acme:form-money code="administrator.dashboard.form.label.minimum" path="minimumRequestRewards"/>
	<acme:form-money code="administrator.dashboard.form.label.maximun" path="maximumRequestRewards"/>
	<acme:form-money code="administrator.dashboard.form.label.average" path="averageRequestRewards"/>
	<acme:form-money code="administrator.dashboard.form.label.standard-deviation" path="standardDeviationRequestRewards"/>
	<br>
	
	<acme:form-panel code="administrator.dashboard.form.label.reward-active-offers"/>
	<acme:form-money code="administrator.dashboard.form.label.minimum" path="minimumOfferRewards"/>
	<acme:form-money code="administrator.dashboard.form.label.maximun" path="maximumOfferRewards"/>
	<acme:form-money code="administrator.dashboard.form.label.average" path="averageofferRewards"/>
	<acme:form-money code="administrator.dashboard.form.label.standard-deviation" path="standardDeviationOfferRewards"/>
	<br>
	
	
	<acme:form-panel code="administrator.dashboard.form.label.jobs"/>
	<acme:form-money code="administrator.dashboard.form.label.average-by-employer" path="averageNumberOfJobsPerEmployer"/>
	<br>
	
	<acme:form-panel code="administrator.dashboard.form.label.applications"/>
	<acme:form-integer code="administrator.dashboard.form.label.average-by-employer" path="averageNumberOfApplicationsPerEmployer"/>
	<acme:form-money code="administrator.dashboard.form.label.average-by-worker" path="averageNumberOfApplicationsPerWorker"/>
	<br>


<div>
	<h4>
		<acme:message code="administrator.dashboard.form.company-record.status"/>
	</h4>
	<canvas id="canvas1"></canvas>
	<br>
	<h4>
		<acme:message code="administrator.dashboard.form.investor-record.status"/>
	</h4>
	<canvas id="canvas2"></canvas>
	<br>
	<h4>
		<acme:message code="administrator.dashboard.form.job.status"/>
	</h4>
	<canvas id="canvas3"></canvas>
	<br>
	<h4>
		<acme:message code="administrator.dashboard.form.application.status"/>
	</h4>
	<canvas id="canvas4"></canvas>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		
		var dashboardLabelsCompany = [];
		var dashboardLabelsInvestor = [];
		var dashboardDataCompany = [];
		var dashboardDataInvestor = [];
	
		//
		var dashboardLabelsJob = [];
		var dashboardLabelsApplication = [];
		var dashboardDataJob = [];
		var dashboardDataApplication = [];
		//
	
		<jstl:forEach items="${companySectors}" var = "sector">
			dashboardLabelsCompany.push("${sector}");
		</jstl:forEach>
		<jstl:forEach items="${totalCompaniesBySector}" var = "total">
			dashboardDataCompany.push("${total}");
		</jstl:forEach>
		<jstl:forEach items="${investorSectors}" var = "sector">
			dashboardLabelsInvestor.push("${sector}");
		</jstl:forEach>
		<jstl:forEach items="${totalInvestorsBySector}" var = "total">
			dashboardDataInvestor.push("${total}");
		</jstl:forEach>
		
		//
		<jstl:forEach items="${jobStatus}" var = "status1">
			dashboardLabelsJob.push("${status1}");
		</jstl:forEach>
		<jstl:forEach items="${averageJobsByStatus}" var = "average">
			dashboardDataJob.push("${average}");
		</jstl:forEach>
		<jstl:forEach items="${applicationStatus}" var = "status2">
			dashboardLabelsApplication.push("${status2}");
		</jstl:forEach>
		<jstl:forEach items="${averageApplicationsByStatus}" var = "average">
			dashboardDataApplication.push("${average}");
		</jstl:forEach>
		//
		
		var data1 = {
				labels : dashboardLabelsCompany,
				datasets : [
					{
						data : dashboardDataCompany
					}
				]
		};
		var options1 = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 5.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
		
		var data2 = {
				labels : dashboardLabelsInvestor,
				datasets : [
					{
						data : dashboardDataInvestor
					}
				]
		};
		var options2 = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 5.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
		
		//
		var data3 = {
				labels : dashboardLabelsJob,
				datasets : [
					{
						data : dashboardDataJob
					}
				]
		};
		var options3 = {
			legend : {
				display : true
			}
		};
		
		var data4 = {
				labels : dashboardLabelsApplication,
				datasets : [
					{
						data : dashboardDataApplication
					}
				]
		};
		var options4 = {
			legend : {
				display : true
			}
		};
		//
		
		
		var canvas1, context1;
		
		canvas1 = document.getElementById("canvas1");
		context1 = canvas1.getContext("2d");
		new Chart(context1, {
			type : "bar",
			data : data1,
			options : options1
		});
		
		var canvas2, context2;
		
		canvas2 = document.getElementById("canvas2");
		context2 = canvas2.getContext("2d");
		new Chart(context2, {
			type : "bar",
			data : data2,
			options : options2
		});
		
		var canvas3, context3;
		
		canvas3 = document.getElementById("canvas3");
		context3 = canvas3.getContext("2d");
		new Chart(context3, {
			type : "pie",
			data : data3,
			options : options3
		});
		
		var canvas4, context4;
		
		canvas4 = document.getElementById("canvas4");
		context4 = canvas4.getContext("2d");
		new Chart(context4, {
			type : "pie",
			data : data4,
			options : options4
		});
	});
	</script>
</acme:form>