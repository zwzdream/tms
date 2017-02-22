<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<ul class="breadcrumb">
            <li>
                  <a href="javascript:ajaxContent('/Index/dashboard/init')">Home</a>
            </li>
            <li>
               <a href="javascript:ajaxContent('/Index/JD/init')">JD Management</a>
            </li>
    </ul>
</div>

<c:if test="${not empty jd}">
<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title>
				<h2>
					<i class="glyphicon glyphicon-edit"></i> Edit
				</h2>
				<div class="box-icon">
		            <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
		            <a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a>
		            <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
				</div>
			</div>
			
			<div class="box-content">
				<form class="form-horizontal" id="editForm" name="editForm" action="${ctx}/JD/edit/save" method="POST">
					<%@ include file="jd_edit_form.jsp"%>
					<div class="form-actions" style="text-align:right;">
						<button class="btn btn-primary" type="button" onclick="closeJD(this.form);">Close JD</button>
						<button class="btn btn-primary" type="button" onclick="dataValiE(this.form);">Submit</button>
					</div>
				</form>
			</div>
		
		</div>
		<!--/span-->
	
	</div>
   
	
</div>
<!--/row-->
</c:if>

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
		  <div id="jd_add_resource" class="box-content">
         <%@ include file="jd_add_resource_table.jsp"%> 
          <%--  <%@ include file="jd_add_resource.jsp"%>  --%>
           </div>
 </div>
 </div>
 </div>
<%-- <%@ include file="jd_grid.jsp"%> --%>