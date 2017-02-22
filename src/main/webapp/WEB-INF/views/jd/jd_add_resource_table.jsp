<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/resources/js/user/jd_resource_table.js"></script>
<input type="hidden" id="id" name="id" value="${jdNo }">
<div class="row">
<form class="form-horizontal">
	<div class="box col-md-12">
	       	<div class="form-group">
	        <label class="col-sm-1 control-label" for="keyWord">Key&nbsp;Word</label>
			<div class="col-sm-3">
				<input id="keyWord" class="form-control" type="text" name="keyWord"  maxlength="20" >
			</div>	
    <button type="button" class="btn btn-primary" onclick="searchCanJoinResource('/Resource/searchCanJoinResource');">Search</button>
    	</div>
				<div class="box-content">
				    <table id="joinTable" class="table table-striped table-bordered bootstrap-datatable responsive" >
				    <caption>The table shows the resources that can be added to the jd</caption>
						    <thead>
						    <tr>
						        <th>#</th>
						    	<th>Type</th>
						        <th>Name</th>
						        <th>Age</th>
						        <th>Gender</th>
						        <th>Actions</th>
						    </tr>
						    </thead>
				    </table>
				</div>
			 
		</div>
	<div class="box col-md-12">

				<div class="box-content">
				    <table id="belongTable" class="table table-striped table-bordered bootstrap-datatable responsive" >
				      <caption>The table shows the resources that already belong to the jd</caption>
						    <thead>
						    <tr>
						    	<th>#</th>
						    	<th>Type</th>
						        <th>Name</th>
						        <th>Age</th>
						        <th>Gender</th>
						        <th>Actions</th>
						    </tr>
						    </thead>
				    </table>
				</div>
			
		</div> 
</form>
</div>
<!--/row-->
