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
    <title>教师预约系统</title>
    <script src="js/jquery-3.4.1.js"></script>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>

<%--    发送公告--%>
    <script>
        $(function () {
            $("#submit").click(function () {
                $.post("AdminUser/sendNotification",$("#form").serialize(),function (resultInfo) {
                    if (resultInfo.status){
                        alert(resultInfo.message);
                        if (confirm("是否跳转回主页面？")){
                            window.location.href = "/TeacherAppointmentSystem_war_exploded/AdminUser/queryRegister";
                        }
                    }else {
                        alert(resultInfo.message);
                    }
                });
            });
        });
    </script>
</head>
<body>
<a href="/TeacherAppointmentSystem_war_exploded/AdminUser/queryRegister" class="btn btn-primary active" role="button">返回</a>
<button type="button" class="btn btn-lg btn-primary" data-toggle="modal" data-target="#notification" data-whatever="@全体成员">发送通知</button>
<div class="modal fade" id="notification" tabindex="-1" role="dialog" aria-labelledby="head">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="head">发送通知</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="modal-body">
                        <form id="form" method="post">
                            <div class="form-group">
                                <label for="message" class="control-label">内容:</label>
                                <textarea class="form-control" rows="5" id="message" name="message"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="submit" type="button" class="btn btn-primary">发送消息</button>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>