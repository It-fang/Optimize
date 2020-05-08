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
<style>
    .content{
        border: 3px solid black;
        width: 400px;
        height: 300px;
        float: left;
    }
    .userList{
        border: 3px solid black;
        width: 100px;
        height: 300px;
        float:left;
    }
    .send{
        background-color: greenyellow;
        width: 100px;
        height: 50px;
        margin-top: 50px;
    }
</style>
<script type="text/javascript">
    let ws;
    let url = "ws://localhost:8080/TeacherAppointmentSystem_war_exploded/chatSocket?username=${sessionScope.username}";
    window.onload = connect;
    function connect() {
        if ("WebSocket" in window){
            ws = new WebSocket(url);
        }else {
            alert("你的客户端不支持WebSocket!");
            return;
        }
        ws.onmessage = function (event) {
            let message = new  Function("return" + event.data)();
            if (message.alert != undefined){
                $("#content").append( message.date + message.alert + "<br/>");
            }
            if (message.names != undefined){
                $("#userList").html("")
                $("#userList").append("用户列表 <br/>")
                $(message.names).each(function () {
                    $("#userList").append(this + "<br/>");
                });
            }
            if (message.from != undefined){
                $("#content").append(message.from+" "+message.date+
                    " 说：<br/>"+message.sendMsg+"<br/>");
            }
        }

    }

    function send() {
        let msg = $("#msg").val();
        ws.send(msg);
        $("#msg").val("");
    }
</script>

<body>
<h3>欢迎 , ${sessionScope.username}进入聊天室！！</h3>
<div id="content" class="content">聊天框<br/>  </div>
<div id="userList" class="userList"></div>
<div style="float: left;clear: left">
    <input id="msg" type="text" style="width: 400px; height: 95px">
    <div style="border: 3px solid #EEEEEE; width: 100px; height: 100px;float: right">
        <button type="button" class="send" onclick="send();">发送</button>
    </div>
</div>
</body>
</html>
