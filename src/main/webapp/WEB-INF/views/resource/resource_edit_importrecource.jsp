<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- attachCalendarInput();不知道怎么回事，利用外部文件引入时，日期时间不能正确显示 -->
<%-- <c:set var="ctx" value="${pageContext.request.contextPath}" />
 <script src="${ctx}/resources/js/user/resource_edit_import.js"></script>  --%>
 
 <script type="text/javascript">
$(document).ready(		
		function() {
			attachCalendarInput();
			var gender = '${bean.gender}';
			gender ? $(':radio[name=gender][value=true]').attr('checked',
					true) : $(':radio[name=gender][value=false]').attr(
					'checked', true);
			var birth = '${bean.birth}';
			var birthArr = new Date(birth).toLocaleDateString().split('/');////toLocaleDateString
			var birthStr = birthArr.join("-");
			$('#birth').attr('value', birthStr);
			//var filePath = '${bean.filePath }';
			//var fileName = filePath.split('\\');
			//console.log(filePath );
			//var fileName = filePath.split('/').slice(-1);
			//$('#existFile').attr('value',filePath);
		});
function updateResource(divId, formId) {
	var fileName=$("#inputFile").val();
	if(fileName!=null && fileName!=""){
	var form = $("#" + formId)[0];
	form.action = ctx + '/Resource/updateImportResource';
	form.method = "post";
	var formData = new FormData(form);
	$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$.ajax({
		type : "post",
		url : form.action,
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		dataType : "json",
		success : function(obj) {
			if ((obj) && (obj.success)) {
				noty({type : "success",text : "Edit successed!",layout : "bottom",timeout : 3000
				});
			} else {
				noty({	type : "error",text : "Update failed!",layout : "bottom",timeout : 3000
				});
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			noty({type : "error",text : "Update failed!",layout : "bottom",timeout : 3000
			});
		},
		complete : function(XMLHttpRequest, textStatus) {$('#loading').remove();$('#content').fadeIn();docReady();
		}
	});}
	else{
		noty({
			type : "warning",text : "File input box cannot be empty!",layout : "center",timeout : 3000
		});
		
	}
}


</script>

<div>
	<ul class="breadcrumb">
	<c:if test="${not empty resourceId}">
		<li>  <a href="javascript:ajaxContent('/Index/dashboard/init')">Home</a></li>
		<li>  <a href="javascript:ajaxContent('/Index/resource/init')">Resource Repository</a></li>
	</c:if>
		<c:if test="${not empty jdNo}">
            <li>
                  <a href="javascript:ajaxContent('/Index/dashboard/init')">Home</a>
            </li>
            <li>
               <a href="javascript:ajaxContent('/Index/JD/init')">JD Management</a>
            </li>
            <li>
               <a href="javascript:ajaxContent('/JD/toEdit','no=${jdNo}')">JD Edit</a>
            </li>
       
            
     </c:if>
     
		<c:if test="${not empty jdNo2}">
            <li>
                  <a href="javascript:ajaxContent('/Index/dashboard/init')">Home</a>
            </li>
            <li>
               <a href="javascript:ajaxContent('/Index/JD/init')">JD Management</a>
            </li>
            <li>
               <a href="javascript:ajaxContent('/JD/toEdit','no=${jdNo2}')">JD Edit</a>
            </li>
       
            
     </c:if>
	</ul>
</div>

<div class="row">
 <c:if test="${not empty resourceId}">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-edit"></i> Edit Resource
				</h2>

				<div class="box-icon">
					<a href="#" class="btn btn-setting btn-round btn-default"><i
						class="glyphicon glyphicon-cog"></i></a> <a href="#"
						class="btn btn-minimize btn-round btn-default"><i
						class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
						class="btn btn-close btn-round btn-default"><i
						class="glyphicon glyphicon-remove"></i></a>
				</div>
			</div>
			<div class="box-content">
				<form role="form" id="editResource">
					<input type="hidden" id="id" name="id" value="${bean.id}">
					<div class="form-inline">
						<label for="firstName">First Name</label>&nbsp; <input type="text"
							class="form-control" id="firstName" name="firstName"
							value="${bean.firstName}">
					</div>
					<br>
					<div class="form-inline">
                        <label for="lastName">Last Name</label>&nbsp;
                        <input type="text" class="form-control" id="lastName" name="lastName" value="${bean.lastName}">
                    </div>
                    <br>
					  <div class="form-inline"> 
                      <div class="form-group input-calendar">
                        <label for="birth">Date of Birth</label>&nbsp;
                        <div class="input-group">
                        <input type="text" class="form-control" id="birth" name="birth"/>
                        <span class="input-group-addon fa fa-calendar"></span>
                        </div>
                        </div>
                 </div>
					<br>
		
					<div class="form-inline">
						<label>Gender</label>&nbsp;&nbsp; <input type="radio"
							name="gender" id="gender1" value="true"> Male
						&nbsp;&nbsp; <input type="radio" name="gender" id="gender2"
							value="false"> Female
					</div>
					<br>
					<div class="form-inline">
						<label for="inputFile">Resource import</label> <input type="file"
							id="inputFile" name="inputFile"> <label id="existFile">You
							have upLoaded the following file:<span style="color: GREEN">${bean.filePath }</span>
						</label>
						<p class="help-block">Please upload your resume.</p>
					</div>
				
					<button type="button" class="btn btn-primary"
						onclick="updateResource('content','editResource')">Submit</button>
				
					

				</form>

			</div>
		</div>

	</div>
	<!--/span-->
	</c:if>
</div>
<!--/row-->
<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-edit"></i>Scan
				</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-minimize btn-round btn-default"><i
						class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
						class="btn btn-close btn-round btn-default"><i
						class="glyphicon glyphicon-remove"></i></a>
				</div>
			</div>
			<div  class="box-content ">
				<div><%@ include file="document_view.jsp"%></div>
	 <c:if test="${not empty jdNo}">
				<div class="form-actions" style="text-align:center;">
					<button class="btn btn-primary" type="button" onclick="addTheResource('${bean.id}','${jdNo}','import');">ADD</button>
				</div>
	   </c:if>
	   
	 <c:if test="${not empty jdNo2}">
				<div class="form-actions" style="text-align:center;">
					<button class="btn btn-primary" type="button" onclick="removeTheResource('${bean.id}','${jdNo2}','import');">REMOVE</button>
				</div>
	   </c:if>

			</div>
	  
		</div>

	</div>
	
</div>
<!--/row-->
<c:if test="${not empty resourceId}">
<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-edit"></i>View
				</h2>
				<div class="box-icon">
					<a href="#" class="btn btn-minimize btn-round btn-default"><i
						class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
						class="btn btn-close btn-round btn-default"><i
						class="glyphicon glyphicon-remove"></i></a>
				</div>
			</div>
			<div id="resource_edit_jd" class="box-content">
				<%@ include file="resource_edit_jd.jsp"%>
			</div>
		</div>
	</div>
</div><!--/row-->
</c:if>

