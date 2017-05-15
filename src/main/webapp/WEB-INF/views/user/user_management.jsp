<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/resources/js/user/user_management.js"></script>

<form id="splitPage" class="form-horizontal" action="#" method="POST">
	<div>
		<ul class="breadcrumb">
	             <li>
                <a href="#"  onclick='ajaxContent("/Index/dashboard/init")'>Home</a>
            </li>

	    </ul>
	</div>
	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title>
					<h2><i class="glyphicon glyphicon-user"></i> </h2>
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round btn-default" title='Add' <%-- onclick="ajaxContent('${ctx}/resource/add'); --%>">
							<i class="glyphicon glyphicon-cog"></i></a> 
						<a href="#" class="btn btn-minimize btn-round btn-default">
							<i class="glyphicon glyphicon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round btn-default">
							<i class="glyphicon glyphicon-remove"></i></a>
					</div>
				</div>
				<div class="box-content">
			<!-- 		<fieldset> -->
					  	<div class="form-group">
							<label class="col-sm-2 control-label" for="name">UserName</label>
						  	<div class="col-sm-3">
							  	<input id="username" class="form-control" type="text" name="username"  maxlength="20" >
						  	</div>	
						  	
						  <div>
								<button id="button" type="button" class="btn btn-primary" onclick='doQry();'>Search</button>
					  			<button type="button" class="btn" onclick='ajaxContent("/User/toAdd");'>Add</button>
								&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn">Clear</button>
					  	</div>
					  		
					  	</div>
					<!--   	<div class="form-actions" >
					  		<div style="float:right;">
								<button id="button" type="button" class="btn btn-primary" onclick='doQry();'>Search</button>
					  			<button type="button" class="btn" onclick='ajaxContent("/User/toAdd");'>Add</button>
								&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn">Clear</button>
					  		</div>
					  	</div> -->
			<!-- 		</fieldset> -->
				</div>
			</div>
		</div><!--/span-->
	</div><!--/row-->


 
 <div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title="">
					<h2>
						<i class="glyphicon glyphicon-user"></i>
					</h2>
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round btn-default">
							<i class="glyphicon glyphicon-cog"></i></a> 
						<a href="#" class="btn btn-minimize btn-round btn-default">
							<i class="glyphicon glyphicon-chevron-up"></i></a> 
						<a href="#" class="btn btn-close btn-round btn-default">
							<i class="glyphicon glyphicon-remove"></i></a>
					</div>
				</div>
				<div class="box-content" >
				 <table id="dataTable" class="table table-striped table-bordered bootstrap-datatable responsive" >
				 	    <thead>
						    <tr>
						    	<th>No.</th>
						        <th>User&nbsp;Name</th>
						        <th>Password</th>
						        <th>Email</th>
						        <th>Telephone</th>
						        <th>Permission</th>
						        <th>Date</th>
						        <th>Operate</th>
						       <!--  <th>Group Setting</th> -->
						    </tr>
						    </thead>
				 
				 </table>
				</div>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->



</form>