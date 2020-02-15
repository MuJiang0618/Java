<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>

<title>幽灵阳光</title>

<script src="https://how2j.cn/study/jquery.min.js"></script>
<link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<form action="uploadProfileImg" method="post" enctype="multipart/form-data">
    选择图片:<input type="file" name="imgFile" accept="image/*" /> <br>
    <input type="submit" class="btn btn-info" value="上传头像">
</form>