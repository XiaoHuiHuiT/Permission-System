<%--
  Created by IntelliJ IDEA.
  User: 30315
  Date: 2019/11/20
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        #dialog #myform .panel-header{
            height: 25px;
        }
        #dialog #myform .panel-title{
            color: #000;
            margin-top: -5px;
        }
    </style>

    <%--引入easyui 的文件 --%>
    <%@include file="/static/common/common.jsp"%>

    <%--引入角色的js--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/role.js"></script>
</head>
<body>
        <%--工具栏--%>
        <div id="toolbar">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="remove">删除</a>
        </div>

        <%--数据表格--%>
        <div id="role_dg"></div>

        <%--添加和编辑的对话框--%>
        <div id="dialog">
            <form id="myform">
                <table align="center" style="border-spacing: 20px 30px">
                    <input type="hidden" name="rid">
                    <tr align="center">
                        <td>角色编号: <input type="text" name="rnum" class="easyui-validatebox" ></td>
                        <td>角色名称: <input type="text" name="rname" class="easyui-validatebox" ></td>
                    </tr>
                    <tr>
                        <td><div id="role_data1"></div></td>
                        <td><div id="role_data2"></div></td>
                    </tr>
                </table>
            </form>
        </div>
</body>
</html>