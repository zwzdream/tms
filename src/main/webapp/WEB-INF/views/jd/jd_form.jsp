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

	function addJD(form){
		var formData = new FormData(form);
	  form.action= ctx + '/JD/add/save';
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
	 // ajaxForm("content", "editForm");
	}

</script>
<fieldset>
	<div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="no">No.</label>
        <div class="col-sm-5">
         	<input type="number" class="form-control" id="no" name="no" placeholder="No." style="height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="priority">Priority</label>
        <div class="col-sm-5">
         	<input type="text" class="form-control" id="priority" name="priority" placeholder="Priority" style="height:35px;">
        </div>
    </div>
     <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="title">Title</label>
        <div class="col-sm-11">
   			<input type="text" class="form-control" id="title" name="title" placeholder="Title" style="height:35px;"></input>
   		</div>
    </div>		
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="client">Client</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="client" name="client" placeholder="Client" style="height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="status">Status</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="status" name="status" placeholder="Status" style="height:35px;">
   		</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="location">Location</label>
        <div class="col-sm-11">
			<input type="text" class="form-control" id="location" name="location" placeholder="Location" style="height:35px;"></input>							        	
    	</div>
    </div>					                    
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="duration">Duration</label>
       	<div class="col-sm-5">
        	<input type="text" class="form-control" id="duration" name="duration" placeholder="Duration" style="height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="payrate">PayRate</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="payrate" name="payrate" placeholder="PayRate" style="height:35px;">
   		</div>
    </div>
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="keyword">KeyWord</label>
        <div class="col-sm-11">
        	<input type="text" class="form-control" id="keyword" name="keyword" placeholder="Key word" style="height:35px;"></input>
   		</div>
    </div>
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="local">Local</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="local" name="local" placeholder="Local" style="height:35px;">
        </div>
        <label class="col-sm-1 control-label" for="owner">Owner</label>
        <div class="col-sm-5">
       	 	<input type="text" class="form-control" id="owner" name="owner" placeholder="Owner" style="height:35px;">
    	</div>
    </div>	
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="description">Description</label>
        <div class="col-sm-11">
       		<textarea class="form-control" id="description" name="description" placeholder="Description" style="height:55px;"></textarea> 
        </div>
    </div>			                    				                    				                    		                   
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="qualification">Qualification</label>
        <div class="col-sm-11">
			<textarea class="form-control" id="qualification" name="qualification" placeholder="Qualification" style="height:55px;"></textarea>							            
        </div>
    </div>
    <div class="form-group tr-new">    
        <label class="col-sm-1 control-label" for="notes">Notes</label>
        <div class="col-sm-11">
        	<textarea class="form-control" id="notes" name="notes" placeholder="Notes" style="height:55px;"></textarea>
   		</div>
    </div>
</fieldset>