<%--
  Created by IntelliJ IDEA.
  User: 92540
  Date: 2020/4/14
<%--
  Created by IntelliJ IDEA.
  User: 92540
  Date: 2020/4/12
  Time: 16:51
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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<%--判断提交处理是否成功--%>
<script>
    $(function () {
        $("#button").click(function () {
            if (confirm("是否要提交处理结果")){
                $.post($("#form").attr("action"),$("#form").serialize(),function (resultInfo) {
                    if (resultInfo.status){
                        alert(resultInfo.message);
                        window.location.href = "/TeacherAppointmentSystem_war_exploded/TeacherUser/queryApplication";
                    }else {
                        alert(resultInfo.message);
                    }
                });
            }
        });
    });
</script>

<body>
<form id="form" action="/TeacherAppointmentSystem_war_exploded/TeacherUser/agree?studentId=${student.id}" method="post">
    <div class="form-group col-xl-3">
        <label for="name">学生姓名</label>
        <input type="text" class="form-control" id="name" name="name" value="${student.name}" disabled>
    </div>
    <div class="form-group col-xl-3">
        <label for="name">学生性别</label>
        <input type="text" class="form-control" id="sex" name="sex" value="${student.sex}" disabled>
    </div>
    <div class="form-group col-xl-3">
        <label for="name">学生学号</label>
        <input type="text" class="form-control" id="number" name="number" value="${student.number}" disabled>
    </div>
    <div class="form-group col-xl-3">
        <label for="name">学生学院</label>
        <input type="text" class="form-control" id="college" name="college" value="${student.college}" disabled>
    </div>
    <div class="form-group col-xl-3">
        <label for="name">学生专业</label>
        <input type="text" class="form-control" id="major" name="major" value="${student.major}" disabled>
    </div>
    <div class="form-group col-xl-3">
        <label for="name">学生班级</label>
        <input type="text" class="form-control" id="clas" name="clas" value="${student.clas}" disabled>
    </div>
    <div>
        <p><input type="radio" name="ifAgree" value="同意">同意</p>
        <p><input type="radio" name="ifAgree" value="拒绝">拒绝</p>
    </div>
    <input type="button" id="button" class="btn btn-primary" value="提交">
</form>
</body>
</html>
