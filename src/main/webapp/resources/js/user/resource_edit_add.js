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