<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<link rel="stylesheet" type="text/css"
	href="front/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="front/css/nav.css">
<link rel="stylesheet" type="text/css" href="front/css/upload_pic.css">

</head>
<body>

	<div id="divnav">
		<ul id="navleft">
			<li class="navitem"><a href="front/index.html">网站名</a></li>
			<li class="navitem"><a href="front/my.html">我的Website</a></li>
			<li class="navitem"><a href="front/order.html">我的订单</a></li>
			<li class="navitem"><a href="front/collect.html">收藏夹</a></li>
			<li class="navitem"><a href="front/cart.html">购物车</a></li>
			<li class="navitem"><a href="front/reg_store.html">免费开店</a></li>
		</ul>
		<ul id="navright">
			<li class="navitem"><a href="front/login.html">登录</a></li>
			<li class="navitem"><a href="front/register.html">注册</a></li>
		</ul>
	</div>

	<div class="container">
		<div style="height: 40px;"></div>
		<div class="row">
			<div class="col-xs-4" id="divupload">
				<h4>请选择要作为商品封面的图片</h4>
				<form id="uploadForm"
					action="${pageContext.request.contextPath }/file?method=upload"
					method="POST" enctype="multipart/form-data">
					<input type="file" name="file1" class="form-control">
				</form>
				<button id="upload">上传</button>
			</div>
			<div class="col-xs-8" style="min-height: 300px;">
				<h4>预览</h4>
				<div class="row">
					<div class="col-xs-4">
						<img id="img_small" style="width: 240px;" src="front/img/logo.png">
					</div>
					<div class="col-xs-8">
						<img id="img_big" style="width: 480px;" src="front/img/logo.png">
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-2">
				<button id="btn_confirm">确认</button>
			</div>
		</div>
	</div>


	<script type="text/javascript" src="front/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="front/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="front/js/variables.js"></script>

	<script type="text/javascript">
		window.onload = function() {
			var id = location.search.split("=")[1];
			var btnSubmit = document.getElementById("upload");
			var img_big = document.getElementById("img_big");
			var img_small = document.getElementById("img_small");
			var pic;
			btnSubmit.onclick = function() {
				var data = new FormData(document.forms.namedItem("uploadForm"));
				var req = new XMLHttpRequest();
				req.open("POST", "/javaweb/file?method=upload", true);
				req.onload = function(oEvent) {
					if (req.status == 200) {
						pic = req.responseText;
						img_big.src = javaweb.base + "/file?method=retrive&img=" + req.responseText;
						img_small.src = javaweb.base + "/file?method=retrive&img=" + req.responseText;
					} else {
						alert("failed");
					}
				};
				req.send(data);
			};
			
			var btnConfirm = document.getElementById("btn_confirm");
			btnConfirm.onclick = function(){
				var ap = javaweb.createAP("/productJson?method=connectPic&pic=" + pic + "&product=" + id);
				ap.success = function(json){
					alert(json.message);
				};
				$.ajax(ap);
			};
		};
	</script>

</body>
</html>