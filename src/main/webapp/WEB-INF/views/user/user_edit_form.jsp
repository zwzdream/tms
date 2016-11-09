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

	function editUser(form){
		 var formData = new FormData(form);
		  form.action= ctx + '/User/edit/update';
		  form.method = "post";	
		  if(confirm('Are you sure?')){
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
							noty({type:"success",text: "Edit successed!", layout: "center", timeout: 3000});
							$('#content').html(data);
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						noty({type:"error",text: "Edit failed!", layout: "center", timeout: 3000});
			        },
			        complete: function(XMLHttpRequest, textStatus) { 
				    	$('#loading').remove();
						$('#content').fadeIn();
						docReady();
			        }
				}); 
			  return true;
		  }
		  return false;
	}

</script>
<fieldset>
	 <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="id">Id</label>
        <div class="col-sm-5">
         	<input type="number" class="form-control" id="id" name="id" value="${user.id }" style="height:35px;" readonly>
        </div>

     <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="title">UserName</label>
        <div class="col-sm-5">
   			<input type="text" class="form-control" id="username" name="username" value="${user.username }" style="height:35px;"></input>
   		</div>
    </div>		
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="status">Password</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="password" name="password" value="${user.password }" style="height:35px;">
   		</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="location">Mail</label>
        <div class="col-sm-5">
			<input type="text" class="form-control" id="mail" name="mail" value="${user.mail }" style="height:35px;"></input>							        	
    	</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="payrate">Telphone</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="telphone" name="telphone" value="${user.telphone }" style="height:35px;">
   		</div>
    </div>
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="Permission">Permission</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="permission" name="permission" value="${user.permission }" style="height:35px;"></input>
   		</div>
    </div>
</fieldset>