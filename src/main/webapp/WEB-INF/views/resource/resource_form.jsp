<style type="text/css">
	.tr-new {
		position: static;
		top: auto;
		right: auto;
		bottom: auto;
		left: auto;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 5px;
		margin-left: 0px;
		color: #333333;
		display: block;
		visibility: visible;
	}
</style>
<script type="text/javascript">

function addResume(divId, formId,callback){
	var form = $("#" + formId)[0];
	form.action= ctx + '/Resource/addResume';
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
				noty({type:"success",text: "Add successed!", layout: "bottom", timeout: 3000});
			}else{
				noty({type:"error",text: "Add failed!", layout: "bottom", timeout: 3000});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			noty({type:"error",text: "Add failed!", layout: "bottom", timeout: 3000});
        },
        complete: function(XMLHttpRequest, textStatus) { 
	    	$('#loading').remove();
			$('#content').fadeIn();
			docReady();
        }
	}); 
}

</script>
<fieldset>
	<div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="firstNmae">First&nbsp;Name&nbsp;</label>
        <div class="col-sm-4 input-group-sm" style="margin-left: 13px;">
         	<input type="text" class="form-control" id="firstName" name="firstName" placeholder="FirstNmae" style="height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="lastName">Last&nbsp;Name&nbsp;</label>
        <div class="col-sm-4">
         	<input type="text" class="form-control" id="lastName" name="lastName" placeholder="LastName" style="height:35px;">
        </div>
    </div>
     <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="birth">Date&nbsp;of&nbsp;Birth</label>
        <div class="col-sm-4" style="margin-left: 15px;">
   			<input type="date" class="form-control" id="birth" name="birth" placeholder="Date of Birth" style="height:35px;"></input>
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
        	<input type="tel" class="form-control" id="mobile" name="mobile" placeholder="Mobile" style="height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="starts">Work&nbsp;Starts</label>
        <div class="col-sm-4">
        	<input type="date" class="form-control" id="starts" name="starts" placeholder="Work Starts" style="height:35px;">
   		</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="email">Email</label>
        <div class="col-sm-4" style="margin-left: 15px;">
			<input type="email" class="form-control" id="email" name="email" placeholder="Email" style="height:35px;"></input>							        	
    	</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="residency">Residency</label>
       	<div class="col-sm-4" style="margin-left: 15px;">
        	<input type="text" class="form-control" id="residency" name="residency" placeholder="Residency" style="height:35px;">
        </div>
    </div>
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="education" style="color:red;">Education</label>
        <div class="col-sm-9" style="margin-left: 15px;">
        	<textarea  class="form-control" id="education" name="education" style="height:85px;"></textarea>
        </div>
    </div>	
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="workExp" style="color:red;">Work Experience</label>
        <div class="col-sm-9" style="margin-left: 15px;">
       		<textarea class="form-control" id="workExp" name="workExp"  style="height:85px;"></textarea> 
        </div>
    </div>			                    				                    				                    		                   
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="projectExp" style="color:red;">Project Experience</label>
        <div class="col-sm-9" style="margin-left: 15px;">
			<textarea class="form-control" id="projectExp" name="projectExp"  style="height:85px;"></textarea>							            
        </div>
    </div>
   
</fieldset>