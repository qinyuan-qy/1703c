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
	
		function login(){
			$.post(
				"login.do",
				$("form").serialize(),
				function(msg){
					if(msg>0){
						alert("登陆成功")
						location.href="list.do";
					}else{
						alert("输入错误")
					}
				},
				"text"
			)
		}
	
	</script>


</head>
<body>	
<form>
	<table>
		<tr>
			<td>用户名</td>
			<td>
				<input type="text" name="uname">
			</td>
		</tr>
		
		<tr>
			<td>密码</td>
			<td>
				<input type="text" name="upwd">
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" value="登陆" onclick="login()">
			</td>
		</tr>
	</table>
</form>
</body>
</html>