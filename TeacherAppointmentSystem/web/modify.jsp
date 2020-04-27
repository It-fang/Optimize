<%--
  Created by IntelliJ IDEA.
  User: 92540
  Date: 2020/4/10
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Teacher Appointment System</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td,th{
            text-align: center;
        }
    </style>
</head>
<body>
    <form action="/TeacherAppointmentSystem_war_exploded/updateServlet?id=${teacher.id}" method="post">
        <div class="form-group col-xl-3">
            <label for="name">姓名</label>
            <input type="text" class="form-control" id="name" name="name" value="${teacher.name}" placeholder="请输入你的名字">
        </div>
        <div class="form-group col-xl-3">
            <label for="college">学院</label>
            <input type="text" class="form-control" id="college" name="college"  value="${teacher.college}" placeholder="请输入所属学院">
        </div>
        <div class="form-group col-xl-3">
            <label for="major">专业</label>
            <input type="text" class="form-control" id="major" name="major"  value="${teacher.major}"placeholder="请输入专业">
        </div>
        <div class="form-group col-xl-3">
            <label for="clas">班级</label>
            <input type="text" class="form-control" id="clas" name="clas" value="${teacher.clas}" placeholder="请输入班级">
        </div>
        <div class="form-group col-xl-3">
            <label for="freetime">空闲时间</label>
            <input type="datetime-local" class="form-control" id="freetime" name="freetime" placeholder="请输入你的空闲时间">
        </div>
        <input type="submit" class="btn btn-success" value="修改 ">
    </form>
</body>
</html>
