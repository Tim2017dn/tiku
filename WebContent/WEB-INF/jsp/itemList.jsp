<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
   <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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


<body onload="cancel()"> 
<form action="${pageContext.request.contextPath }/itemlist.action" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td>
<label for="custIndustry">题型</label> 
	<select	class="form-control" id="type"  name="type">
		<option value="">--请选择--</option>
			<!-- <option value='判断题'>判断题</option>
			<option value='填空题'>填空题</option>
			<option value='简答题'>简答题</option> -->
			<option  value='判断题' <c:if test="${type=='判断题'}"> selected="selected"</c:if>>判断题</option>
			<option value='填空题' <c:if test="${type=='填空题'}"> selected="selected"</c:if>>填空题</option>
			<option value='简答题' <c:if test="${type=='简答题'}"> selected="selected"</c:if>>简答题</option> 
	</select>
</td>
<td>
	<label >题目关键字</label>
	<input type="text" id="keyWord" name="keyWord" value="${keyWord} " />
</td>
<td>
	<label >学科</label>
	<select	class="form-control" id="subject"  name="subject">
		<option value="">--请选择--</option>
			<option value='数据库'  <c:if test="${subject=='数据库'}"> selected="selected"</c:if>>数据库</option>
		<!-- 	<option value='计算机网络'>计算机网络</option> -->
		<option value='计算机网络'  <c:if test="${subject=='计算机网络'}"> selected="selected"</c:if>>计算机网络</option>
		
	</select>
</td>
<td><input type="submit" value="查询"/></td>
</tr>
</table>
</form>


题库列表：
<form name="itemlist">  <!-- action="${pageContext.request.contextPath }/addToPaper.action?page=${page.page}" method="post"  -->
<table width="100%" border=1>
<tr>
	<td><input type="checkbox" name="ids"></td>
	<td>题目</td>
	<td>答案</td>
	<td width="50px">题型</td>
	<td width="30px">难度</td>
	<td width="50px">学科</td>
	<td width="40px">操作一</td>
	<td width="40px">操作二</td>

</tr>
<c:forEach items="${page.rows }" var="row">
<tr>
	<td><input type="checkbox" id="ids" name="ids" value="${row.id}"></td>
	<td>${row.question }</td>
	<td>${row.answer }</td>
	<td>${row.type }</td>
	<td>${row.dif }</td>
	<td>${row.subject }</td>
	<td><a href="${pageContext.request.contextPath }/itemEdit.action?id=${row.id}">修改</a></td>
	<td><a href="${pageContext.request.contextPath }/itemDelete.action?id=${row.id}" onclick="javascript:return del();">删除</a></td>
	
</tr>
</c:forEach>
	<td><input type="button" onclick="addto()" value="加入手动试卷" ></td>

</table>
<div class="col-md-12 text-right">
		<itcast:page url="${pageContext.request.contextPath }/itemlist.action" />	
	</div>
	<!-- /.panel-body -->
</div>
</form>


 <script src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript">
function addto(){
	  var type =$("#type").val();//#是id选择器
	  var keyWord =$("#keyWord").val();//#是id选择器
	  var subject =$("#subject").val();//#是id选择器
	  var page=${page.page};
	  
	  
	  var ids=new Array();
	  var count=0;
	  for(i=0;i<document.itemlist.ids.length;i++){	 
		  if(document.itemlist.ids[i].checked==true){
			  ids[count]=document.itemlist.ids[i].value;
				count++;	
		  } 
		}

	
	  $.ajax({
			type:"post",
			url:"<%=basePath%>addToPaper.action",
			data:{"page":page,"type":type,"keyWord":keyWord,"subject":subject,"sids":JSON.stringify(ids)},
			async:false,
			success:function(data) {
				cancel();
				alert("已添加");
			
			}
		});
}


function cancel(){
	for(i=0;i<document.itemlist.ids.length;i++){	 
		  document.itemlist.ids[i].checked=false;	
		}
}
</script>
</body>

</html>