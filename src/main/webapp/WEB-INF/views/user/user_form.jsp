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

	function addUser(form){
		var formData = new FormData(form);
	  form.action= ctx + '/User/add/save';
	  $('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	  $.ajax({
			type : "post",
			url : form.action,
			data :formData,
			async: false,
			cache: false,
			contentType: false,  
	        processData: false,
	        dataType : 'html',
	        success:function(data){
				if(data !=""){
					noty({type:"success",text: "Add successed!", layout: "center", timeout: 3000});
					$('#content').html(data);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
				noty({type:"error",text: "Add failed!", layout: "center", timeout: 3000});
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
	<!-- <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="id">Id.</label>
        <div class="col-sm-5">
         	<input type="number" class="form-control" id="id" name="id" placeholder="Id." style="height:35px;">
        </div>
    </div> -->
     <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="title">UserName</label>
        <div class="col-sm-5">
   			<input type="text" class="form-control" id="username" name="username" placeholder="UserName" style="height:35px;"></input>
   		</div>
   		    <label class="col-sm-1 control-label" for="status">Password</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="password" name="password" placeholder="Password" style="height:35px;">
   		</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="location">Mail</label>
        <div class="col-sm-5">
			<input type="text" class="form-control" id="mail" name="mail" placeholder="Mail" style="height:35px;"></input>							        	
    	</div>
    	   <label class="col-sm-1 control-label" for="payrate">Telphone</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="telphone" name="telphone" placeholder="Telphone" style="height:35px;">
   		</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="Permission">Permission</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="permission" name="permission" placeholder="Permission" style="height:35px;"></input>
   		</div>
    </div>
</fieldset>