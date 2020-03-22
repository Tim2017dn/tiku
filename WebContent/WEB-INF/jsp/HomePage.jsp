<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>题库管理系统</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.6/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.6/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.7.6/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.7.6/jquery.easyui.min.js"></script>
	
<title>Insert title here</title>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height: 90px" >   <!-- background: #2596ea; padding: 10px" -->
		
		<!-- <p style="font-size:30px;font-family:verdana;color:blue;">
			题 库 管 理 系 统</p> -->
	
    <%--   <img  src="<%=basePath%>images/logo.png" /> --%>
    
    <!-- 	<p align="center" style="font-size:40px;color:blue;">题 库 管 理 系 统</p> -->
	<%-- 	  <p align="right" style="font-size:20px"> 欢迎您  ${username}<a href="${pageContext.request.contextPath }/UserLogout.action">登出</a></p> --%>
	
		<div class="footer" align="center" style="font-size:40px;color:#039BE5;">题库管理与试卷自动生成系统</div>
		<div class="footer" align="right">欢迎您  ${realname}  <a href="${pageContext.request.contextPath }/UserLogout.action">登出</a></div>
     </div>
     <div data-options="region:'south',border:false" style="height: 23px;">
         <div class="footer">题库管理与试卷自动生成系统</div>
     </div>
     
     <div data-options="region:'west',split:true" title="功能栏" style="width: 170px;">
       <div class="easyui-accordion" data-options="fit:true,border:false">
            <div title="基本功能" data-options="selected:true">
                <div style="margin: 5px">
                    <ul class="tree easyui-tree" data-options="animate:true,lines:true">
                        <li data-options="iconCls:'icon-group'">
                            <span>题库管理</span>
                            <ul>
                                <li data-options="iconCls:'icon-group_add'">
                                    <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="addTab('t0','${pageContext.request.contextPath }/gotoAddItem.action')">增加题目</a>
                                </li>
                                <li >
                                 
                                    <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="addTab('t1','${pageContext.request.contextPath }/itemlist.action')">题目管理</a>
                                </li>
                                
                               
                            </ul>
                        </li>
                        
                         <li data-options="state:'closed',iconCls:'icon-joystick'">
                            <span>试卷管理</span>
                            <ul>
                            	 <li data-options="iconCls:'icon-group_delete'">
                                <!--     <span>手动试卷</span> -->
                                     <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="addTab('t2','${pageContext.request.contextPath }/paperShou.action')">手动生成试卷</a>
                                </li>
                                <li >
                               
                                      <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="addTab('t3','${pageContext.request.contextPath }/paperAuto.action')">自动生成试卷</a>
                                </li>
                            	
                                <li data-options="iconCls:'icon-joystick_add'">
                            
                                    <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="addTab('t6','${pageContext.request.contextPath }/linianPaper.action')">历年试卷查看</a>
                                </li>
                                
                            </ul>
                        </li>
                        
                        <li data-options="state:'closed',iconCls:'icon-joystick'">
                            <span>用户管理</span>
                            <ul>
                                <li data-options="iconCls:'icon-joystick_add'">
                            
                                    <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="addTab('t4','${pageContext.request.contextPath }/gotoUserInfo.action')">个人信息查看</a>
                                </li>
                                 <li data-options="iconCls:'icon-joystick_add'">
                            
                                    <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="addTab('t5','${pageContext.request.contextPath }/EditUserPass.action')">密码修改</a>
                                </li>
                            </ul>
                        </li>
                        
                    </ul>
                </div>
            </div>
         
        </div>
    </div>
     
      <div style="margin-bottom:10px">
      <!--   <a href="#" class="easyui-linkbutton" onclick="addTab('google','http://www.google.com')">google</a> -->
        
       <!--  <a href="#" class="easyui-linkbutton" onclick="addTab('easyui','http://jeasyui.com/')">easyui</a> -->
      
    </div>
  <!--   <div id="tt" class="easyui-tabs" style="width:400px;height:250px;">
        <div title="Home">
        </div>
    </div> -->
     
     
  <!--    <div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'"> -->
     
     <div data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="tools:'#tab-tools'">
            <!-- <div title="主页" data-options="iconCls:'icon-house'" style="padding: 10px; height: 100%;">主页</div>
 -->        </div>
       <!--  <div id="tab-tools">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addPanel()"></a>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="removePanel()"></a>
        </div>
    </div> -->
    
     
     
     <script type="text/javascript">
    

    function addTab(title, url){
        if ($('#tabs').tabs('exists', title)){
            $('#tabs').tabs('select', title);
        } else {
             var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>'; 
            $('#tabs').tabs('add',{
                title:title,
                content:content,
                closable:true
            });
        }
      /*   tabClose(); */
    }
 
 </script>
     
     
     

</body>


</html>
