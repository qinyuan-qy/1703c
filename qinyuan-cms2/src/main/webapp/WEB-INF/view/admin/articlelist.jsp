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
            <li class="breadcrumb-item active">查看文章详情</li>
          </ol>
         		 <%-- <form action="/admin/articles" method="post">
         			<input type="hidden" name="currentpage">
         			标题：<input type="text" name="title" value="${title }">
         			<input type="submit" value="搜索">
         		</form> --%>
         		<!-- <input type="button" value="发布文章" onclick="fabu()"> -->
			<!-- 表格 -->
			<table class="table table-striped table-bordered table-hover">
				<tr class="info">
				   	<th>文章编号</th>
				    <th>文章标题</th>
				    <th>点击量</th>
				    <th>是否热门</th>
				    <th>是否审核</th>
				   <!--  <th>操作</th> -->
				</tr>
					<tr class="info">
					    <th>${articles.id }</th>
					    <th>${articles.title}</th>
					    <th>${articles.hits }</th>
					    <th>${articles.hot }</th>
					    <th>
					    	<c:if test="${articles.status ==1 }">
					    		已审核
					    	</c:if>
					    	<c:if test="${articles.status !=1 }">
					    		未审核
					    	</c:if>
					    </th>
					    <%--  <th>
					     	<input type="button" value="删除" onclick="del(${u.id})">
					     	<input type="button" value="审核" onclick="shenhe(${u.id})">
					     	<input type="button" value="收藏" onclick="shoucang(${u.id})">
					     	<input type="button" value="查看" onclick="chakan(${u.id})">
					     </th> --%>
					</tr>
         	</table>
         	<%-- ${page } --%>

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
    
    	function page(i){
    		$("[name=currentpage]").val(i);
    		$("form").submit();
    	}
    	
    </script>
  </body>

</html>
