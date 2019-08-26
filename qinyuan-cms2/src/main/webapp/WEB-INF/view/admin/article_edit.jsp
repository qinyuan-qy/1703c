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
    <title>发布博客</title>

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
	<br/>
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default">
				  <div class="panel-body">
				    	<hr/>
				    	<form >
				    	<p align="center" class="red"> </p>
				    	<p>
				    		<span>频道</span>
				    		<select id="channel" name="channel.id"  onchange="fenlei()"> </select>
				    		
				    		<span>种类</span>
				    		<select id="category" name="category.id"></select>
				    	</p>
				    	<p>
				    		文章标题：
				    		<input name="id" type="hidden">
				    		<input name="title"  class="form-control" placeholder="文章标题"/>
				    		<span class="red"></span>
				    	</p>
				    	<p>
				    		文章内容：
				    		<textarea name="content" rows="30"  class="form-control" placeholder="文章内容"></textarea>
				    		<span class="red"></span>
				    	</p>
				    	
				    	<p>
				    		文章简介：
				    		<textarea name="summary" rows="3" class="form-control" placeholder="文章简介" ></textarea>
				    		<span class="red"></span>
				    	</p>
				    	
				    	<p>
				    		<button type="button" class="btn btn-info btn-block" onclick="bao()">保存</button> 
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
		$(document).ready(function(){
			$("#content").summernote({
				placeholder:'博客内容',
				height:300
			});
		});
    	function fenlei(){
    		$("#category").html("");
			cid = $("option:selected").val();
			$.post(
    				"/categroyList",
    				{cid:cid},
    				function(msg){
    					for ( var i in msg) {
							$("#category").append("<option value="+msg[i].id+">"+msg[i].name+"</option>")
						}
    					
    					$("[value="+msg.a.categroy_id+"]").attr("selected",true)
    				},
    				"json"
    		)
		}
		 $(function(){
			 aid = ${param.aid}
				$.post(
						"/admin/updatearticle",
						{aid:aid},
						function(msg){
							$("[name='id']").val(msg.a.id)
							$("[name='title']").val(msg.a.title)
							$("[name='content']").html(msg.a.content)
							$("[name='summary']").html(msg.a.summary)
							channel=msg.c;
							
							for ( var i in channel) {
								$("#channel").append("<option value="+channel[i].id+">"+channel[i].name+"</option>")
							}
							$("[value="+msg.a.channel_id+"]").attr("selected",true)
							fenlei();
						},
						"json"
				)
		 })
		 function bao(){
			 $.post(
					"/admin/update",
					$("form").serialize(),
					function(msg){
						if(msg>0){
							alert("修改成功")
							location.href="/admin/articles";
						}else{
							alert("修改失败")
						}
					},
					"json"
			 )
		 }
	</script>
  </body>
</html>