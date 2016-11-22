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
		  form.action= ctx + '/Group/edit/update';
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
         	<input type="number" class="form-control" id="id" name="id" value="${group.id }" style="height:35px;" readonly>
        </div>
  </div>
     <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="title">Group Name</label>
        <div class="col-sm-5">
   			<input type="text" class="form-control" id="name" name="name" value="${group.name }" style="height:35px;"></input>
   		</div>
    </div>		
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="status">Remarks</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="remark" name="remark" value="${group.remark }" style="height:35px;">
   		</div>
    </div>					                    
   
</fieldset>