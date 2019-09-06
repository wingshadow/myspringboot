$(function () {
    $("#jqGrid").jqGrid({
        url: '../area/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: 'PID 上级行政区划代码', name: 'pid', index: 'pid', width: 80 }, 			
			{ label: 'PIDS 各个上级行政区划ID串，以英文逗号连接', name: 'pids', index: 'pids', width: 80 }, 			
			{ label: '行政区划名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '级别', name: 'lev', index: 'lev', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		area: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.area = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.area.id == null ? "../area/save" : "../area/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.area),
			    success: function(responseMessage){
			    	if(responseMessage.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(responseMessage.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../area/delete",
				    data: JSON.stringify(ids),
				    success: function(responseMessage){
						if(responseMessage.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(responseMessage.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../area/info/"+id, function(responseMessage){
                vm.area = responseMessage.area;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});