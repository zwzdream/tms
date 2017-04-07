<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
 $(function(){
	//attachCalendarInput();
	var gender = '${bean.relocation}';
	gender?$(':radio[name=relocation][value=true]').attr('checked',true):$(':radio[name=relocation][value=false]').attr('checked',true);
}); 
function updateResume(form){
	var formData = new FormData(form);
	form.action= ctx + '/Resource/updateResource';
	$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$.ajax({
		type : "post",
		url : form.action,
		data : formData,
		async: false,
		cache: false,
		contentType: false,  
        processData: false,
        dataType: "json",
		success:function(obj){
			if((obj) && (obj.success)){
				noty({type:"success",text: "Update successed!", layout: "bottom", timeout: 3000});
 			}else if((obj) && (obj.errorMessage)){
				noty({type:"error",text:"Update failes!" +obj.errorMessage, layout: "bottom", timeout: 3000});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			noty({type:"error",text: "Update failed!", layout: "bottom", timeout: 3000});
        },
        complete: function(XMLHttpRequest, textStatus) { 
	    	$('#loading').remove();
			$('#content').fadeIn();
			docReady();
        }
	}); 
}
</script>

<div>
	<ul class="breadcrumb">
	<c:if test="${not empty resourceId}">
            <li>
               <a href="javascript:ajaxContent('/Index/dashboard/init')">Home</a>
            </li>
            <li>
                <a href="javascript:ajaxContent('/Index/resource/init')">Resource Repository</a>
            </li>
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

<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title="">
			<h2>
				<i class="glyphicon glyphicon-edit"></i>Resume&nbsp;Info
			</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
			</div>
		</div>
		<div class="box-content">
			<form class="form-horizontal" id="editForm" name="editForm" method="POST">
			<input type="hidden"  id="id" name="id" value="${bean.id}">
				<div class="form-group tr-new">
        <label class="col-sm-2 control-label" for="firstName">Name&nbsp;</label>
        <div class="col-sm-4 input-group-sm" >
         	<input type="text" class="form-control" id="name" name="name" value="${bean.name}" style="height:35px;">
        </div>
        <label class="col-sm-2 control-label" for="title">Title&nbsp;</label>
        <div class="col-sm-4" >
         	<input type="text" class="form-control" id="title" name="title" value="${bean.title}" style="height:35px;">
        </div>
    </div>
 
    <div class="form-group tr-new">
    
         <label class="col-sm-2 control-label" for="degree">Degree</label>
        <div class="col-sm-4">
        	<input type="date" class="form-control" id="degree" name="degree" value="${bean.degree}" style="height:35px;">
   		</div> 

         <label class="col-sm-2 control-label" for="industryExperience">Industry&nbsp;Experience</label>
        <div class="col-sm-4">
        	<input type="date" class="form-control" id="industryExperience" name="industryExperience" value="${bean.industryExperience}" style="height:35px;">
   		</div> 
   	

    </div>	
   		
		
    <div class="form-group tr-new">
        <label class="col-sm-2 control-label" for="mobile">Mobile</label>
        <div class="col-sm-4" >
        	<input type="tel" class="form-control" id="mobile" name="mobile" value="${bean.mobile}" style="height:35px;">
        </div>
          <label class="col-sm-2 control-label" for="email">Email</label>
        <div class="col-sm-4" >
			<input type="email" class="form-control" id="email" name="email" value="${bean.email}" style="height:35px;"></input>							        	
    	</div>

    </div>					                    
					                    
    <div class="form-group tr-new">
        <label class="col-sm-2 control-label" for="location">Location</label>
       	<div class="col-sm-4" >
        	<input type="text" class="form-control" id="location" name="location" value="${bean.location}" style="height:35px;">
        </div>
        	<label class="col-sm-2 control-label" for="gender">Relocation</label>
       <label class="radio-inline" style="margin-left:15px;">
		  <input type="radio" id="inlineRadio1" name="relocation"  value="true" checked="checked">YES
		</label>
		 <label class="radio-inline" style="margin-left:15px;">
		  <input type="radio" id="inlineRadio2" name="relocation"  value="false">NO
		</label>
        

    </div>	
       <div class="form-group tr-new">
           <label class="col-sm-2 control-label" for="website">Web&nbsp;Site</label>
        <div class="col-sm-4" >
			<input type="email" class="form-control" id="website" name="website" value="${bean.website}" style="height:35px;"></input>							        	
    	</div>
           <label class="col-sm-2 control-label" for="workEligibility">Work&nbsp;Eligibility</label>
        <div class="col-sm-4" >
			<input type="email" class="form-control" id="workEligibility" name="workEligibility" value="${bean.workEligibility}" style="height:35px;"></input>							        	
    	</div>
    </div>
    <div class="form-group tr-new">
    	      <label class="col-sm-2 control-label" for="inputFile">Resume</label>
         <div class="col-sm-4">
                  <input type="file" id="inputFile" name="inputFile"> 
         </div>	
    </div>	
    	         
    <div  class="box-content ">
      <c:if test="${filePath !='error'}">
    <div><%@ include file="document_view.jsp"%></div>
    </c:if>
    </div>
    
    
   <c:if test="${not empty resourceId}">
				<div class="form-actions" style="text-align:center;">
					<button class="btn btn-primary" type="button" onclick="updateResume(this.form);">Submit</button>
				</div>
	</c:if>
	
   <c:if test="${not empty jdNo}">
				<div class="form-actions" style="text-align:center;">
					<button class="btn btn-primary" type="button" onclick="addTheResource('${bean.id}','${jdNo}');">ADD</button>
				</div>
	</c:if>
	
   <c:if test="${not empty jdNo2}">
				<div class="form-actions" style="text-align:center;">
					<button class="btn btn-primary" type="button" onclick="removeTheResource('${bean.id}','${jdNo2}');">REMOVE</button>
				</div>
	</c:if>

			</form>
		</div>
	
	</div>
	<!--/span-->
</div>
<!--/row-->
 <c:if test="${not empty resourceId}">
<div  class="row">
 <div class="box col-md-12">
  <div class="box-inner">
          <div class="box-header well" data-original-title="">
			<h2><i class="glyphicon glyphicon-edit"></i>Resource-JD</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
			</div>
		  </div>
		  <div id="resource_edit_jd" class="box-content">
           <%@ include file="resource_edit_jd.jsp"%>
           </div>
 </div>
 </div>
 </div>
</c:if> 