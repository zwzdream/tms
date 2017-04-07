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
/* $(function(){//日期插件
	attachCalendarInput();
}); */
function addResume(form){
	var formData = new FormData(form);
	form.action= ctx + '/Resource/addResource';
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
			}else if((obj) && (obj.errorMessage)){
				noty({type:"error",text: "Add failed!"+obj.errorMessage, layout: "bottom", timeout: 3000});
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
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2>
				<i class="glyphicon glyphicon-edit"></i>Resume&nbsp;Info
			</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="glyphicon glyphicon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round"><i class="glyphicon glyphicon-remove"></i></a>
			</div>
		</div>
		
		<div class="box-content">
	<form class="form-horizontal" id="addForm" name="addForm" method="POST">
		<div class="form-group tr-new">
        <label class="col-sm-2 control-label" for="firstName">Name&nbsp;</label>
        <div class="col-sm-4 input-group-sm" >
         	<input type="text" class="form-control" id="name" name="name" placeholder="Name" style="height:35px;">
        </div>
        <label class="col-sm-2 control-label" for="title">Title&nbsp;</label>
        <div class="col-sm-4" >
         	<input type="text" class="form-control" id="title" name="title" placeholder="Title" style="height:35px;">
        </div>
    </div>
 
    <div class="form-group tr-new">
    
         <label class="col-sm-2 control-label" for="degree">Degree</label>
        <div class="col-sm-4">
        	<input type="date" class="form-control" id="degree" name="degree" placeholder="Degree" style="height:35px;">
   		</div> 

         <label class="col-sm-2 control-label" for="industryExperience">Industry&nbsp;Experience</label>
        <div class="col-sm-4">
        	<input type="date" class="form-control" id="industryExperience" name="industryExperience" placeholder="Industry Experience" style="height:35px;">
   		</div> 
   	

    </div>	
   		
		
    <div class="form-group tr-new">
        <label class="col-sm-2 control-label" for="mobile">Mobile</label>
        <div class="col-sm-4" >
        	<input type="tel" class="form-control" id="mobile" name="mobile" placeholder="Mobile" style="height:35px;">
        </div>
          <label class="col-sm-2 control-label" for="email">Email</label>
        <div class="col-sm-4" >
			<input type="email" class="form-control" id="email" name="email" placeholder="Email" style="height:35px;"></input>							        	
    	</div>
       
     <!--      <div class="input-calendar">
                 <label class="col-sm-2 control-label" for="starts">Work&nbsp;Starts</label>
                       <div class="col-sm-4" >
                        <input type="text" class="form-control" id="starts" name="starts" />
                        </div>
         </div> -->

    </div>					                    
					                    
    <div class="form-group tr-new">
        <label class="col-sm-2 control-label" for="location">Location</label>
       	<div class="col-sm-4" >
        	<input type="text" class="form-control" id="location" name="location" placeholder="Location" style="height:35px;">
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
			<input type="email" class="form-control" id="website" name="website" placeholder="Website" style="height:35px;"></input>							        	
    	</div>
           <label class="col-sm-2 control-label" for="workEligibility">Work&nbsp;Eligibility</label>
        <div class="col-sm-4" >
			<input type="email" class="form-control" id="workEligibility" name="workEligibility" placeholder="WorkEligibility" style="height:35px;"></input>							        	
    	</div>
    </div>
    <div class="form-group tr-new">
    	      <label class="col-sm-2 control-label" for="inputFile">Resume</label>
         <div class="col-sm-4">
                  <input type="file" id="inputFile" name="inputFile"> 
         </div>	
    </div>	

        <div class="form-actions" style="text-align:center;">
        <button class="btn btn-primary" type="button" onclick="addResume(this.form);">Submit</button>
        </div>
        </form>
  </div>
</div>
	<!--/span-->
</div>
<!--/row-->