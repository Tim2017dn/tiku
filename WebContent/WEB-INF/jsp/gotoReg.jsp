<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
用户注册页面
<form id="userForm"	action="${pageContext.request.contextPath }/RegUser.action" method="post">
		
		<table width="100%" border=1>
			<tr>
				<td>登录名</td>
				<td><input type="text" name="username"   /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="password"   /></td>
			</tr>
			<tr>
				<td>真实名</td>
				<td><input type="text" name="realname" /></td>
			</tr>
			<tr>
				<td>所教学科</td>
				<td><input type="text" name="subject"  /></td>
			</tr>
			<tr>
				<td>职称</td>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="注册" onclick="add()" />
			
				
					<a href="${pageContext.request.contextPath }/LoginJsp.action" >放回</a></td>
			</tr>
		</table>

	</form>
	
	
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript">
	function add()
	{
		alert("注册成功");
	}
	</script>
</body>
</html>