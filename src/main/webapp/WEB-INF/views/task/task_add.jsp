<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<ul class="breadcrumb">
            <li>
                <a href="#"  onclick='ajaxContent("/Index/dashboard/init")'>Home</a>
            </li>
            <li>
                <a href="#" onclick='ajaxContent("/Index/task/init")'>Task Management </a>
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
			<form class="form-horizontal" id="taskForm" name="taskForm"  action="#" method="POST">
				<%@ include file="task_form.jsp"%>
				<div class="form-actions" style="text-align:right;">
					<button class="btn btn-primary" type="button" onclick="addTask(this.form);">Submit</button>
				</div>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->