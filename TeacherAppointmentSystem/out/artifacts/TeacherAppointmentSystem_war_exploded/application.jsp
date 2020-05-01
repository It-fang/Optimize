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
    <title>Teacher Appointment System</title>
    <script src="js/jquery-3.4.1.js"></script>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<%--提交预约请求--%>
<script>
    $(function () {
        $("#submit").click(function () {
            alert($("#form").serialize());
            $.post("StudentUser/apply",$("#form").serialize(),function (resultInfo) {
                alert("adf");
                if (resultInfo.status){
                    alert(resultInfo.message);
                    window.location.href = "/TeacherAppointmentSystem_war_exploded/queryteacher.jsp"
                }else {
                    alert(resultInfo.message);
                    window.location.href = "/TeacherAppointmentSystem_war_exploded/application.jsp"
                }
            },"json");
            // $.ajax({
            //     url:"StudentUser/apply",
            //     type:"post",
            //     async:true,
            //     dataType:"text",
            //     contentType: "application/json; charset=utf-8",
            //     data:$("#form").serialize(),
            //     success:function (resultInfo) {
            //         alert("resultInfo.message");
            //     },
            //     error:function (resultInfo) {
            //       alert("操作错误");
            //     }
            // })
        });
    });
</script>
<body>
    <form id="form" action="/TeacherAppointmentSystem_war_exploded/submitApplyServlet?teacherId=${teacherId}&studentId=${studentId}" method="post">
        <div class="form-group col-xl-3">
            <label>教师姓名</label>
            <p class="form-control-static">${teacherName}</p>
        </div>
        <div class="form-group col-xl-3">
            <label for="name">姓名</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入你的名字">
        </div>
        <div class="form-group col-xl-3">
            <label for="number">学号</label>
            <input type="text" class="form-control" id="number" name="number" placeholder="请输入你的学号">
        </div>
        <div class="form-group col-xl-3">
            <label for="time">预约时间</label>
            <input type="date" class="form-control" id="time" name="time" placeholder="请输入预约时间">
        </div>
        <input type="button" id="submit" class="btn btn-success btn-lg" value="提交">
    </form>
</body>
</html>
