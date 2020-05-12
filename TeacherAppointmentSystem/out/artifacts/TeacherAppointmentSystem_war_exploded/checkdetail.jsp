<%--
  Created by IntelliJ IDEA.
  User: 92540
  Date: 2020/4/12
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>教师预约系统</title>
    <script src="js/jquery-3.4.1.js"></script>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<script>
    $(function () {
        let url = "upload\\"+"${upload.fileName}";
        $("#image").prop("src",url);
    });
</script>
<body>
    <a href="/TeacherAppointmentSystem_war_exploded/TeacherUser/queryApplication" class="btn btn-primary active" role="button">返回</a>
    <form id="form" enctype="multipart/form-data" method="post">
        <div class="form-group col-xl-3">
            <label>姓名</label>
            <input type="text" class="form-control" id="name" name="name" value="${student.name}" disabled>
        </div>
        <div class="form-group col-xl-3">
            <label>学号</label>
            <input type="text" class="form-control" id="number" name="number" value="${student.number}" disabled>
        </div>
        <div class="form-group col-xl-3">
            <label>性别</label>
            <input type="text" class="form-control" id="sex" name="sex" value="${student.sex}" disabled>
        </div>
        <div class="form-group col-xl-3">
            <label>学院</label>
            <input type="text" class="form-control" id="college" name="college" value="${student.college}" disabled>
        </div>
        <div class="form-group col-xl-3">
            <label>专业</label>
            <input type="text" class="form-control" id="major" name="major" value="${student.major}" disabled>
        </div>
        <div class="form-group col-xl-3">
            <label>班级</label>
            <input type="text" class="form-control" id="clas" name="clas" value="${student.clas}" disabled>
        </div>
    </form>

    <label>学生上传的图片</label>
    <img id="image" src="null" height="100" width="100" alt="${upload.fileName}">

</body>
</html>
