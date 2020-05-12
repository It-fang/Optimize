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
    <title>教师预约系统</title>
    <script src="js/jquery-3.4.1.js"></script>
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
<%--    同意注册申请--%>
    <script>
        function agreeRegister(username,password,studentId) {
            if (confirm("是否同意该用户的注册申请?")){
                $.post("AdminUser/agreeRegister",{username:username,password:password,studentId:studentId},function (resultInfo) {
                    if (resultInfo.status){
                        alert(resultInfo.message);
                        window.location.href = "/TeacherAppointmentSystem_war_exploded/AdminUser/queryRegister";
                    }else {
                        alert(resultInfo.message);
                    }
                },"json");
            }
        }
    </script>
<%--    拒绝注册申请--%>
    <script>
        function refuseRegister(studentId) {
            if (confirm("是否拒绝用户的注册申请?")){
                $.post("AdminUser/refuseRegister",{studentId:studentId},function (resultInfo) {
                    if (resultInfo.status){
                        alert(resultInfo.message);
                        window.location.href = "/TeacherAppointmentSystem_war_exploded/AdminUser/queryRegister";
                    }else {
                        alert(resultInfo.message);
                    }
                },"json");
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3><p class="text-center">用户注册申请列表</p></h3>
    <div style="float: left; margin: 5px">
        <a href="/TeacherAppointmentSystem_war_exploded/teacherregister.html" class="btn btn-primary active" role="button">注册教师用户</a>
    </div>
    <div style="float:right; margin: 5px;">
        <a class="btn btn-lg btn-success" href="/TeacherAppointmentSystem_war_exploded/AdminUser/queryRegister" role="button">显示所有注册申请</a>
    </div>
    <div style="float:right; margin: 5px;">
        <a class="btn btn-lg btn-primary" href="/TeacherAppointmentSystem_war_exploded/notification.jsp" role="button">发送通知</a>
    </div>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>注册用户昵称</th>
            <th>注册用户密码</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${studentUsers}" var="studentUser" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${studentUser.username}</td>
                <td>${studentUser.password}</td>
                <td>
                    <a id="agrees" name="agrees" href="javascript:agreeRegister('${studentUser.username}','${studentUser.password}','${studentUser.studentId}')" class="btn btn-primary btn-xs active"  role="button">同意</a>
                    <a id="refuses" name="refuses" href="javascript:refuseRegister('${studentUser.studentId}')" class="btn btn-primary btn-xs active"  role="button" > 拒绝</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
