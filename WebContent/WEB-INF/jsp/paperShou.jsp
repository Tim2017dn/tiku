<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<%@ taglib prefix="itcast" uri="http://itcast.cn/common/"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手动试卷页面</title>
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

function save(){
	alert("已保存到题库");
}

function checkAll(){
	if (document.shou.quanxuan.checked==true){
		for(i=0;i<document.shou.ids.length;i++){
			 document.shou.ids[i].checked = true;
		}
	}else{
		for(i=0;i<document.shou.ids.length;i++){
			 document.shou.ids[i].checked = false;
		}
	}
}

</script>

<body>
手动试卷列表：
<form name="shou" method="post">
<table width="100%" border=1>
<tr>
	<td>试卷名称：<input type="text" name="title" /></td>
	<td><a href="${pageContext.request.contextPath }/paperShou.action">刷新</a></td>
	
</tr>

<tr>
	<td><input type="checkbox" name="quanxuan" onclick="javascript:return checkAll();">全选</td>
	<td>题目</td>
	<td>答案</td>
	<td>题型</td>
	<td>难度</td>
	<td>操作一</td>

</tr>
<c:forEach items="${page.rows }" var="row">
<tr>
	<td><input type="checkbox" name="ids" value="${row.id}"></td>
	<td>${row.question }</td>
	<td>${row.answer }</td>
	<td>${row.type }</td>
	<td>${row.dif }</td>
	
	<td><a href="${pageContext.request.contextPath }/paperDelete.action?id=${row.id}" onclick="javascript:return del();">删除</a></td>
</tr>
</c:forEach>
<tr>
<td><input type="button" value="批量删除" onclick="shou.action='${pageContext.request.contextPath }/paperDeleteMuti.action';shou.submit(); javascript:return del();"></td>
<td><input type="button" value="生成word文档" onclick="shou.action='${pageContext.request.contextPath }/generatePaperShou.action';shou.submit();"></td>
<td><input type="button" value="试卷保存到题库" onclick="shou.action='${pageContext.request.contextPath }/savePaperShou.action';shou.submit();javascript:return save(); "></td>
<td><input type="button"  onclick="shou.action='${pageContext.request.contextPath }/paperShouClean.action';shou.submit();" value="清除全部"></td>
</tr>
<tr>

</tr>
</table>
</form>


<div class="col-md-12 text-right">
		<itcast:page url="${pageContext.request.contextPath }/itemlist.action" />
	<!-- /.panel-body -->
</div>
<a href="${pageContext.request.contextPath }/itemlist.action">返回</a>
</body>


</html>