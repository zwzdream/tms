$(function(){
	$table=$('#dataTable');
   initTable();
});
function doQry(){
	var username= $('#username').val();
	if(username=='') {
		noty({type:"warning",text: " Please enter the username that you want to search accurately!", layout: "bottom", timeout: 3000});
		 initTable();
	}else{
		$table.dataTable().fnClearTable(false);
		 initTable();
	}
}
function initTable(){
	var username = $('#username').val();
	$table.DataTable({
		
    	searching: false,
    	iDisplayLength: 10,//pagesize
    	aLengthMenu: [5, 10, 15, 20],
    	bLengthChange: true,//是否允许选择分页
    	bProcessing: true,
    	bServerSide: true,
    	destroy: true,
    	bFilter:false,
    	bSort:false,
    	bPaginate: true,
    	//sDom: "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
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
    	sAjaxSource: ctx+'/User/username/list?username='+username, //'GET'to server url
        //"ajax": { "url": ctx+'/User/username/list?username='+username, "type": "POST" },
   	aoColumns:[
    		{mData:'id'},
    		{mData:'username'},
    		{mData:'password'},
    		{mData:'mail'},
    		{mData:'telphone'},
    		{mData:'permission'},
    		{mData:'date'},
    		{sDefaultContent: ''},
    		/*{sDefaultContent: ''}*/
    	],
    	fnRowCallback: function(nRow, aData, iDisplayIndex) {
    		$('td:eq(7)', nRow).html('<a  href="#" onclick="edit(\''+ aData.id + '\')";>'
	    			+'<i class="glyphicon glyphicon-edit icon-white"></i>Edit</a>&nbsp;'
	    			+'<a  href="#" onclick="del(\''+ aData.id + '\')";>'
	    			+'<i class="glyphicon glyphicon-trash icon-white"></i>Delete</a>');
    	/*	$('td:eq(8)',nRow).html('<a class="btn btn-info" onclick="editGroup(\''+aData.id+'\')";>'
    				+'<i class="glyphicon glyphicon-edit "></i>Edit</a>');*/
    	},

    	fnInitComplete: function(oSettings, json) { 
            //.
        }
    });
}
function edit(id){
	ajaxContent('/User/toEdit/' + id);
}
function del(id){
	if(confirm('Are you sure?')){ajaxContent('/User/delete/' + id);alert('DELETE SUCCESS!');}
}
function editGroup(id){
	ajaxContent('/User/editGroup/' + id);
}