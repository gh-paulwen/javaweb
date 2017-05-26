<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload</title>

</head>
<body>
<form id="uploadForm" action="${pageContext.request.contextPath }/file?method=upload" method="POST" enctype="multipart/form-data">
	<input type="file" name="file1">
</form>
<button id="upload" >Submit</button><br>
<img style="width:300px;height:200px" id="img" src="">

<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js"></script>
<script type="text/javascript">
	window.onload = function(){
		var btnSubmit = document.getElementById("upload");
		btnSubmit.onclick = function(){
			var data = new FormData(document.forms.namedItem("uploadForm"));
			var req = new XMLHttpRequest();
			req.open("POST","/javaweb/file?method=upload",true);
			req.onload = function(oEvent){
				if(req.status == 200){
					img.src = "/javaweb/file?method=retrive&img=" + req.responseText;
				}else {
					alert("failed");
				}
			};
			req.send(data);
		};
	};
	
	/*
	$(document).ready(function(){
		$("#upload").click(function(){
			$.ajax({
				url:"/javaweb/file?method=upload",
				type:"POST",
				data:document.forms.namedItem("uploadForm"),
				success:function(text){
					$("#img").attr("src","/javaweb/file?method=retrive&img=" + req.responseText);
				}
			});
		});
	});
	*/
</script>
</body>
</html>