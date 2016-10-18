<form id="splitPage" class="form-horizontal" action="${ctx}/jd/list" method="POST">
	<div>
		<ul class="breadcrumb">
	            <li>
	                <a href="#">Home</a>
	            </li>
	            <li>
	                <a href="#">JD Management</a>
	            </li>
	    </ul>
	</div>
	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title>
					<h2><i class="glyphicon glyphicon-user"></i> </h2>
					<div class="box-icon">
						<a href="#" class="btn btn-setting btn-round btn-default" title='Add' onclick="ajaxContent('${ctx}/resource/add.html');">
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
							<label class="col-sm-1 control-label" for="name">name</label>
						  	<div class="col-sm-3">
							  	<input class="form-control" type="text" name="name" value='' maxlength="20" placeholder="name">
						  	</div>	
					  	</div>
					  	<div class="form-actions" >
					  		<div style="float:right;">
								<button type="button" class="btn btn-primary" onclick='ajaxForm("content", "splitPage");'>Search</button>
								&nbsp;&nbsp;&nbsp;
					  			<button type="button" class="btn" onclick='ajaxForm("content", "splitPage");'>Add</button>
								&nbsp;&nbsp;&nbsp;
								<button type="reset" class="btn">Clear</button>
					  		</div>
					  	</div>
					</fieldset>
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
				<div class="box-content">
					<div class="row">
						<div class="col-md-6">
							<div id="DataTables_Table_0_length" class="dataTables_length">
								<label>
									<select name="DataTables_Table_0_length" size="1" aria-controls="DataTables_Table_0">
										<option value="10" selected="selected">10</option>
										<option value="25">25</option>
										<option value="50">50</option>
										<option value="100">100</option>
									</select>
									records per page
								</label>
							</div>
						</div>
					</div>
				    <table class="table table-striped table-bordered bootstrap-datatable ">
						    <thead>
						    <tr>
						        <th>Seq</th>
						        <th>Name</th>
						        <th>Description</th>
						        <th>Total</th>
						        <th>Complete</th>
						        <th>Actions</th>
						    </tr>
						    </thead>
						    <tbody>
							    <tr>
							        <td>1</td>
							        <td class="center">David R</td>
							        <td class="center">Member</td>
							        <td class="center">
							            <span class="label-success label label-default">228</span>
							        </td>
							        <td class="center">
							            <span class="label-success label label-default">Active</span>
							        </td>
							        <td class="center">
							            <a class="btn btn-info" href="#" onclick="ajaxContent('/JD/toEdit.html');">
							                <i class="glyphicon glyphicon-edit icon-white"></i>
							                Edit
							            </a>
							            <a class="btn btn-success" href="#" onclick="ajaxContent('/JD/toAdd.html');">
							                <i class="glyphicon glyphicon-edit icon-white"></i>
							                Add Resource
							            </a>
							        </td>
							    </tr>
							    <tr>
							        <td>2</td>
							        <td class="center">David R</td>
							        <td class="center">Member</td>
							        <td class="center">
							            <span class="label-success label label-default">128</span>
							        </td>
							        <td class="center">
							            <span class="label-success label label-default">Active</span>
							        </td>
							        <td class="center">
							            <a class="btn btn-info" href="#" onclick="ajaxContent('/JD/toEdit');">
							                <i class="glyphicon glyphicon-edit icon-white"></i>
							                Edit
							            </a>
							            <a class="btn btn-success" href="#">
							                <i class="glyphicon glyphicon-edit icon-white" onclick="ajaxContent('/JD/toAdd');"></i>
							                Add Resource
							            </a>
							        </td>
							    </tr>
						    </tbody>
				    </table>
					<div class="row">
						<div class="col-md-12">
							<div id="DataTables_Table_0_info" class="dataTables_info">Showing 1 to 2 of 2 entries</div>
						</div>
						<div class="col-md-12 center-block">
							<div class="dataTables_paginate paging_bootstrap pagination">
								<ul class="pagination">
									<li class="prev disabled">
										<a href="#"> Previous </a>
									</li>
									<li class="active">
										<a href="#">1</a>
									</li>
									<li class="next disabled">
										<a href="#"> Next </a>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 		    
				    <ul class="pagination pagination-centered">
                        <li><a href="#">Prev</a></li>
                        <li class="active">
                            <a href="#">1</a>
                        </li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">Next</a></li>
                    </ul> -->
				</div>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
</form>