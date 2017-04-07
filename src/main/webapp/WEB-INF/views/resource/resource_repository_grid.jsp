<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
						<!-- <a href="#" class="btn btn-close btn-round btn-default">
							<i class="glyphicon glyphicon-remove"></i></a> -->
					</div>
				</div>
				<div class="box-content">
				    <table id="rsRepositoryTable" class="table table-striped table-bordered bootstrap-datatable responsive" >
						    <thead>
						    <tr>
						    	<th>No.</th>
						        <th>Name</th>
						        <th>Industry&nbsp;
						        Experience</th>
						        <th>Title</th>
						        <th>Location</th>
						        <th>Work&nbsp;Eligibility</th>
						        <th>Resume</th>
						        <th>Actions</th>
						    </tr>
						    </thead>
				    </table>
				</div>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->