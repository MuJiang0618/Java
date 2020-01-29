<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>幽灵阳光</title>

    <script src="https://how2j.cn/study/jquery.min.js"></script>
    <link href="https://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="https://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head>
<body>

<style>
    #导航栏{
        position: absolute;
        left:400px;
        top: 100px;
    }

    #分类栏{
        position: absolute;
        left:100px;
        top: 300px;
    }

    #头像{
        position: absolute;
        left: 150px;
        top: 100px;
        width: 120px;
        height: 120px;
    }


</style>

<img src="/img/${imgSrc}" width="100px" height="100px">

<ul id="导航栏" class="nav nav-pills">
    <li role="presentation"><a href="#">待办</a></li>
    <li role="presentation"><a href="#">私信</a></li>
    <li role="presentation"><a href="#">广场</a></li>

    <input type="text" class="form-control" onFocus="this.value=''" onblur="this.value='今天想要做点儿什么呢?~'"
           value="" placeholder="今天想要做点儿什么呢?~" style="position: absolute;
           top: 40px; left: 10px; width: 500px; border-color: green">
</ul>

<ul id="分类栏" class="nav nav-pills nav-stacked" style="width:100px">
    <c:forEach items="${userData.blogCategory}" var="category" varStatus="st"  >
        <li role="presentation"><a href="#">${category}</a></li>
    </c:forEach>
</ul>

</body>
</html>
