<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>陕西省人力资源市场管理信息系统</title>

    <meta name="keywords" content="xxx">
    <meta name="description" content="xxxx">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="<c:url value='/resource/hplus/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/font-awesome.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/animate.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resource/hplus/css/style.min.css'/>" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                <span class="text-muted text-xs block"><strong> 陕西省人力资源市场管理信息系统</strong></span>
                                <span class="text-muted text-xs block">欢迎您:<strong>${suser.cnname}</strong></span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">
                           ${suser.cnname}
                            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#">
		                    <i class="fa fa-bars"></i> 
		                    </a>
                        </div>
                    </li>
                    <shiro:user>
                    <shiro:hasPermission  name="perm:pagedesignindex">
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">页面设计</span>
                            <span class="fa arrow"></span>
                        </a>
                        
                        <shiro:hasPermission  name="perm:pagedesignlist">
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="<c:url value='/drag/list'/>" data-index="0">页面设计</a>
                            </li>
                        </ul>
                        </shiro:hasPermission>
                    </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission  name="perm:jobindex">
                    
                    <li>
                        <a href="#">
                            <i class="fa fa-bar-chart-o"></i>
                            <span class="nav-label">定时任务配置</span>
                            <span class="fa arrow"></span>
                        </a>
                        <shiro:hasPermission  name="perm:joblist">
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="<c:url value='/job/list'/>" data-index="1">定时任务配置</a>
                            </li>
                        </ul>
                        </shiro:hasPermission>
                    </li>
                    </shiro:hasPermission>
                    <!-- 管理员角色功能  -->
                    <shiro:hasRole name="admin">
                    <li>
                        <a href="#">
                            <i class="fa fa-sun-o"></i>
                            <span class="nav-label">权限管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="<c:url value='/sys/perm/index'/>" data-index="2">权限管理</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-users"></i>
                            <span class="nav-label">角色管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="<c:url value='/job/list'/>" data-index="3">角色管理</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-user"></i>
                            <span class="nav-label">授权管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="<c:url value='/job/list'/>" data-index="4">授权管理</a>
                            </li>
                        </ul>
                    </li>
                    </shiro:hasRole>
                    </shiro:user>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                            <a href="javascript:;" class="active J_menuTab"  data-id="index_v1.html" >首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>
                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="<c:url value='/loginout'/>" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="<c:url value='/index'/>" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy;2017浙江网新思普软件有限公司
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>
    <script src="<c:url value='/resource/hplus/js/jquery.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/metisMenu/jquery.metisMenu.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/slimscroll/jquery.slimscroll.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/layer/layer.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/hplus.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/contabs.min.js'/>"></script>
    <script src="<c:url value='/resource/hplus/js/plugins/pace/pace.min.js'/>"></script>
    <script type="text/javascript">
    $(function(){
    	//$('.navbar-minimalize').click();
    })
    </script>
</body>
</html>