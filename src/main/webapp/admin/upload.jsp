<%@page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
   <title>上传图片</title>
</head>

<body>
   <div>
     <form method="post" enctype="multipart/form-data">
        <h4>请选择上传的图片</h4>
        <input type="file" name="imageFileName"><br><br>
        <input type="submit" value="提交">
     </form>
   </div>

</body>
</html>