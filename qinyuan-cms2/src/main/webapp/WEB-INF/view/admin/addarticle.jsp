<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="howsun">

    <title>CMS后台管理系统</title>

    <!-- Bootstrap core CSS-->
    <link href="/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="/libs/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="/libs/sb-admin/sb-admin.css" rel="stylesheet">

  </head>

  <body id="page-top">

	<!-- 后台管理系统顶部 -->
 	<jsp:include page="_inc_top.jsp"/>

    <div id="wrapper">

 		<!-- 后台管理系统左部菜单 -->
 		<jsp:include page="_inc_left.jsp"/>

      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- Breadcrumbs-->
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="#">后台首页</a>
            </li>
            <li class="breadcrumb-item active">发布文章</li>
          </ol>
			<form>
				<table>
					<tr>
						<td>频道</td>
						<td>
							<select id="channel" name="channel.id"></select>
						</td>
					</tr>
					<tr>
						<td>分类</td>
						<td>
							<select id="category" name="category.id"></select>
						</td>
					</tr>
					<tr>
						<td>文章标题</td>
						<td>
							<input type="text" name="title">
						</td>
					</tr>
					<tr>
						<td>文章简介</td>
						<td>
							<input type="text" name="summary">
						</td>
					</tr>
					<tr>
						<td>文章内容</td>
						<td>
							<input type="text" name="content">
						</td>
					</tr>
					<tr>
						<td>上传图片</td>
						<td>
							<input type="file" name="file">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="发布">
						</td>
					</tr>
				</table>
			</form>
        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright Â© Your Website 2019</span>
            </div>
          </div>
        </footer>

      </div>
      <!-- /.content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="/libs/jquery/jquery.min.js"></script>
    <script src="/libs/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/libs/sb-admin/sb-admin.min.js"></script>
    <script type="text/javascript">
    
    	$(function(){
    		$.post(
    		
    				"channelList",
    				{},
    				function(msg){
    					for ( var i in msg) {
							
						}
    				},
    				"json"
    		)
    	})
    
    
    
    </script>
  </body>

</html>
