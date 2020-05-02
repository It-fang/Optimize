<%--
  Created by IntelliJ IDEA.
  User: 92540
  Date: 2020/4/10
  Time: 14:58
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
<style>
    body{
        background: url("img/background.jpg") no-repeat ;
        background-size: 100%;
    }
    .rg_layout{
        width:350px;
        height:400px;
        border: 3px solid #EEEEEE;
        background-color: white;
        margin: auto;
        margin-top: 120px;
        margin-bottom: 100px;
    }
    .td_text{
        position: absolute;
        top: 150px;
        left:500px;
    }
    .td_text_password{
        position: absolute;
        top: 55px;
        left:50px;
    }
</style>
<%--    判断登陆是否成功--%>
    <script>
        $(function () {
            $("#button").click(function () {
                $.post("TeacherUser/login",$("#form").serialize(),function (resultInfo) {
                    if (resultInfo.status){
                        alert(resultInfo.message);
                        window.location.href = "/TeacherAppointmentSystem_war_exploded/TeacherUser/queryApplication"
                    }else {
                        alert(resultInfo.message);
                        window.location.href = "/TeacherAppointmentSystem_war_exploded/teacherlogin.jsp"
                    }
                },"json");
            });
        });
    </script>
</head>
<body>
<div class= "rg_layout">
    <form class="form-horizontal form-inline" id="form" action="/TeacherAppointmentSystem_war_exploded/teacherLoginServlet" method="post">
            <div class="td_text">
                <div class="form-group">
                    <label for="username" class="row-cols-3 control-label">用户名:</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control " id="username" name="username" placeholder="请输入用户名">
                    </div>
                </div><br>
                <div class="form-group">
                    <label for="password" class="row-cols-1 control-label">密码:</label>
                    <div class="col-sm-2 td_text_password">
                        <input type="password" class="form-control " id="password" name="password" placeholder="请输入密码">
                    </div>
                </div>
            </div>
        <input type="button" id="button" class="btn btn-primary btn-block" style="margin-top: 150px" value="教师登陆 ">
    </form>
    <br><br><br>
    <h7>--------------------其他用户登陆------------------</h7>
    <p style="margin-left: 70px">
        <a href="studentlogin.html" class="btn btn-info active" role="button">学生登陆</a>
        <a href="adminlogin.html" class="btn btn-info     active" role="button">管理员登陆</a>
    </p>
    <c:if test="${msg != null}">
        <span style="margin-left:70px ;background-color: yellow">${msg}</span>
    </c:if>
</div>
</body>
</html>