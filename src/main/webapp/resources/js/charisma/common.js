function ajaxToLogout(form){
		 var formData = new FormData(form);
		  form.action= ctx + '/auth/logout';
		  form.method = "post";	
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
							$('#content').html(data);
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						noty({type:"error",text: "Please contact the administrator", layout: "center", timeout: 3000});
			        },
			        complete: function(XMLHttpRequest, textStatus) { 
				    	$('#loading').remove();
						$('#content').fadeIn();
						docReady();
			        }
			        });
	}

function ajaxToLogin(url, data, callback){
	$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$.ajax({
		type : "get",
		url : encodeURI(encodeURI(ctx + url)),
		data : data,
		dataType : "html",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		cache: false,
		success:function(returnData){
			$("#content").html(returnData);
			if( callback != null ){
				callback();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("error!");
		},
		complete: function(XMLHttpRequest, textStatus) { 
			$('#loading').remove();
			$('#content').fadeIn();
			docReady();
		}
	});
}
/**
 * 
 * @param {} url
 * @param {} data
 * @param {} callback
 */
function ajaxContent(url, data, callback){
	$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$.ajax({
		type : "post",
		url : encodeURI(encodeURI(ctx + url)),
		data : data,
		dataType : "html",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		cache: false,
		success:function(returnData){
			$("#content").html(returnData);
			if( callback != null ){
				callback();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("error!");
        },
        complete: function(XMLHttpRequest, textStatus) { 
	    	$('#loading').remove();
			$('#content').fadeIn();
			docReady();
        }
	});
}


/**
 * 
 * @param {} divId
 * @param {} formId
 * @param {} callback
 */
function ajaxForm(divId, formId, callback){
	$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$("#" + formId).ajaxSubmit({
		cache: false,
	    success:  function (data) {
	    	if(data != ""){
	    		$("#" + divId).html(data);
	    	}
			if( callback != null ){
				callback();
			}
	    	$('#loading').remove();
			$('#content').fadeIn();
			docReady();
	    }
	});
}
