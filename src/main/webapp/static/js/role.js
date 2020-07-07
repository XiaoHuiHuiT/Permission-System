$(function () {

    /*----------------------------------------------------0.初始化组件----------------------------------------------------*/
    LoadComponentFn();
    /**********************************************************************************************************************/

    // 1.角色数据表格
    LoadEmpDatafn();

    // 2.添加角色
    addRoleFn();

    // 3.编辑角色
    editRoleFn();

    // 4.删除角色
    deleteRoleFn();
});

function LoadComponentFn() {
    // 添加编辑对话框
    $("#dialog").dialog({
        width: 600,
        height: 600,
        buttons: [{
            text: '保存',
            handler: function () {
                // 判断当前是保存操作还是添加操作
                let rid = $("[name='rid']").val();
                let url;
                if (rid) {
                    // 编辑
                    url = "updateRole";
                } else {
                    // 添加
                    url = "saveRole";
                }

                // 提交表单
                $("#myform").form("submit", {
                    url: url,
                    onSubmit: function (param) {
                        // 传递额外参数，已选择的权限
                        // 获取已经选择的权限
                        let allRows = $("#role_data2").datagrid("getRows");
                        // 遍历出每一个权限
                        for (let i = 0; i < allRows.length; i++) {
                            // 取出每一个权限
                            let row = allRows[i];
                            // 给他封装到集合中
                            param["permissions[" + i + "].pid"] = row.pid;
                        }
                    },
                    success: function (data) {
                        data = $.parseJSON(data);
                        if (data.success) {
                            $.messager.alert("温馨提示", data.msg);

                            // 关闭对话框
                            $("#dialog").dialog("close");

                            // 重新加载数据表格
                            $("#role_dg").datagrid("reload");
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
        }],
        closed: true
    });

    // 权限列表
    $("#role_data1").datagrid({
        title: "所有权限",
        width: 250,
        height: 400,
        // 宽度自动伸缩
        fitColumns: true,
        // 不允许多选
        singleSelect: true,
        url: "permissionList",
        // 每一列
        columns: [[
            {field: "pname", title: "权限名称", width: 100, align: "center"}
        ]],
        /*
        点击一行时进行回调
        rowIndex：当前点击的哪一行
        rowData: 当前点击这一行的数据
         */
        onClickRow: function (rowIndex, rowData) {
            // 判断是否已经存在该权限
            // 取出所有的已选权限
            let allRows = $("#role_data2").datagrid("getRows");

            // 取出每一行进行判断
            for (let i = 0; i < allRows.length; i++) {
                // 取出每一行
                let row = allRows[i];

                // 如果左侧的权限id等于右侧的pid
                if (rowData.pid == row.pid) {
                    // 让已经存在的权限选中
                    // 获取已经成为选中状态的当前的角标
                    let index = $("#role_data2").datagrid("getRowIndex", row);

                    // 让该行成为选中状态
                    $("#role_data2").datagrid("selectRow", index);

                    // 已经存在该权限
                    return;
                }
            }

            // 把当前选中的添加到已选权限当中
            $("#role_data2").datagrid("appendRow", rowData);
        }
    });

    // 选中权限列表
    $("#role_data2").datagrid({
        title: "已选权限",
        width: 250,
        height: 400,
        // 宽度自动伸缩
        fitColumns: true,
        // 不允许多选
        singleSelect: true,
        // 每一列
        columns: [[
            {field: "pname", title: "权限名称", width: 100, align: "center"}
        ]],
        onClickRow: function (rowIndex, rowData) {
            // 删除当前选中的行
            $("#role_data2").datagrid("deleteRow", rowIndex);
        }
    });
}

function LoadEmpDatafn() {
    // 角色数据表格
    $("#role_dg").datagrid({
        // 加载数据
        url: "/getRoles",

        // 每一列
        columns: [[
            {field: "rnum", title: "角色编号", width: 100, align: "center"},
            {field: "rname", title: "角色名称", width: 100, align: "center"}
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
        toolbar: "#toolbar",

        // 不允许多选
        singleSelect: true,

        // 斑马线
        striped: true
    });
}

function addRoleFn() {
    // 监听添加按钮的点击
    $("#add").click(function () {
        // 清空表单数据
        $("#myform").form("clear");

        // 清空已选权限
        $("#role_data2").datagrid("loadData", {rows: []});

        // 设置标题
        $("#dialog").dialog("setTitle", "添加角色");

        // 打开对话框
        $("#dialog").dialog("open");
    });
}

function editRoleFn() {
    $("#edit").click(function () {
        // 添加之前把对话框的数据清空
        $("#myform").form("clear");

        // 获取当前选中的行的数据
        let rowData = $("#role_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行编辑");
            return;
        }
        // 加载当前角色下的权限
        let options = $("#role_data2").datagrid("options");
        options.url = "/getPermissionByRid?rid=" + rowData.rid;
        // 重新加载数据
        $("#role_data2").datagrid("load");

        // 回显表单
        $("#myform").form("load", rowData);
        // 设置标题
        $("#dialog").dialog("setTitle", "编辑角色");
        // 打开对话框
        $("#dialog").dialog("open");
    });
}

function deleteRoleFn() {
    // 监听删除按钮的点击
    $("#remove").click(function () {

        // 获取当前选中的行的数据
        let rowData = $("#role_dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行删除");
            return;
        }

        // 提醒用户，是否做删除操作
        $.messager.confirm("确认", "确认要删除吗？", function (res) {
            if (res) {
                $.get("deleteRole?rid=" + rowData.rid, function (data) {
                    // get请求会识别json
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        // 重新加载数据表格
                        $("#role_dg").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                });
            }
        });
    });
}