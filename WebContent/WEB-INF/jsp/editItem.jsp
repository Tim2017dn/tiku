<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改题目内容</title>

</head>

<body> 
<!-- <input type="button" onclick="myFunction()" value="显示警告框"> -->
	<!-- 上传图片是需要指定属性 enctype="multipart/form-data" -->
	<!-- <form id="itemForm" action="" method="post" enctype="multipart/form-data"> -->
	<form id="itemForm"	> <!-- action="${pageContext.request.contextPath }/updateitem.action" method="post" -->
		<input type="hidden" id="id" value="${item.id }" /> 修改题目内容：
		<table width="100%" border=1>
			<tr>
				<td>问题</td>
			<%-- 	<td><input type="text" id="question"  value="${item.question }" /></td> --%>
				<td><textarea rows="5" cols="30" id="question" >${item.question }</textarea></td>
			</tr>
			<tr>
				<td>答案</td>
			<%-- 	<td><input type="text" id="answer"  value="${item.answer }" /></td> --%>
				<td><textarea rows="5" cols="30" id="answer" >${item.answer }</textarea></td>
			</tr>
			<tr>
			<td>题型</td>
			<td>
				<select	id="type" >
					<option  value='判断题' <c:if test="${item.type=='判断题'}"> selected="selected"</c:if>>判断题</option>
					<option value='填空题' <c:if test="${item.type=='填空题'}"> selected="selected"</c:if>>填空题</option>
					<option value='简答题' <c:if test="${item.type=='简答题'}"> selected="selected"</c:if>>简答题</option> 
				</select>
			</td>
			</tr>
			<tr>
				<td>难度</td>
				<td><input type="text" id="dif"  value="${item.dif}" />
				</td>
			</tr>
			
			
			<tr>
			<td>学科</td>
			<td>
				<select	id="subject" name="subject">
					<option  value='数据库' <c:if test="${item.subject=='数据库'}"> selected="selected"</c:if>>数据库</option>
					<option value='java' <c:if test="${item.subject=='java'}"> selected="selected"</c:if>>java</option>
					<option value='计算机网络' <c:if test="${item.subject=='计算机网络'}"> selected="selected"</c:if>>计算机网络</option> 
				</select>
			</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"  onclick="edit()"  value="提交" />
				</td>
			</tr>
		</table>

	</form>
	<a href="<%=basePath%>itemlist.action">返回</a>
	 <script src="<%=basePath%>js/jquery.min.js"></script>
	<script>
function edit()
{
	var id=$("#id").val();
   var question=$("#question").val();
   var answer=$("#answer").val();
   var dif=$("#dif").val();
   var type=$("#type option:selected").val();
   var subject=$("#subject option:selected").val();
   //  $("#s").val();

          // $("#s option:selected").val();
   
   
   if(question==""){
       alert("问题不能为空！");
       return false;
   }
   else if(answer==""){
       alert("答案不能为空！");
       return false;
   }
   else if(dif==""){
       alert("难度不能为空！");
       return false;
   }
   else{
	 
	   $.ajax({
			type:"get",
			url:"<%=basePath%>updateitem.action",
			data:{"id":id,"question":question,"answer":answer,"type":type,"dif":dif,"subject":subject},
			async:false,
			success:function(data) {
				
				alert("修改成功");
				 $.ajax({
					 type:"get",
					url:"<%=basePath%>itemEdit.action",
					data:{"id":id}
				 });
				
			}
		});
   }
}
</script>
	
</body>

</html>