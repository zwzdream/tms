<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<!-- bootstrapTable plugin -->
<script type="text/javascript" src="${ctx}/resources/js/user/user_management.js"></script>
<form id="splitPage" class="form-horizontal" action="#" method="POST">
	<div>
		<ul class="breadcrumb">
	            <li>
	                <a href="#">Home</a>
	            </li>
	            <li>
	                <a href="#">User Management</a>
	            </li>
	    </ul>
	</div>
	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title>
					<h2><i class="glyphicon glyphicon-user"></i> </h2>
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round btn-default" title='Add' onclick="ajaxContent('${ctx}/resource/add');">
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
							<label class="col-sm-1 control-label" for="name">User Name</label>
						  	<div class="col-sm-3">
							  	<input id="username" class="form-control" type="text" name="username" value='' maxlength="20" >
						  	</div>	
					  	</div>
					  	<div class="form-actions" >
					  		<div style="float:right;">
								<button type="button" class="btn btn-primary" onclick='SearchUser("table1", "splitPage");'>Search</button>
					  			<button type="button" class="btn" onclick='ajaxContent("/User/toAdd");'>Add</button>
								&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn">Clear</button>
					  		</div>
					  	</div>
					</fieldset>
				</div>
			</div>
		</div><!--/span-->
	</div><!--/row-->
	
	

 <c:if test="${not empty page}">
	<div id="table" class="row">
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
				<div class="box-content">
					<div class="row">
						<div class="col-md-6">
							<div id="DataTables_Table_0_length" class="dataTables_length">
								<label>
									<select id="pageSize" name="pageSize" size="1"  aria-controls="DataTables_Table_0" onchange='SearchUser("table1", "splitPage");'>
										<option value="5" selected="selected" >5</option>
										<option value="10" >10</option>
										<option value="15">15</option>
										<option value="20">20</option>
									</select>
									records per page
								</label>
							</div>
						</div>
					</div>
					
				    <table id="table1" class="table table-striped table-bordered bootstrap-datatable ">
						    <thead>
				    <tr>
						        <th >id</th>
						        <th>UserName</th>
						        <th>Password</th>
						        <th>Mail</th>
						        <th>Telphone</th>
						        <th>Permission</th>
						        <th>Date</th>
						        <th>Operation</th>
						    </tr>
						    </thead>
		 				    <tbody>
						       <c:forEach items="${page.list}" var="user" varStatus="status">
							    <tr>
							        <td >${user.id }</td>
							        <td>${user.username}</td>
							        <td class="center">${user.password}</td>
							        <td class="center">${user.mail}</td>
							        <td class="center">${user.telphone}</td>
							        <td class="center">${user.permission}</td>
							        <td class="center">${user.date}</td>
							        <td class="center">
							            <a class="btn btn-info" href="#" onclick="Edit('/User/toEdit','${user.id}');">
							                <i class="glyphicon glyphicon-edit icon-white"></i>
							                Edit
							            </a>
							            <a class="btn btn-warning" href="#" onclick="Delete('/User/edit/delete','${user.id}');">
							                <i class="glyphicon glyphicon-edit icon-white"></i>
							                Delete
							            </a>
							        </td>
							    </tr>
							    </c:forEach>
						    </tbody> 
				    </table>			    
         <table class="gridtable" style="width:100%;text-align: center;">
                <tr>
                    <c:if test="${page.hasPreviousPage}">
                        <td><a href="#" onclick="SearchTo('/User/username/listPage','username=${name}&pageNum=${page.prePage}&pageSize=${page.pageSize}');">Previous</a></td>
                    </c:if>
                    <c:forEach items="${page.navigatepageNums}" var="nav">
                        <c:if test="${nav == page.pageNum}">
                            <td style="font-weight: bold;">${nav}</td>
                        </c:if>
                        <c:if test="${nav != page.pageNum}">
                            <td><a href="#" onclick="SearchTo('/User/username/listPage','username=${name}&pageNum=${nav}&pageSize=${page.pageSize}')">${nav}</a></td>
                        </c:if>
                    </c:forEach>
                    <c:if test="${page.hasNextPage}">
                        <td><a href="#" onclick="SearchTo('/User/username/listPage','username=${name}&pageNum=${page.nextPage}&pageSize=${page.pageSize}');">Next</a></td>
                    </c:if>
                </tr>
            </table> 
                   <div id="nav"></div>
				</div>
			</div>
		</div>
		<!--/span-->
	</div>
	
	<!--/row-->
	</c:if>
</form>
