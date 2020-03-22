<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<%@ taglib prefix="itcast" uri="http://itcast.cn/common/"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>题库管理页面</title>
</head>

<script type="text/javascript">
function del() {
var msg = "您真的确定要删除吗？\n\n请确认！";
if (confirm(msg)==true){
return true;
}else{
return false;
}
}
</script>


<body> 



试卷列表：
<form action="${pageContext.request.contextPath }/addToPaper.action" method="post">
<table width="100%" border=1>
<tr>
	<td><input type="checkbox" name="ids"></td>
	<td>试卷名称</td>
	<td>出卷人</td>
	<td>操作一</td>
	<td>操作二</td>

</tr>
<c:forEach items="${paper }" var="paper">
<tr>
	<td><input type="checkbox" name="ids" value="${paper.pid}"></td>
	<td>${paper.pname }</td>
	<td>${paper.creater }</td>

	
	<td><a href="${pageContext.request.contextPath }/paperExact.action?pid=${paper.pid}" >抽取试卷</a></td>
	<td><a href="${pageContext.request.contextPath }/paperdelete.action?pid=${paper.pid}" >删除试卷</a></td>
	
</tr>
</c:forEach>
	<!-- <td><input type="submit" value="加入手动试卷" ></td> -->
</table>

</form>
</body>

</html>