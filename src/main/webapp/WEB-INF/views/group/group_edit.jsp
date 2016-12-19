<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div>
	<ul class="breadcrumb">
            <li>
               <a href="#"  onclick='ajaxContent("/Index/dashboard/init")'>Home</a>
            </li>
            <li>
               <a href="#"  onclick='ajaxContent("/Index/group/init")'>Group Management</a>
            </li>
    </ul>
</div>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2>
				<i class="glyphicon glyphicon-edit"></i> Add
			</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="glyphicon glyphicon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round"><i class="glyphicon glyphicon-remove"></i></a>
			</div>
		</div>
		
	<div class="box-content">
			<form class="form-horizontal" id="editForm" name="editForm"  action="#" method="POST">
				<%@ include file="group_edit_form.jsp"%>
				<div class="form-actions" style="text-align:right;">
					<button class="btn btn-primary" type="button" onclick="editUser(this.form);">Submit</button>
				</div>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<!--/row-->

<div  class="row">
 <div class="box col-md-12">
  <div class="box-inner">
          <div class="box-header well" data-original-title="">
			<h2><i class="glyphicon glyphicon-edit"></i>Add&nbsp;Resource</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
			</div>
		  </div>
		  <div id="group_user" class="box-content">
           <%@ include file="group_user.jsp"%>
           </div>
 </div>
 </div>
 </div>