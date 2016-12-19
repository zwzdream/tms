<div>
	<ul class="breadcrumb">
            <li>
               <a href="javascript:ajaxContent('/Index/dashboard/init')">Home</a>
            </li>
            <li>
                <a href="javascript:ajaxContent('/Index/resource/init')">Resource Repository</a>
            </li>
    </ul>
</div>
<script type="text/javascript">
$(function(){
	attachCalendarInput();
	var gender = '${bean.gender}';
	gender?$(':radio[name=gender][value=true]').attr('checked',true):$(':radio[name=gender][value=false]').attr('checked',true);
	var birth = '${bean.birth}';
	var birthArr = new Date(birth).toLocaleDateString().split('/');////toLocaleDateString
	var birthStr = birthArr.join("-");
	$('#birth').attr('value',birthStr);
	var starts = '${bean.starts}';
	var startsArr = new Date(starts).toLocaleDateString().split('/');////toLocaleDateString
	var startsStr = startsArr.join("-");
	$('#starts').attr('value',startsStr);
});
function updateResume(divId, formId){
	var form = $("#" + formId)[0];
	form.action= ctx + '/Resource/updateResume';
	form.method = "post";
	var formData = new FormData(form);
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
				noty({type:"success",text: "Edit successed!", layout: "bottom", timeout: 3000});
 			}else{
				noty({type:"error",text: "Update failed!", layout: "bottom", timeout: 3000});
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
			<form class="form-horizontal" id="editResume" method="POST">
			<input type="hidden"  id="id" name="id" value="${bean.id}">
				<div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="firstNmae">First&nbsp;Name&nbsp;</label>
        <div class="col-sm-4 input-group-sm" style="margin-left: 13px;">
         	<input type="text" class="form-control" id="firstName" name="firstName" value="${bean.firstName}" style="height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="lastName">Last&nbsp;Name&nbsp;</label>
        <div class="col-sm-4">
         	<input type="text" class="form-control" id="lastName" name="lastName" value="${bean.lastName}" style="height:35px;">
        </div>
    </div>
     <div class="form-group tr-new">
         <div class=" input-calendar">     
            <label class="col-sm-1 control-label" for="birth">Date&nbsp;of&nbsp;Birth</label>
                       <div class="col-sm-4" >
                        <input type="text" class="form-control" id="birth" name="birth"/>
                        </div>
         </div>
   		<label class="col-sm-1 control-label" for="gender">Gender</label>
       <label class="radio-inline" style="margin-left:15px;">
		  <input type="radio" id="inlineRadio1" name="gender"  value="true">Male
		</label>
		 <label class="radio-inline" style="margin-left:15px;">
		  <input type="radio" id="inlineRadio2" name="gender"  value="false">Female
		</label>
    </div>		
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="mobile">Mobile</label>
        <div class="col-sm-4" style="margin-left: 15px;">
        	<input type="tel" class="form-control" id="mobile" name="mobile" value="${bean.mobile}" style="height:35px;">
        </div>
           <div class="form-group tr-new input-calendar">
                 <label class="col-sm-1 control-label" for="starts">Work&nbsp;Starts</label>&nbsp;
                       <div class="col-sm-4" ">
                        <input type="text" class="form-control" id="starts" name="starts"/>
                        </div>
         </div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="email">Email</label>
        <div class="col-sm-4" style="margin-left: 15px;">
			<input type="email" class="form-control" id="email" name="email" value="${bean.email}" style="height:35px;"></input>							        	
    	</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="residency">Residency</label>
       	<div class="col-sm-4" style="margin-left: 15px;">
        	<input type="text" class="form-control" id="residency" name="residency" value="${bean.residency}" style="height:35px;">
        </div>
    </div>
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="education" style="color:red;">Education</label>
        <div class="col-sm-9" style="margin-left: 15px;">
        	<textarea  class="form-control" id="education" name="education"  style="height:85px;">${bean.education}</textarea>
        </div>
    </div>	
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="workExp" style="color:red;">Work Experience</label>
        <div class="col-sm-9" style="margin-left: 15px;">
       		<textarea class="form-control" id="workExp" name="workExp" style="height:85px;">${bean.workExp}</textarea> 
        </div>
    </div>			                    				                    				                    		                   
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="projectExp" style="color:red;">Project Experience</label>
        <div class="col-sm-9" style="margin-left: 15px;">
			<textarea class="form-control" id="projectExp" name="projectExp"  style="height:85px;"> ${bean.projectExp}</textarea>							            
        </div>
    </div>
   
				<div class="form-actions" style="text-align:center;">
					<button class="btn btn-primary" type="button" onclick="updateResume('content','editResume');">Submit</button>
				</div>
			</form>
		</div>
	
	</div>
	<!--/span-->
</div>
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
		  <div id="resource_edit_jd" class="box-content">
           <%@ include file="resource_edit_jd.jsp"%>
           </div>
 </div>
 </div>
 </div>
