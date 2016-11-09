<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div>
	<ul class="breadcrumb">
            <li>
                <a href="#"  onclick='ajaxContent("/Index/user/init")'>Home</a>
            </li>
            <li>
                User Management Edit</a>
            </li>
    </ul>
</div>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2>
				<i class="icon-edit"></i> Add
			</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>
		
	<div class="box-content">
			<form class="form-horizontal" id="editForm" name="editForm"  action="#" method="POST">
				<%@ include file="user_edit_form.jsp"%>
				<div class="form-actions" style="text-align:right;">
					<button class="btn btn-primary" type="button" onclick="editUser(this.form);">Submit</button>
				</div>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->