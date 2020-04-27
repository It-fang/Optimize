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
    <title>Teacher Appointment System</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
</head>

<body>
<form class="form-horizontal" action="/TeacherAppointmentSystem_war_exploded/agreeServlet?teacherId=${teacherId}&number=${student.number}" method="post">
    <div class="form-group">
        <label class="col-sm-2 control-label">学生姓名</label>
        <div class="col-sm-10">
            <p class="form-control-static">${student.name}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">学生性别</label>
        <div class="col-sm-10">
            <p class="form-control-static">${student.sex}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">学生学号</label>
        <div class="col-sm-10">
            <p class="form-control-static">${student.number}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">学生学院</label>
        <div class="col-sm-10">
            <p class="form-control-static">${student.college}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">学生专业</label>
        <div class="col-sm-10">
            <p class="form-control-static">${student.major}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">学生班级</label>
        <div class="col-sm-10">
            <p class="form-control-static">${student.clas}</p>
        </div>
    </div>
    <div>
        <p><input type="radio" name="ifAgree" value="同意">同意</p>
        <p><input type="radio" name="ifAgree" value="拒绝">拒绝</p>
    </div>
    <input type="submit" class="btn btn-primary" value="提交">
</form>
</body>
</html>
