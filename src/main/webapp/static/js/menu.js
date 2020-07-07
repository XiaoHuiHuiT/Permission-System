$(function () {


    /*----------------------------------------------------0.初始化组件----------------------------------------------------*/
    LoadComponentFn();
    /**********************************************************************************************************************/


    /*// 1.员工数据表格
    LoadEmpDatafn();

    // 2.添加员工
    addEmpFn();

    // 3.编辑员工
    EditingStaffFn();

    // 4.软删除
    delEmployeeFn();

    // 5.高级搜索
    searchEmployeeFn();

    // 6.刷新
    refreshFn();*/

    // 2.添加菜单
    addMenuFn();

    // 3.编辑菜单
    EditMenuFn();

    // 4.删除菜单
    deleteMenuFn();
});

function LoadComponentFn() {
    $("#menu_datagrid").datagrid({
        url:"/menuList",
        columns:[[
            {field:'text',title: "名称",width:1,align:'center'},
            {field:'url',title: "跳转地址",width:1,align:'center'},
            {field:'parent',title: "父菜单",width:1,align:'center',formatter:function(value,row,index){
                    return value?value.text:'';
                }
            }
        ]],
        fit:true,
        rownumbers:true,
        singleSelect:true,
        striped:true,
        pagination:true,
        fitColumns:true,
        toolbar:'#menu_toolbar'
    });

    /*
     * 初始化新增/编辑对话框
     */
    $("#menu_dialog").dialog({
        width:300,
        height:300,
        closed:true,
        buttons:'#menu_dialog_bt'
    });

    $("#add").click(function () {
        $("#menu_form").form("clear");
        $("#menu_dialog").dialog("setTitle","添加菜单");
        $("#menu_dialog").dialog("open");
    });

    $("#cancel").click(function () {
        $("#menu_dialog").dialog("close");
    });

    $("#parentMenu").combobox({
        width: 150,
        panelHeight: 'auto',
        editable: false,
        url: "parentList",
        // 发送给服务器的值
        valueField: 'id',
        // 页面显示的值
        textField: 'text',
        // 在加载远程数据成功的时候触发。
        onLoadSuccess: function () {
            $("#parentMenu").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
}
function addMenuFn() {
    // 监听保存按钮的点击
    $("#save").click(function () {
        // 添加ID是没有值得    只有编辑才有
        let id = $("[name='id']").val();
        let url;
        if (id) {
            let parent_id = $("[name='parent.id']").val();
            if(id == parent_id){
                $.messager.alert("提示","不能设置自己为父菜单");
                return;
            }
            // 编辑
            url = "updateMenu";
        } else {
            // 添加
            url = "saveMenu";
        }

        // 提交表单     内部使用的还是ajax提交
        $("#menu_form").form("submit", {
            url: url,
            success: function (data) {
                data = $.parseJSON(data);
                if (data.success) {
                    $.messager.alert("温馨提示", data.msg);

                    // 关闭对话框
                    $("#menu_dialog").dialog("close");

                    // 重新加载下拉列表数据
                    $("#parentMenu").combobox("reload");
                    // 重新加载数据表格
                    $("#menu_datagrid").datagrid("reload");
                } else {
                    $.messager.alert("温馨提示", data.msg);
                }
            }
        });

    });
}
function EditMenuFn() {
    // 监听编辑按钮的点击
    $("#edit").click(function () {

        // 清空表单数据
        $("#menu_form").form("clear");

        // 获取当前选中的行
        let rowData = $("#menu_datagrid").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示", "选择一行数据进行编辑");
            return;
        }

        // 父菜单回显
        if(rowData.parent){
            rowData["parent.id"] = rowData.parent.id;
        }else{
            // 回显的placehloder
            $("#parentMenu").each(function (i) {
               let span = $(this).siblings("span")[i];
               let targetInput = $(span).find("input:first");
               if(targetInput){
                   $(targetInput).attr("placeholder",$(this).attr("placeholder"));
               }
            });
        }

        // 弹出对话框
        $("#menu_dialog").dialog("setTitle","编辑菜单");
        $("#menu_dialog").dialog("open");

        // 选中数据的回显
        $("#menu_form").form("load", rowData);
    });
}
function deleteMenuFn(){
    $("#del").click(function () {
        // 获取当前选中的行的数据
        let rowData = $("#menu_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行删除");
            return;
        }

        // 提醒用户，是否做删除操作
        $.messager.confirm("确认", "是否做删除操作", function (res) {
            if (res) {
                // 做删除操作
                $.get("/deleteMenu?id=" + rowData.id, function (data) {

                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        // 重新加载下拉列表数据
                        $("#parentMenu").combobox("reload");
                        // 重新加载数据表格
                        $("#menu_datagrid").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                });
            }
        });
    });
}