<%@page import="com.qinyuan.cms.metas.Gender"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>我的个人空间</title>

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
				<jsp:include page="/WEB-INF/inc/my_left.jsp"><jsp:param value="profile" name="module"/></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="panel panel-default">
				  <div class="panel-body">
				   <h1> 个人信息设置</h1>
				  </div>
				  <div class="panel-body">
				  	<form action="/my/user/save" method="post" >
				    	<input type="hidden" value="${user.id}" name="id">
				    	<div><br></div>
				    	<p>
				    		昵称：
				    		<input type="text" name="nickname" value="${user.nickname}" class="form-control">
				    	</p>
				    	<p>
				    		性别：
				    		<input type="radio" name="gender" value="<%=Gender.MALE %>" <c:if test="${_LOGIN_USER_.gender=='MALE' }">checked="checked"</c:if>>先生
				    		<input type="radio" name="gender" value="<%=Gender.FAMALE %>" <c:if test="${_LOGIN_USER_.gender=='FAMALE' }">checked="checked"</c:if>>女士
				    	</p>
				    	<p>
				    		星座：
				    		<input type="text" name="xingzuo" value="${user.xingzuo}" class="form-control" >
				    	</p>
				    	<p>
				    		地址：
				    		<input type="text" name="address" value="${user.address}" class="form-control" >
				    	</p>
				    	<p>
				    		座右铭：
				    		<input type="text" name="zuoyouming" value="${user.zuoyouming}" class="form-control" >
				    	</p>
				    	<p>
				    		<button type="submit" class="btn btn-info btn-block">保存</button> 
				    	</p>
				    </form>
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