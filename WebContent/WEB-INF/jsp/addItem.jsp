<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加题目</title>

</head>
<script>
function addInfo()
{
    alert("已保存");
}
</script>
<body> 
<!-- <input type="button" onclick="myFunction()" value="显示警告框"> -->
	<!-- 上传图片是需要指定属性 enctype="multipart/form-data" -->
	<!-- <form id="itemForm" action="" method="post" enctype="multipart/form-data"> -->
	<form id="itemForm"	action="${pageContext.request.contextPath }/additem.action" method="post">
		 题目信息：
		<table width="100%" border=1>
			<tr>
				<td>问题</td>
				<!-- <td><input type=""  name="question"  /></td> -->
				<td><textarea rows="5" cols="30" name="question"></textarea></td>
			</tr>
			<tr>
				<td>答案</td>
	<!-- 			<td><input type="text" name="answer"/></td> -->
					<td><textarea rows="10" cols="30" name="answer"></textarea></td>
			</tr>
			<td>题型</td>
			<td>
				<select	id="type" name="type">
					<option  value='判断题' >判断题</option>
					<option value='填空题' >填空题</option>
					<option value='简答题'>简答题</option> 
				</select>
			</td>
			
		
			<tr>
				<td>难度</td>
				<td><input type="text"  name="dif" />
				</td>
			</tr>
				<td>学科</td>
			
			<td>
				<select	id="subject" name="subject">
					<option  value='数据库' >数据库</option>
					<option value='java' >java</option>
					<option value='计算机网络'>计算机网络</option> 
				</select>
			</td>
			<tr>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit"  onclick="addInfo()"  value="提交" />
				</td>
			</tr>
		</table>

	</form>
</body>

</html>