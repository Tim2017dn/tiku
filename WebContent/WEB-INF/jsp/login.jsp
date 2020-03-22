  <%@ page language="java" contentType="text/html;  charset=UTF-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.6/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.7.6/themes/icon.css">
	
	<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.7.6/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.7.6/jquery.easyui.min.js"></script>

	
	
<title>Insert title here</title>
</head>
<body>
 	<div id="win" class="easyui-window" title="Login" style="width:300px;height:180px;"><!--  class="easyui-window" -->
	<form  name="loginForm"   style="padding:10px 20px 10px 40px;"> <%-- action="${pageContext.request.contextPath }/UserLogin.action"  --%>
		<p>Name: <input type="text" id="username" name="username"></p>
		<p>Pass: <input type="password" id="password" name="password"></p>
		<div style="padding:5px;text-align:center;">
		<input class="layui-btn layui-btn-normal" type="button"  onclick="requestJson()"  value="提交" />
		<input class="layui-btn layui-btn-normal" type="button"  onclick="register()"  value="注册" />
		</div>
	</form>
 </div> 
 
<!--  <p>Name: <input type="text" id="username" name="username"></p>
<p>Pass: <input type="password" id="password" name="password"></p>
 	<input type="submit"  onclick="requestJson()"  value="提交" />
 	 -->
 	
 	
 <script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript">
		function requestJson(){
        var name =$("#username").val();//#是id选择器
            var pwd =$("#password").val();
            if(name==""){
                alert("用户名不能为空！");
                return false;
            }
            else if(pwd==""){
                alert("密码不能为空！");
                return false;
            }
            else
            {
            	$.ajax({
    				type:"post",
    				url:"<%=basePath%>checklogin.action",
    				data:{"loginname":name,"password":pwd},
    				async:false,
    				success:function(data) {
    					if(data){
    					window.location.href="<%=basePath%>UserLogin.action";
    					}else{
    						alert("用户名或密码错误");
    						
    					}
    				}
    			});
           }
            
       }
		
		function register(){
			window.location.href="<%=basePath%>gotoReg.action";
		}
   </script>
</body>
</html>