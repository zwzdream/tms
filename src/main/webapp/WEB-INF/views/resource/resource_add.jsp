<div>
	<ul class="breadcrumb">
            <li>
                <a href="#">Home</a>
            </li>
            <li>
                <a href="#">Resource Repository</a>
            </li>
            <li>
                <a href="#">Resume Add</a>
            </li>
    </ul>
</div>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2>
				<i class="icon-edit"></i>Resume&nbsp;Info
			</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>
		
		<div class="box-content">
			<form class="form-horizontal" id="editForm" name="editForm" action="${ctx}/Resource/toSave" method="POST">
				<%@ include file="resource_form.jsp"%>
				<div class="form-actions" style="text-align:center;">
					<button class="btn btn-primary" type="button" onclick="dataVali();">Submit</button>
				</div>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->