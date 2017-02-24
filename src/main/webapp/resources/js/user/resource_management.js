/* function searchResource(url){

		$("#rsRepositoryTable").dataTable().fnClearTable(false);
		initTable();


} */
 function searchResource(url){
	var keyWord = $('#keyWord').val();
	if(keyWord=='') {
		noty({type:"warning",text: "The keyword is emopty! Please enter a keyword to accurately query.", layout: "bottom", timeout: 3000});
		initTable();
	}else{
		$("#rsRepositoryTable").dataTable().fnClearTable(false);
		initTable();
	}

} 
function initTable(){
	var keyWord = $('#keyWord').val();
	$('#rsRepositoryTable').DataTable({
    	searching: false,
    	iDisplayLength: 10,//pagesize
    	aLengthMenu: [2, 5, 8, 10],
    	bLengthChange: true,
    	processing: true,
    	serverSide: true,
    	destroy: true,
    	bFilter:false,
    	bSort:false,
    	bPaginate: true,
    	//sDom: "<'row'<'col-md-6'><'col-md-6'f>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
       // sPaginationType: "bootstrap",
       sDom: '<"top"f>rt<"bottom"ip><"clear">',
        oLanguage: {
        	"sProcessing":"Loading......",
        	"sLengthMenu":" _MENU_ records per page",
            "sZeroRecords":"NO Records for Table!",
            "sSearch":"Search",
            "oPaginate" : {
                "sFirst" : "First",
                "sPrevious" : "Previous",
                "sNext" : "Next",
                "sLast" : "Last"
            }
        },
    	sAjaxSource: ctx+'/Resource/searchresource?keyWord='+keyWord, //'Get'to server url
    	aoColumns: [
				 {sDefaultContent: ''},
    	         {sDefaultContent: ''},
    	         {mData: 'age'},
    	         {mData: 'gender'},
    	         {mData: 'lastMTime'},
    	         {sDefaultContent: ''},
     	     /*     {sDefaultContent: ''}  */
    	],
    	fnRowCallback: function(nRow, aData, iDisplayIndex) {
    		var tableSetings = this.fnSettings(); 
    		//var paging_length = tableSetings._iDisplayLength;  //pagesize
    		var page_start = tableSetings._iDisplayStart;
    		$('td:eq(0)', nRow).html(page_start+iDisplayIndex+1);
    		$('td:eq(1)', nRow).html(aData.firstName+' '+aData.lastName);
    		if(aData.gender){
    			$('td:eq(3)', nRow).html('<span class="label-success label label-default">Male</span>');
    		}else{
    			$('td:eq(3)', nRow).html('<span class="label-warning label label-default">Female</span>');
    		}
    
    		if(aData.type=='add'){
    			$('td:eq(5)', nRow).html('<a  href="#" onclick=ajaxContent(\"/Resource/toEditAdd\","resourceId='+ aData.id +'");>'
    	    			+'<i class="glyphicon glyphicon-edit icon-white"></i>Edit</a>&nbsp;'
    	    			+'<a  href="#" onclick=deleteResource(\"/Resource/toDeleteAdd\","resourceId='+ aData.id +'");>'
    	    			+'<i class="glyphicon glyphicon-trash icon-white"></i>Delete</a>');
    		}else if(aData.type=='import'){
    			$('td:eq(5)', nRow).html('<a   href="#" onclick=ajaxContent(\"/Resource/toEditImport\","resourceId='+ aData.id +'");>'
    	    			+'<i class="glyphicon glyphicon-edit icon-white"></i>Edit</a>&nbsp;'
    	    			+'<a  href="#" onclick=deleteResource(\"/Resource/toDeleteImport\","resourceId='+ aData.id +'");>'
    	    			+'<i class="glyphicon glyphicon-trash icon-white"></i>Delete</a>');
    		}
    	
     		/* $('td:eq(6)', nRow).html('<a class="btn btn-success" href="#" onclick=ajaxContent(\"/Resource/toEditJD\","resourceId='+ aData.id +'&resourceType='+ aData.type +'");>'
	    			+'<i class="glyphicon glyphicon-edit icon-white"></i>Edit JD</a>&nbsp;'
	    			+'<a class="btn btn-info" href="#" onclick=ajaxContent(\"/Resource/toScan\","resourceId='+ aData.id +'&resourceType='+ aData.type +'");>'
	    			+'<i class="glyphicon glyphicon-eye-open icon-white"></i>Scan</a>');  */
    		
    		
    		return nRow;
    	},
    	fnInitComplete: function(oSettings, json) { 
            //
        }
    });
}
$(document).ready(function() {  
	initTable();
});



function deleteResource(url, data,callback){
	 if(confirm('Are you sure?')){
	$('#content').fadeOut().parent().append('<div id="loading" class="center">Loading...<div class="center"></div></div>');
	$.ajax({
		type : "post",
		url : ctx + url,
		data : data,
		async: false,
		cache: false,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
        processData: false,
        dataType: "json",
		success:function(obj){
			if((obj) && (obj.success)){
				$("#rsRepositoryTable").dataTable().fnDraw(false);//删除后，刷新表格
				if( callback != null ){
					callback();
				}
				noty({type:"success",text: "Delete successed!", layout: "bottom", timeout: 3000});
			}else{
				noty({type:"error",text: "Delete failed!", layout: "bottom", timeout: 3000});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			noty({type:"error",text: "Delete failed!", layout: "bottom", timeout: 3000});
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