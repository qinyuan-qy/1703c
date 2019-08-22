<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<jsp:include page="/WEB-INF/inc/my_left.jsp"><jsp:param value="cang" name="module"/></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="panel panel-default">
				  <div class="panel-body">
				    	<table class="table table-striped table-bordered table-hover">
				    			<tr class="info">
				    				<th>文章ID</th>
				    				<th>文章标题</th>
				    				<th>文章点击量</th>
				    				<th>是否热门</th>
				    				<th>操作</th>
				    			</tr>
				    		<c:forEach items="${cang}" var="u">
				    			<tr>
				    				<td>${u.id}</td>
				    				<td>${u.title}</td>
				    				<td>${u.hits}</td>
				    				<td>${u.hot }</td>
				    				<td>
				    					<input type="button" value="取消收藏" onclick="quxiao(${u.id})">
				    				</td>
				    			</tr>
				    		</c:forEach>
				    		<tr>
				    			<td colspan="5">
				    				共${pages }页&nbsp;&nbsp;&nbsp;&nbsp;当前${pageNum }/${pages }页     共${total }条数据
				    				<a href="/my/cang?pageNum=1"><input type="button" value="首页"></a>
				    				<a href="/my/cang?pageNum=${pageNum==1?pageNum:pageNum-1 }"><input type="button" value="上一页"></a>
				    				<a href="/my/cang?pageNum=${pageNum==pages?pages:pageNum+1 }"><input type="button" value="下一页"></a>
				    				<a href="/my/cang?pageNum=${pages }"><input type="button" value="尾页"></a>
				    			</td>
				    		</tr>
				    	</table>
				  </div>
				</div>
				
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/inc/footer.jsp"/>
	
	<script type="text/javascript">
		function quxiao(id){
			$.post(
					"/my/delcang",
					{id:id},
					function(msg){
						if(msg>0){
							alert("取消收藏成功")
							location.href="/my/cang";
						}else{
							alert("取消收藏失败")
						}
					},
					"json"
			)
		}
	</script>
  </body>
</html>