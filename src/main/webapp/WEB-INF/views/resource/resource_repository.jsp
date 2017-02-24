<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/resources/js/user/resource_management.js"></script>
<form id="splitPage" class="form-horizontal" action="${ctx}/Resource/searchresource" method="POST">
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
						<a href="#" class="btn btn-setting btn-round btn-default" title='Add' <%-- onclick="ajaxContent('${ctx}/resource/add.html'); --%>">
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
							<label class="col-sm-2 control-label" for="keyWord">KeyWord</label>
						  	<div class="col-sm-3">
							  	<input class="form-control" type="text" name="keyWord" id="keyWord" value='' maxlength="20" >
						  	</div>	
						  	
						  	<div class="form-actions" >
					  		 <!--   <div style="float:right;"> -->
								<button type="button" class="btn btn-primary" onclick="searchResource('/Resource/searchresource');">Search</button>
								&nbsp;&nbsp;&nbsp;
					  			<button type="button" class="btn" onclick="ajaxContent('/Resource/toAdd.html');">Add</button>
								&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn" onclick="ajaxContent('/Resource/toimport');">Import</button>
								&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn">Clear</button>
					  		<!--   </div> -->
					      	</div> 
					  	
					  	</div>
					<!--   	<div class="form-actions" >
					  		<div style="float:right;">
								<button type="button" class="btn btn-primary" onclick="searchResource('/Resource/searchresource');">Search</button>
								&nbsp;&nbsp;&nbsp;
					  			<button type="button" class="btn" onclick="ajaxContent('/Resource/toAdd.html');">Add</button>
								&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn" onclick="ajaxContent('/Resource/toimport');">Import</button>
								&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn">Clear</button>
					  		</div>
					  	</div> -->
					</fieldset>
				</div>
			</div>
		</div><!--/span-->
	</div><!--/row-->
	<div id="resourceTableDiv" >
        <!-- content starts -->
		<%@ include file="resource_repository_grid.jsp"%>
   		<!-- content ends -->
   	</div><!--/#content.col-md-0-->
</form>