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

	function closeJD(form){
	  document.editForm.action= ctx + '/JD/edit/close';
	  if(confirm('Are you sure?')){
		  ajaxForm("content", "editForm");
		  return true;
	  }
	  return false;
	}
	function dataValiE(form){
	  document.editForm.action= ctx + '/JD/edit/save';
	  if(confirm('Are you sure?')){
		  ajaxForm("content", "editForm");
		  return true;
	  }
	  return false;
	}

</script>
<fieldset>
	<div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="no">No.</label>
        <div class="col-sm-5">
         	<input type="number" class="form-control" id="no" name="no" value="${jd.no }" style="height:35px;" readonly>
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
        	<input type="text" class="form-control" id="status" name="status" value="${jd.status }" style="height:35px;" readonly>
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
        <label class="col-sm-1 control-label" for="payrate">Pay Rate</label>
        <div class="col-sm-5">
        	<input type="text" class="form-control" id="payrate" name="payrate" placeholder="PayRate" style="height:35px;">
   		</div>
    </div>
    <div class="form-group tr-new">
        <label class="col-sm-1 control-label" for="keyword">Key word</label>
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