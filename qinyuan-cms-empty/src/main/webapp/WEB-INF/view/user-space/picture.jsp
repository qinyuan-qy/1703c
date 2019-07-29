<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>发布图片</title>
	<script type="text/javascript" src="/libs/jquery/jquery.min.js"></script>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/libs/bootstrap/css/bootstrap.min.css"/>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
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
				<jsp:include page="/WEB-INF/inc/my_left.jsp"><jsp:param value="picture" name="module"/></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="panel panel-default">
				  <div class="panel-body">
				    	<h1>发布图片</h1>
				    	<hr/>
				    	
				    	<form action="/my/blog/picture" enctype="multipart/form-data" method="post" >
				    	<p align="center" class="red"> </p>
				    	<p>
				    		博客标题:
				    		<input id="title" name="title" style=""  class="form-control" placeholder="博客标题">
				    		<button class="btn btn-primary" id="jiacu" type="button">加粗</button>
				    		<button class="btn btn-primary" id="xieti" type="button">斜体</button>
				    		<button class="btn btn-primary" id="bianhong" type="button">变红</button>
				    		<input id="style" name="style" type="hidden"> 
				    		<span class="red"></span>
				    	</p>
				    		<a id="btn" class="btn btn-primary" role="button">添加图片</a><br>
				    		上传图片：
				    	<p id="stu">
				    		
				    	</p>
				    	<p>
				    		上传封面：<input type="file" name="phtno"/>
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
	
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
	<script type="text/javascript">
	
	
		$("#btn").click(function(){
			$("#stu").append("<br><input type='file' name='file'><br><input type='text' name='describe' class='form-control'>");
		})
	//font-weight: bolder;
		 $("#jiacu").click(function(){
			var style = $("#title").attr("style");
			if(style.indexOf("font-weight: bolder;")!=-1){
				var str = style.replace("font-weight: bolder;","");
				$("#title").attr("style",str);
				$("#style").val($("#title").attr("style"));
			}else{
				$("#title").attr("style",style+"font-weight: bolder;");
				$("#style").val($("#title").attr("style"));
			}
		}) 
		//font-style: italic;
		 $("#xieti").click(function(){
			var style = $("#title").attr("style");
			if(style.indexOf("font-style: italic;")!=-1){
				var str = style.replace("font-style: italic;","");
				$("#title").attr("style",str);
				$("#style").val($("#title").attr("style"));
			}else{
				$("#title").attr("style",style+"font-style: italic;");
				$("#style").val($("#title").attr("style"));
			}
		}) 
		//color: red;
		 $("#bianhong").click(function(){
			var style = $("#title").attr("style");
			if(style.indexOf("color: red;")!=-1){
				var str = style.replace("color: red;","");
				$("#title").attr("style",str);
				$("#style").val($("#title").attr("style"));
			}else{
				$("#title").attr("style",style+"color: red;");
				$("#style").val($("#title").attr("style"));
			}
		}) 
		
	</script>
</body>
</html>