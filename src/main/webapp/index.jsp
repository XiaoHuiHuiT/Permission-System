<%--
  Created by IntelliJ IDEA.
  User: 30315
  Date: 2019/11/20
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>员工权限管理系统</title>

    <%--引入easyui 的文件 --%>
    <%@include file="/static/common/common.jsp"%>

    <%--引入首页的js--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>

</head>
<body class="easyui-layout">

<%--顶部logo区域--%>
<div data-options="region:'north'" style="height:100px; background: #ec4e00; padding: 20px 20px">
    <img src="${pageContext.request.contextPath}/static/images/logo.png" style="width: 300px;height: 70px;"/>

    <div style="position: absolute; right: 50px; top: 30px;">
        <img src="./static/images/user.png" style="vertical-align: middle; margin-right: 10px;" >
        <%--显示当前登录用户名--%>
        <span style="color: white; font-size: 20px; margin-right: 5px;"><shiro:principal property="username"/></span>
        <%--取消认证  跳转到 登录页面  在shiro配置文件当中  配置   /logout = logout --%>
        <a id="cancellationBtn" style="font-size: 18px; color: white;text-decoration: none;" href="${pageContext.request.contextPath}/logout">注销</a>
    </div>
</div>

<%--底部--%>
<div data-options="region:'south'" style="height:50px; border-bottom: 3px solid #ec4e00">
    <p align="center" style="font-size: 14px">Copyright &copy; 2017.Company Name All Rights Reserved.More Templates - Collect From</p>
</div>

<%--左侧--%>
<div data-options="region:'west',split:true" style="width:300px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">
        <div title="菜单" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">
            <!--tree-->
            <ul id="tree"></ul>
        </div>
        <div title="公告" data-options="iconCls:'icon-reload'" style="padding:10px;">
        </div>
    </div>
</div>

<%--右侧主题--%>
<div data-options="region:'center'" style="background:#eee;">
    <!--标签  右侧选项卡展示区域-->
    <div id="tabs" style="overflow: hidden" >
    </div>
</div>

</body>
</html>