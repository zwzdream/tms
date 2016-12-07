<div>
	<ul class="breadcrumb">
            <li>
                <a href="#">Home</a>
            </li>
            <li>
                <a href="#">Resource Repository</a>
            </li>
            <li>
                <a href="#">Resume Add</a>
            </li>
    </ul>
</div>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2>
				<i class="icon-edit"></i>Resume&nbsp;Info
			</h2>
			<div class="box-icon">
				<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a> 
				<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
			</div>
		</div>
		
		<div class="box-content">
			<form class="form-horizontal" id="addResume" method="POST">
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
   
				<div class="form-actions" style="text-align:center;">
					<button class="btn btn-primary" type="button" onclick="addResume('content','addResume');">Submit</button>
				</div>
			</form>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->