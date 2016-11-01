<div>
	<ul class="breadcrumb">
            <li>
                <a href="#">Home</a>
            </li>
            <li>
                <a href="#">Import Resource</a>
            </li>
    </ul>
</div>
<script type="text/javascript">

function importResource(divId, formId,callback){
	var form = $("#" + formId)[0];
	form.action= ctx + '/Resource/importResource';
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
		success:function(returnData){
			$("#content").html(returnData);
			if( callback != null ){
				callback();
			}
			noty({type:"success",text: "Import successed!", layout: "bottom", timeout: 3000});
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			noty({type:"error",text: "Import failed!", layout: "bottom", timeout: 3000});
        },
        complete: function(XMLHttpRequest, textStatus) { 
	    	$('#loading').remove();
			$('#content').fadeIn();
			docReady();
        }
	}); 
}

</script>
<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-edit"></i> Import Resource</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-setting btn-round btn-default"><i
                            class="glyphicon glyphicon-cog"></i></a>
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content">
                <form role="form" id="importResource">
                    <div class="form-inline">
                        <label for="firstName">First Name</label>&nbsp;
                        <input type="text" class="form-control" id="firstName" name="firstName">
                    </div>
                    <br>
                    <div class="form-inline">
                        <label for="lastName">Last Name</label>&nbsp;
                        <input type="text" class="form-control" id="lastName" name="lastName">
                    </div>
                    <br>
                    <div class="form-inline">
                        <label for="birth">Date of Birth</label>&nbsp;
                        <input type="date" class="form-control" id="birth" name="birth">
                    </div>
                    <br>
                    <div class="form-inline">
                        <label>Gender</label>&nbsp;&nbsp;
                        <input type="radio" name="gender" id="gender1" value="true"> Male &nbsp;&nbsp;
                        <input type="radio" name="gender" id="gender2" value="false"> Female
                    </div>
                    <br>
                    <div class="form-inline">
                        <label for="inputFile">Resource import</label>
                        <input type="file" id="inputFile" name="inputFile">
                        <p class="help-block">Please upload your resume.</p>
                    </div>
                    <button type="button" class="btn btn-default" onclick="importResource('content','importResource')">Submit</button>
                </form>

            </div>
        </div>
    </div>
    <!--/span-->

</div><!--/row-->