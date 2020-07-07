$(function () {

    /*----------------------------------------------------0.初始化组件----------------------------------------------------*/
    LoadComponentFn();
    /**********************************************************************************************************************/


    // 1.员工数据表格
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
    refreshFn();

    // 7.excel导出
    excelOutFn();

    // 8.Excel导入
    ExcelInFn();
});

function LoadComponentFn() {
    // 对话框
    $("#dialog").dialog({
        width: 350,
        height: 400,
        closed: true,
        buttons: [{
            text: '保存',
            handler: function () {

                // 添加ID是没有值得    只有编辑才有
                let id = $("[name='id']").val();
                let url;
                if (id) {
                    // 编辑
                    url = "updateEmployee";
                } else {
                    // 添加
                    url = "saveEmployee";
                }

                // 提交表单     内部使用的还是ajax提交
                $("#employeeForm").form("submit", {
                    url: url,
                    onSubmit: function (param) {
                        // 获取选中的角色      当中选择的下拉列表的value值
                        let values = $("#role").combobox("getValues");

                        for (let i = 0; i < values.length; i++) {
                            // 取出下拉列表的每一个值
                            let rid = values[i];
                            param["roles["+i+"].rid"] = rid;
                        }
                    },
                    success: function (data) {
                        data = $.parseJSON(data);
                        console.log(data);
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg);

                            // 关闭对话框
                            $("#dialog").dialog("close");

                            // 重新加载数据表格
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示", data.msg);
                        }
                    }
                });
            }
        }, {
            text: '关闭',
            handler: function () {
                // 关闭对话框
                $("#dialog").dialog("close");
            }
        }]
    });

    // 部门选择下拉列表
    $("#department").combobox({
        width: 150,
        panelHeight: 'auto',
        editable: false,
        url: "departList",
        // 发送给服务器的值
        valueField: 'id',
        // 页面显示的值
        textField: 'name',
        // 在加载远程数据成功的时候触发。
        onLoadSuccess: function () {
            $("#department").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

    // 是否是管理员下拉列表
    $("#state").combobox({
        width: 150,
        panelHeight: 'auto',
        // 发送给服务器的值
        valueField: 'value',
        // 页面显示的值
        textField: 'label',
        editable: false,
        data: [{
            label: "是",
            value: "true"
        }, {
            label: "否",
            value: "false"
        }],
        // 在加载远程数据成功的时候触发。
        onLoadSuccess: function () {
            $("#state").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

    // 选择角色的下拉列表
    $("#role").combobox({
        width: 150,
        panelHeight: 'auto',
        editable: false,
        url: "roleList",
        // 发送给服务器的值
        valueField: 'rid',
        // 页面显示的值
        textField: 'rname',
        // 多选
        multiple: true,
        // 在加载远程数据成功的时候触发。
        onLoadSuccess: function () {
            // 以下代码为    为了让input现实placeholder的值
            $("#role").each(function (i) {
                let span = $(this).siblings("span")[i];
                let targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
}

function LoadEmpDatafn() {
    // 员工数据表格
    $("#dg").datagrid({
        // 加载数据
        url: "/employeeList",

        // 每一列
        columns: [[
            {field: "username", title: "姓名", width: 100, align: "center"},
            {field: "inputtime", title: "入职时间", width: 100, align: "center"},
            {field: "tel", title: "电话", width: 100, align: "center"},
            {field: "email", title: "邮箱", width: 100, align: "center"},
            {
                field: "department", title: "部门", width: 100, align: "center", formatter: function (value, row, index) {
                    // 先判断一下value是否有值，有值在返回name
                    if (value) {
                        return value.name;
                    }
                }
            },
            {
                field: "state", title: "状态", width: 100, align: "center", formatter: function (value, row, index) {
                    if (row.state) {
                        return "在职";
                    } else {
                        return "<font style='color: red;'>离职</font>";
                    }
                }
            },
            {
                field: "admin", title: "管理员", width: 100, align: "center", formatter: function (value, row, index) {
                    if (row.admin) {
                        return "是";
                    } else {
                        return "否";
                    }
                }
            }
        ]],

        // 占满整个页面
        fit: true,

        // 宽度自动伸缩
        fitColumns: true,

        // 显示行号
        rownumbers: true,

        // 导航
        pagination: true,

        // 绑定工具栏
        toolbar: "#tb",

        // 不允许多选
        singleSelect: true,

        // 斑马线
        striped: true,

        // 在用户点击一行的时候触发
        onClickRow: function (rowIndex, rowData) {
            // 判断当前是否是离职状态
            if (!rowData.state) {
                // 离职，就把离职按钮禁用
                $("#delete").linkbutton("disable");
            } else {
                // 没离职，就把离职按钮启用
                $("#delete").linkbutton("enable");
            }
        }
    });
}

function addEmpFn() {

    // 监听添加按钮的点击
    $("#add").click(function () {
        // 添加之前把对话框的数据清空
        $("#employeeForm").form("clear");

        $("#dialog").dialog("setTitle", "添加员工");
        $("#password").show();
        $("#dialog").dialog("open");

        // 添加密码验证
        $("[name='password']").validatebox({required: true});
    });
}

function EditingStaffFn() {
    // 监听编辑按钮的点击
    $("#edit").click(function () {
        // 添加之前把对话框的数据清空
        $("#employeeForm").form("clear");

        // 获取当前选中的行的数据
        let rowData = $("#dg").datagrid("getSelected");
        console.log(rowData);
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行编辑");
            return;
        }
        // 取消密码验证
        $("[name='password']").validatebox({required: false});
        $("#password").hide();

        // 弹出对话框
        $("#dialog").dialog("setTitle", "编辑员工");
        $("#dialog").dialog("open");

        if (rowData["department"]) {
            // 回显部门 处理部门
            rowData["department.id"] = rowData["department"].id;
        }
        if (rowData["department"]) {
            // 回显管理员
            rowData["admin"] = rowData["admin"] + "";
        }

        /*
        回显角色
        根据当前用户的id, 查出对应的角色
         */
        $.get("/getRoleByEid?id="+rowData.id, function (data) {
            // 设置下拉列表数据回显
            $("#role").combobox("setValues", data);
        });

        // 选中数据的回显
        $("#employeeForm").form("load", rowData);
    });
}

function delEmployeeFn() {
    // 监听离职按钮的点击
    $("#delete").click(function () {
        // 获取当前选中的行的数据
        let rowData = $("#dg").datagrid("getSelected");
        console.log(rowData);
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行离职");
            return;
        }

        // 提醒用户，是否做离职操作
        $.messager.confirm("确认", "是否做离职操作", function (res) {
            if (res) {
                // 做离职操作
                $.get("/updateState?id=" + rowData.id, function (data) {

                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        // 重新加载数据表格
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                });
            }
        });
    });
}

function searchEmployeeFn() {
    // 监听搜索按钮的点击
    $("#searchbtn").click(function () {
        // 获取搜索的内容
        let keyword = $("[name='keyword']").val();

        // 重新加载列表、把参数keyword传过去
        $("#dg").datagrid("load", {keyword: keyword});
    });
}

function refreshFn() {
    // 监听刷新按钮的点击
    $("#reload").click(function () {
        // 清空搜索内容
        let keyword = $("[name='keyword']").val("");

        // 重新加载数据
        $("#dg").datagrid("load", {});
    });
}

function excelOutFn(){
    $("#excelOut").click(function () {
        window.open("/downloadExcel");
    });
}

function ExcelInFn() {
    $("#excelUpload").dialog({
        width:260,
        height:180,
        title:"导入Excel",
        buttons:[{
            text:'保存',
            handler:function(){

                $("#uploadForm").form("submit", {
                    url: "uploadExcelFile",
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg);

                            // 关闭对话框
                            $("#excelUpload").dialog("close");

                            // 重新加载数据表格
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示", data.msg);
                        }
                    }
                });

            }
        },{
            text:'关闭',
            handler:function(){
                $("#excelUpload").dialog("close");
            }
        }],
        closed:true
    });

    $("#excelImpot").click(function () {
        $("#excelUpload").dialog("open");
    });

    $("#excelIn").click(function () {
        $("#excelUpload").dialog("open");
    });

    // 下载Excel模板
    $("#downloadTml").click(function () {
        window.open("/downloadExcelTpl");
    });
}