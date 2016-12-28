<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<!--
        ===
        This comment should NOT be removed.

        Charisma v2.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
        ===
    -->
<meta charset="utf-8">
<title>Free HTML5 Bootstrap Admin Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">
<meta http-equiv="X-Frame-Options" content="SAMEORIGIN / DENY "> 
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<!-- The styles -->
<link id="bs-css"
	href="${ctx}/resources/ui/charisma/css/cerulean/bootstrap.min.css"
	rel="stylesheet">
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<link href='${ctx}/resources/ui/charisma/bower_components/datatables/media/css/jquery.dataTables.css' rel='stylesheet'>
<link href="${ctx}/resources/ui/charisma/css/charisma-app.css"
	rel="stylesheet">
<link
	href='${ctx}/resources/ui/charisma/bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link
	href='${ctx}/resources/ui/charisma/bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link
	href='${ctx}/resources/ui/charisma/bower_components/chosen/chosen.min.css'
	rel='stylesheet'>
<link
	href='${ctx}/resources/ui/charisma/bower_components/daterangepicker/daterangepicker.css'
	rel='stylesheet'  media='all'>
<link
	href='${ctx}/resources/ui/charisma/bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link
	href='${ctx}/resources/ui/charisma/bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='${ctx}/resources/ui/charisma/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link href='${ctx}/resources/ui/charisma/css/jquery.noty.css'
	rel='stylesheet'>
<link href='${ctx}/resources/ui/charisma/css/noty_theme_default.css'
	rel='stylesheet'>
<link href='${ctx}/resources/ui/charisma/css/elfinder.min.css'
	rel='stylesheet'>
<link href='${ctx}/resources/ui/charisma/css/elfinder.theme.css'
	rel='stylesheet'>
<link href='${ctx}/resources/ui/charisma/css/jquery.iphone.toggle.css'
	rel='stylesheet'>
<link href='${ctx}/resources/ui/charisma/css/uploadify.css'
	rel='stylesheet'>
<link href='${ctx}/resources/ui/charisma/css/animate.min.css'
	rel='stylesheet'>

<!-- jQuery -->
<script
	src="${ctx}/resources/ui/charisma/bower_components/jquery/jquery.min.js"></script>
	<!-- inputmask -->
<script
	src="${ctx}/resources/ui/charisma/bower_components/jquery/jquery.inputmask.bundle.js"></script>
<script
	src="${ctx}/resources/ui/charisma/bower_components/jquery/jquery.inputmask.bundle.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon"
	href="${ctx}/resources/ui/charisma/img/favicon.ico">
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
</head>

<body>
	<%@ include file="top.jsp"%>
	<div class="ch-container">
		<div class="row">
			<!-- menu -->
			<%@ include file="menu.jsp"%>

			<noscript>
				<div class="alert alert-block col-md-12">
					<h4 class="alert-heading">Warning!</h4>
					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>

			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->

				<!-- content ends -->
			</div>
			<!--/#content.col-md-0-->
		</div>
		<!--/fluid-row-->
		<hr>
		<!-- <div id="myModal" class="modal hide fade"></div>-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Settings</h3>
					</div>
					<div class="modal-body">
						<p>Here settings can be configured...</p>
					</div>
					<div class="modal-footer">
						<a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
						<a href="#" class="btn btn-primary" data-dismiss="modal">Save
							changes</a>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="messageButtonId" class="btn btn-primary noty"
			data-noty-options='{"text":"Success","layout":"bottom","type":"information","closeButton":"true"}' />

	</div>
	<!--/.fluid-container-->

	<!-- external javascript -->

	<script
		src="${ctx}/resources/ui/charisma/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- library for cookie management -->
	<script src="${ctx}/resources/ui/charisma/js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script
		src='${ctx}/resources/ui/charisma/bower_components/moment/min/moment.min.js'></script>
	<script
		src='${ctx}/resources/ui/charisma/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='${ctx}/resources/ui/charisma/js/jquery.dataTables.min.js'></script>
	<!-- select or dropdown enhancer -->
	<script
		src="${ctx}/resources/ui/charisma/bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script
		src="${ctx}/resources/ui/charisma/bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="${ctx}/resources/ui/charisma/js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script
		src="${ctx}/resources/ui/charisma/bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script
		src="${ctx}/resources/ui/charisma/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- Date Range Picker -->
		<script
		src="${ctx}/resources/ui/charisma/bower_components/daterangepicker/moment.min.js"></script>
	<script
		src="${ctx}/resources/ui/charisma/bower_components/daterangepicker/daterangepicker.js"></script>

	<!-- star rating plugin -->
	<script src="${ctx}/resources/ui/charisma/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="${ctx}/resources/ui/charisma/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script
		src="${ctx}/resources/ui/charisma/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script
		src="${ctx}/resources/ui/charisma/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="${ctx}/resources/ui/charisma/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="${ctx}/resources/ui/charisma/js/charisma.js"></script>

	<script type="text/javascript"
		src="${ctx}/resources/js/jquery/jquery.form.js"></script>
	<!--  <script type="text/javascript" src="${ctx}/resources/js/charisma/utils.js"></script>-->
	<script type="text/javascript"
		src="${ctx}/resources/js/charisma/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/app/jd/jd.js"></script>
	<script type="text/javascript">
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	</script>
	 	<script type="text/javascript">
	 	$(function(){
		ajaxContent("/Index/dashboard/init");
	 	});
	</script>  

</body>
</html>