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
</style>

<div style="position: absolute; width: 350px">
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


<div style="position: absolute; left: 550px; height: 100px; width: 300px">
    <tr id="导航栏" class="nav nav-pills" style="position: absolute; top: 200px">
        <td role="presentation" style="float: left"><a href="enterHomePage">Home</a></td>
        <td role="presentation" style="float: left"><a href="browseBlogs">博客</a></td>
        <td role="presentation" style="float: left"><a href="#">私信</a></td>
        <td role="presentation" style="float: left"><a href="#">广场</a></td>
        <td role="presentation"><a role="button" href="addBlog" class="text-success">写博客</a></td>
        <br>
    </tr>
</div>

<%--    博客标题列表--%>
<div style="position: absolute; left: 550px; height: 100px">
    <ul style="position: absolute; top: 180px; width: 1000px;">
        <c:forEach items="${blogs}" var="blog" varStatus="st">
            <li>
                <tr>
                    <td>
                        <a href="blogDetail?${blog.blog_id}">${blog.title}</a>
                    </td>
                    <td>
                            ${blog.create_time}
                    </td>
                    <td>
<%--                        <a href="delBlog?${blog.blog_id}">删除</a>--%>
                        <button type="button" onclick="delBlog(${blog.blog_id})">删除</button>
                    </td>
                </tr>
            </li>
<%--            <br>--%>
        </c:forEach>
        <br>

        <tr style="position: absolute; top: 700px">
            <td colspan="6" align="center">
                <a href="?start=0">[首  页]</a>
                <a href="?start=${page.pre}">[上一页]</a>
                <a href="?start=${page.next}">[下一页]</a>
                <a href="?start=${page.last}">[末  页]</a>
            </td>
        </tr>

    </ul>
</div>

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

    function delBlog(blog_id) {
        $.ajax({
            type: 'POST',
            url: 'delBlog',
            data: {'blogId': blog_id},
            success: function () {
                console.log("delete blog success")
                window.location.reload();
            },
            error: function () {
                console.log("failed");
            }
        });
    }

    function search() {
        var query = $("#search").val();   // 用户未输入内容则不响应搜索请求
        if(query != null) {
            $.ajax({
                type: 'GET',
                url: 'search',
                data: {'query': query},
                success: function () {
                    console.log("success")
                    window.location.href = "search.jsp";
                },
                error: function () {
                    console.log("failed");
                }
            });
        }
    }

</script>

</html>
