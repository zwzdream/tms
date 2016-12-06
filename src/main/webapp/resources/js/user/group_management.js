$(function(){
	$table=$('#dataTable');
   initTable();
});
function doQry(){
	   $table.dataTable().fnClearTable(false);
	   initTable();
}

function initTable(){
	var groupname = $('#groupname').val();
	$table.DataTable({
    	searching: false,
    	iDisplayLength: 5,//pagesize
    	aLengthMenu: [5, 10, 15, 20],
    	bLengthChange: true,//是否允许选择分页
    	bProcessing: true,
    	bServerSide: true,
    	destroy: true,
    	bPaginate: true,
    	sDom: "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
        sPaginationType: "bootstrap",
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
    	sAjaxSource: ctx+'/Group/group/list?name='+groupname, //'GET'to server url
        //"ajax": { "url": ctx+'/User/groupname/list?groupname='+groupname, "type": "POST" },
   	aoColumns:[
    		{mData:'id'},
    		{mData:'name'},
    		{mData:'remark'},
    		{sDefaultContent: ''},
    		{sDefaultContent: ''}
    	],
    	fnRowCallback: function(nRow, aData, iDisplayIndex) {
    		$('td:eq(3)', nRow).html('<a class="btn btn-info" href="#" onclick="edit(\''+ aData.id + '\')";>'
	    			+'<i class="glyphicon glyphicon-edit icon-white"></i>Edit</a>&nbsp;'
	    			+'<a class="btn btn-danger" href="#" onclick="del(\''+ aData.id + '\')";>'
	    			+'<i class="glyphicon glyphicon-trash icon-white"></i>Delete</a>');
    		$('td:eq(4)', nRow).html('<a class="btn btn-info" href="#" onclick="editUser(\''+ aData.id + '\')";>'
    				+'<i class="glyphicon glyphicon-edit icon-white"></i>Edit</a>')
    	},

    	fnInitComplete: function(oSettings, json) { 
            //
        }
    });
}
function edit(id){
	ajaxContent('/Group/toEdit/' + id);
}
function del(id){
	ajaxContent('/Group/delete/' + id);
}
function editUser(id){
	ajaxContent('/Group/editUser/' + id);
}