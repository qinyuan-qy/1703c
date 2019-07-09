<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/index3.css"  type="text/css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.8.3.min.js"></script>

	<script type="text/javascript">
	
		$(function(){
			$("#xuan").toggle(
				function(){
					
					$("[name='checked']").attr("checked",true);
				},function(){
					$("[name='checked']").attr("checked",false);
				}		
			)
		})
	
		function shang(mid){
			$.post(
				"shangjia.do",
				{mid:mid},
				function(msg){
					if(msg>0){
						alert("上架成功")
						location.href="list.do";
					}
				}
			)
		}
		function xia(mid){
			$.post(
					"xiajia.do",
					{mid:mid},
					function(msg){
						if(msg>0){
							alert("下架成功")
							location.href="list.do";
						}
					}
				)
		}
	</script>



</head>
<body>	
	<table>
		<tr>
			<td>
				<input type="checkbox" id="xuan">
			</td>
			<td>电影ID</td>
			<td>电影名称</td>
			<td>导演</td>
			<td>票价</td>
			<td>上映时间</td>
			<td>电影类型</td>
			<td>状态</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${list }" var="u">
			<tr>
				<td>
					<input type="checkbox"  name="checked"  value="${u.mid }">
				</td>
				<td>${u.mid }</td>
				<td>${u.mname }</td>
				<td>${u.author }</td>
				<td>${u.price }</td>
				<td>${u.mdate }</td>
				<td>${u.mtype }</td>
				<td>
					<c:if test="${u.mstatus == 0 }">
						正在热映
					</c:if>
					<c:if test="${u.mstatus == 1 }">
						已经下架
					</c:if>
				</td>
				<td>
					<input type="button" value="详情">
					<input type="button" value="修改">
					<c:if test="${u.mstatus == 0 }">
						<input type="button" value="下架" onclick="xia(${u.mid})">
					</c:if>
					<c:if test="${u.mstatus == 1 }">
						<input type="button" value="上架" onclick="shang(${u.mid})">
					</c:if>
				</td>
			</tr>
		</c:forEach>
		 <tr>
			<td colspan="9">
				<a href="list.do?pageNum=1"><input type="button" value="首页"></a>
				<a href="list.do?pageNum=${pageNum==1?pageNum:pageNum-1}"><input type="button" value="上一页"></a>
				<a href="list.do?pageNum=${pageNum==zongye?zongye:pageNum+1 }"><input type="button" value="下一页"></a>
				<a href="list.do?pageNum=${zongye }"><input type="button" value="尾页"></a>
			</td>
		</tr>
	</table>
</body>
</html>