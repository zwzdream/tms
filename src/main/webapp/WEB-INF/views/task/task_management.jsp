<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="uid" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.id}" />
<script>
var uid = ${uid};
</script>
<script src="${ctx}/resources/js/user/task_management.js"></script>


<form id="taskPage" class="form-horizontal" action="#" method="POST">
	<div>
		<ul class="breadcrumb">
	            <li>
	                  <a href="javascript:ajaxContent('/Index/dashboard/init')">Home</a>
	            </li>
	     
	    </ul>
	</div>
	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title>
					<h2><i class="glyphicon glyphicon-user"></i> </h2>
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round btn-default" title='Add'>
							<i class="glyphicon glyphicon-cog"></i></a> 
						<a href="#" class="btn btn-minimize btn-round btn-default">
							<i class="glyphicon glyphicon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round btn-default">
							<i class="glyphicon glyphicon-remove"></i></a>
					</div>
				</div>
				<div class="box-content">
					<fieldset>
					  	<div class="form-group">
							<label class="col-sm-2 control-label" for="name">Keyword</label>
						  	<div class="col-sm-3">
							  	<input id="keyword" class="form-control" type="text" name="keyword" value='' maxlength="20"  >
						  	</div>	
						<div class="form-actions" >
						  	<div>
								<button id="searchJD" type="button" class="btn btn-primary" onclick='doQry();'>Search</button>
								&nbsp;&nbsp;&nbsp;
					  			<button type="button" class="btn" onclick='ajaxContent("/Task/toAdd");'>Add</button>
								&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn">Clear</button>
					  		</div>
					  	</div>
					  	</div>
		
					</fieldset>
				</div>
			</div>
		</div><!--/span-->
	</div><!--/row-->
	<div id="table" class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title="">
					<h2>
						<i class="glyphicon glyphicon-user"></i>
					</h2>
	
					<div class="box-icon">
						<!-- <a href="#" class="btn btn-setting btn-round btn-default">
							<i class="glyphicon glyphicon-cog"></i></a>  -->
						<a href="#" class="btn btn-minimize btn-round btn-default">
							<i class="glyphicon glyphicon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round btn-default">
							<i class="glyphicon glyphicon-remove"></i></a>
					</div>
				</div>
				
			<div class="box-content">	
			<table id="taskTable" class="table table-striped table-bordered bootstrap-datatable ">
				     <thead>
				               <tr>
						        <th>Name</th>
						        <th>Priority</th>
						        <th>Status</th>
						        <th>CreateTime</th>
						        <th>Duration</th> 
						        <th>AssigneeTo</th>
						        <th>Consummator</th>
						        <th>Actions</th>
						    </tr>
						    </thead>
				    </table>			    
        
				</div>
			</div>
		</div>
		<!--/span-->
	</div>
	
</form>

