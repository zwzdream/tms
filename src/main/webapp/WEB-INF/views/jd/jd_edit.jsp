<div>
	<ul class="breadcrumb">
            <li>
                <a href="#">Home</a>
            </li>
            <li>
                <a href="#">JD Management Edit</a>
            </li>
    </ul>
</div>
<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="icon-edit"></i> Edit
				</h2>
				<div class="box-icon">
		            <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
		            <a href="#" class="btn btn-minimize btn-round btn-default"><i
		                    class="glyphicon glyphicon-chevron-up"></i></a>
		            <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
				</div>
			</div>
			
			<div class="box-content">
				<form class="form-horizontal" id="editForm" name="editForm" action="${ctx}/JD/edit/save" method="POST">
					<%@ include file="jd_form.jsp"%>
					<div class="form-actions" style="text-align:right;">
						<button class="btn btn-primary" type="button" onclick="dataValiE(this.form);">Submit</button>
					</div>
				</form>
			</div>
		</div>
		<!--/span-->
	</div>
</div>
<!--/row-->
<%@ include file="jd_grid.jsp"%>