<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<c:set var="appName" value="幽灵阳光" />
<html>
<head>
    <title>${appName}</title>

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

<div style="position: absolute; width: 25%">
    <a href="Logout">注销</a>
    <img src="/img/profile_img/${userData.imgSrc}" width="100px" height="100px" class="img-circle" style="position: absolute; left: 140px; top: 120px">
    <a href="enterUploadProfileImg" style="position: absolute; left: 155px; top: 230px">上传头像</a>
    <br>
    <br>

    <dl id="分类栏" class="nav nav-pills nav-stacked" style="width:180px; left: 140px">
        <dt><button>Add</button></dt>
        <c:forEach items="${userData.blogCategory}" var="category" varStatus="st">
            <li role="presentation"><a href="#">${category}</a></li>
        </c:forEach>
    </dl>
</div>

<ul id="导航栏" class="nav nav-pills">
    <li role="presentation"><a href="#">Home</a></li>
    <li role="presentation"><a href="browseBlogs">博客</a></li>
    <li role="presentation"><a href="#">私信</a></li>
    <li role="presentation"><a href="#">广场</a></li>
    <li role="presentation"><a>    </a></li>
    <li role="presentation"><a>    </a></li>
    <li role="presentation"><a role="button" href="addBlog">写博客</a></li>

    <input type="text" id="addToDo" class="form-control"
           value="" placeholder="今天想要做点儿什么呢?~" style="position: absolute;
           top: 40px; left: 10px; width: 500px; border-color: green">

    <button type="button" onclick="addToDo()" style="position: absolute; top: 40px; left: 530px">添加</button>

    <ul style="position: absolute; top: 90px;">
        <c:forEach items="${userData.checklist}" var="thing" varStatus="st">
            <li role="listitem"><input type="text" class="list-group-item list-group-item-success" value= ${thing}></li>
            <br>
        </c:forEach>
    </ul>
</ul>

<div style="position: absolute; width: 350px; left: 1100px; top: 100px">
    <form action="search" method="post">
        <input type="text" placeholder="想寻找什么?" class="list-group-item list-group-item-success" id="search" name="query">
        <button type="submit" class="btn btn-primary" >搜索</button>
    </form>
</div>

</body>

<script>
    function addToDo() {
        var thingToDo = $("#addToDo").val();
        if(thingToDo != null) {
            $.ajax({
                type: 'POST',
                url: 'addToDo',
                data: {'thing': thingToDo},
                success: function () {
                    console.log("success")
                    window.location.reload();
                },
                error: function () {
                    console.log("failed");
                }
            });
        }
    }

    function search() {
        var query = $("#search").val();
        if(query != null) {
            $.ajax({
                type: 'GET',
                url: 'search',
                data: {'query': query},
                success: function () {
                    console.log("success")
                    window.location.href = "";
                },
                error: function () {
                    console.log("failed");
                }
            });
        }
    }

</script>

</html>
