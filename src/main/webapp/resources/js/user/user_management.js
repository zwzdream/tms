function SearchUser(divId, formId,callback){
	var form = $("#" + formId)[0];
	form.action= ctx + '/User/username/listPage';
	form.method = "post";	
    var formData = new FormData(form);
	formData.append("pageNum","1"); 
	$('#table').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$.ajax({
		type : "post",
		url : form.action,
		data :formData,
		async: false,
		cache: false,
		contentType: false,  
        processData: false,
        dataType: "html",
		success:function(data){
			if(data !=""){
				$('#content').html(data);
			 }
				if( callback != null ){
					callback();
				}
				$('#loading').remove();
				$('#table').fadeIn();
				docReady();
			}
		});
	}

 function Edit(url, data, callback){
		$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
		$.ajax({
			type : "post",
			url : encodeURI(encodeURI(ctx + url)),
			data : {id:data},
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
 function Delete(url,data,callback){
	 $('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
		$.ajax({
			type : "post",
			url : encodeURI(encodeURI(ctx + url)),
			data : {id:data},
			dataType : "html",
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			async: false,
			cache: false,
			success:function(returnData){
				noty({type:"success",text: "Delete successed!", layout: "center", timeout: 3000});
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
 function SearchTo(url, data, callback){
		$('#table').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
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
				$('#table').fadeIn();
				docReady();
	        }
		});
	}
