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

function add(){
	alert("已添加");
}

function checkAll(){
	for(i=0;i<document.paperitemlist.ids.length;i++){
		 document.paperitemlist.ids[i].checked = true;
	}
}

</script>


<body>
试题列表：
<form name="paperitemlist" action="${pageContext.request.contextPath }/saveAsPaper.action?ids=${ids}" method="post">  <%-- action="${pageContext.request.contextPath }/paperListDeleteMuti.action" --%>
<table width="100%" border=1>
<tr>
	试卷名称：<input type="text" name="title" value="${title}" />
</tr>

<tr>
	<td><input type="checkbox" name="quanxuan" onclick="javascript:return checkAll();">全选</td>
	<td>题目</td>
	<td>答案</td>
	<td width="60px">题型</td>
	<td width="20px">难度</td>
	<td width="60px">学科</td>
	<td width="35px">操作一</td>

</tr>
<c:forEach items="${item }" var="item">
<tr>
	<td><input type="checkbox" name="ids" value="${item.id}" ></td>
	<td>${item.question }</td>
	<td>${item.answer }</td>
	<td>${item.type }</td>
	<td>${item.dif }</td>
	<td>${item.subject }</td>
	<td><a href="${pageContext.request.contextPath }/paperDelete.action?id=${item.id}" onclick="javascript:return del();">删除</a></td>
</tr>
</c:forEach>
<tr>
<td><input type="button" value="试卷保存到题库" onclick="checkAll();paperitemlist.action='${pageContext.request.contextPath }/saveAsPaper.action?ids=${ids}';save();paperitemlist.submit();"></td>
<td><input type="button" value="生成word文档" onclick="checkAll();paperitemlist.action='${pageContext.request.contextPath }/generatePaperWord.action?ids=${ids}';paperitemlist.submit();"></td>
<td><input type="button" value="加入到手动试卷" onclick="paperitemlist.action='${pageContext.request.contextPath }/addToPaper2.action?ids=${ids}';add();paperitemlist.submit();"></td>
</tr>
</table>
</form>

<!-- form1.action='action_1';form1.submit(); -->

	<!-- /.panel-body -->
</div>



</body>

<!-- <script type="text/javascript">
	function MutiDel(){
		document.paperitemlist.action="${pageContext.request.contextPath }/paperListDeleteMuti.action"
			document.paperitemlist.submit();
	}



</script> -->
</html>