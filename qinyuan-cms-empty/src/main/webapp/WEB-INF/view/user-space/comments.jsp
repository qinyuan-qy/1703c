<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>我的博客</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/libs/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/cms.css"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    </style>
  </head>
  <body>
    <jsp:include page="/WEB-INF/inc/top.jsp"></jsp:include>
	
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 my_banner">
			</div>
		</div>
	</div>
	<br/>
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="/WEB-INF/inc/my_left.jsp"><jsp:param value="comments" name="module"/></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="panel panel-default">
				  <div class="panel-body">
				    	<h1>我的评论</h1>
				    	<hr/>
				    	<table class="table table-striped table-bordered table-hover">
				    		<thead>
				    			<tr class="info">
				    				<th>评论标题</th>
				    				<th>评论内容</th>
				    				<th>评论的时间</th>
				    				<th>评论的作者</th>
				    				<th>操作</th>
				    			</tr>
				    		</thead>
				    		<tbody class="">
				    		<c:forEach items="${comments}" var="blog">
				    			<tr>
				    				<td>${blog.title}</td>
				    				<td>${blog.content}</td>
				    				<td><fmt:formatDate value="${blog.displayTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				    				<td>${blog.nickname}</td>
				    				<td>
				    					<a class="btn btn-success" onclick="removeBlog(${blog.id});" title="删除评论"><span class="glyphicon glyphicon-remove">删除</span></a>
				    				</td>
				    			</tr>
				    		</c:forEach>
				    		</tbody>
				    	</table>
				    	${pageList}
				  </div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/inc/footer.jsp"/>
	
	<script type="text/javascript">
		
	</script>
  </body>
</html>