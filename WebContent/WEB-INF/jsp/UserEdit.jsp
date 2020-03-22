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
<form id="userForm"	><%-- action="${pageContext.request.contextPath }/updateUser.action"  --%>
		<input type="hidden" name="id" value="${user.id }" /> 个人信息：
		<table width="100%" border=1>
			<tr>
				<td>登录名</td>
				<td><input type="text" id="username" readonly="readonly" value="${user.username }" /></td>
			</tr>
			<tr>
				<td>原来密码</td>
				<td><input type="password" id="oldpass" /></td>
			</tr>
			<tr>
			<td>现在密码</td>
			<td>
				<input type="password" id="newpass" /></td>
			</td>
			<tr>
				<td>再输一遍密码</td>
			<td>
				<input type="password" id="newpass1"/></td>
			</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"  onclick="editInfo()"  value="提交" />
				</td>
			</tr>
			
		</table>

	</form>
	
	<script src="<%=basePath%>js/jquery.min.js"></script>
<script>
function editInfo()
{
	var oldpass =$("#oldpass").val();
	var newpass =$("#newpass").val();//#是id选择器
    var newpass1 =$("#newpass1").val();    
	if(oldpass==""){
		alert("请填写原来密码");
    	return false;
	}
	else if(newpass==""){
		alert("请填写新密码");
    	return false;
	}
	else if(newpass1==""){
		alert("请填写确认新密码");
    	return false;
	}
	else if(newpass!=newpass1){
    	alert("新密码两次输入不一致");
    	return false;
    }else{
    	$.ajax({
    		type:"post",
    		url:"<%=basePath%>updateUser.action",
    		async:false,
    		data:{"oldpass":oldpass,"newpass":newpass},
    		success:function(data){
    			if(data){alert("修改成功");}
    			else{alert("旧密码有误");}
    			
    		}
    	});
    }
}
</script>
	
</body>
</html>