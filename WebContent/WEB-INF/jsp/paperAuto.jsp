<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自动生成试卷</title>

</head>

<body> 

	<form id="itemForm"	action="${pageContext.request.contextPath }/paperQuest.action" method="post">
		<table width="100%" border=1>
			<!-- <td>试卷标题</td>
				<td><input type="text" name="title"/></td>
			
			<tr> -->
			<tr>
				<td>
				<label >学科</label></td>
				<td>
				<select	class="form-control" id="subject"  name="subject">
					<option value="">--请选择--</option>
						<option value='数据库'>数据库</option>
						<option value='计算机网络'>计算机网络</option>
					
				</select>
				</td>
			</tr>
			<tr>
				<td>填空题</td>
				<td><input type="text" name="tian"/></td>
			</tr>
			<tr>
				<td>判断题</td>
				<td><input type="text" name="pan" /></td>
			</tr>
		
			<tr>
				<td>简答题</td>
				<td><input type="text" name="jian" /></td>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"  value="提交" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>