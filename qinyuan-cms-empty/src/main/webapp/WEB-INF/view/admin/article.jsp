<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap-theme.min.css">
<script src="<%=request.getContextPath() %>/bootstrap/js/jquery-1.8.3.min.js"></script>
<script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div><br></div>
<div><br></div>
<div><h1>后台专题管理</h1></div>
<div><br></div>
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr class="info">
				<th>专题</th>
				<th>文章数量</th>
				<th>操作</th>
				</tr>
		</thead>
		<tbody class="">
			<c:forEach items="${blogs}" var="blog">
				<tr id="item_${blog.id}">
				   	<td>${blog.title}</td>
				    <td>${blog.category.name}</td>
				    <td>${blog.hits}</td>
				    <td><fmt:formatDate value="${blog.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				   	<td><a class="btn btn-primary" href="/my/blog/edit?id=${blog.id}" title="编辑"><span class="glyphicon glyphicon-edit">编辑</span></a>&nbsp;&nbsp;
				   	<a class="btn btn-success" onclick="removeBlog(${blog.id});" title="删除"><span class="glyphicon glyphicon-remove">删除</span></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>