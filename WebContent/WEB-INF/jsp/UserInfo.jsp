<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="userForm"	action="${pageContext.request.contextPath }/updateUser.action" method="post">
		<input type="hidden" name="id" value="${user.id }" /> 个人信息：
		<table width="100%" border=1>
			<tr>
				<td>登录名</td>
				<td><input type="text" name="username" readonly="readonly" value="${user.username }" /></td>
			</tr>
			<tr>
				<td>真实名</td>
				<td><input type="text" name="realname" readonly="readonly" value="${user.realname }" /></td>
			</tr>
			<tr>
				<td>所教学科</td>
				<td><input type="text" name="subject" value="${user.subject}" /></td>
			</tr>
			<td>职称</td>
			<td>
				<input type="text" name="title" value="${user.title}" /></td>
			</td>
			<tr>
				
			</tr>
			
			
		</table>

	</form>
</body>
</html>