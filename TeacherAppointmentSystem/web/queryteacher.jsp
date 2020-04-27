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
<div class="container">
    <h3><p class="text-center">教师信息列表</p></h3>
    <form class="form-inline" action="/TeacherAppointmentSystem_war_exploded/queryTeacherByPageServlet?studentId=${studentUser.studentId}" method="post">
        <div class="form-group">
            <label for="name">姓名</label>
            <input type="text" class="form-control" id="name" name="name" value="${condition.name[0]}" placeholder="请输入教师的名字">
        </div>
        <div class="form-group">
            <label for="college">学院</label>
            <input type="text" class="form-control" id="college" name="college" value="${condition.college[0]}" placeholder="请输入教师所属学院">
        </div>
        <input type="submit" class="btn btn-primary btn-sm" value="搜索 ">
        <div style="float:right; margin: 5px;">
            <a class="btn btn-success" href="/TeacherAppointmentSystem_war_exploded/queryTeacherByPageServlet?currentPage=1&rows=5&studentId=${studentUser.studentId}" role="button">显示所有教师信息</a>
        </div>
        <div style="float:right; margin: 5px;">
            <a class="btn  btn-success" href="/TeacherAppointmentSystem_war_exploded/queryResultServlet?studentId=${studentUser.studentId}" role="button">显示所有预约结果</a>
        </div>
    </form>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>教师姓名</th>
            <th>所属学院</th>
            <th>专业</th>
            <th>班级</th>
            <th>空闲时间</th>
            <th>预约教师</th>
        </tr>
        <c:forEach items="${page.list}" var="teacher" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${teacher.name}</td>
                <td>${teacher.college}</td>
                <td>${teacher.major}</td>
                <td>${teacher.clas}</td>
                <td>${teacher.freetime}</td>
                <td><a href="/TeacherAppointmentSystem_war_exploded/applicationServlet?id=${teacher.id}&name=${teacher.name}&studentId=${studentUser.studentId}" class="btn btn-primary btn-sm active" role="button">预约</a></td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination pagination-lg">
                <c:if test="${page.currentPage == 1}">
                    <li class="disabled">
                        <a href="/TeacherAppointmentSystem_war_exploded/queryTeacherByPageServlet?currentPage=${page.currentPage}&rows=5&name=${condition.name[0]}&college=${condition.college[0]}&studentId=${studentUser.studentId}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${page.currentPage != 1}">
                    <li>
                        <a href="/TeacherAppointmentSystem_war_exploded/queryTeacherByPageServlet?currentPage=${page.currentPage - 1}&rows=5&name=${condition.name[0]}&college=${condition.college[0]}&studentId=${studentUser.studentId}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${page.totalPage}" var="i">
                    <c:if test="${page.currentPage == i}">
                         <li class="active"><a href="/TeacherAppointmentSystem_war_exploded/queryTeacherByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&college=${condition.college[0]}&studentId=${studentUser.studentId}">${i}</a></li>
                    </c:if>
                    <c:if test="${page.currentPage!= i}">
                         <li><a href="/TeacherAppointmentSystem_war_exploded/queryTeacherByPageServlet?currentPage=${i}&rows=5&name=${condition.name[0]}&college=${condition.college[0]}&studentId=${studentUser.studentId}">${i}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${page.currentPage == page.totalPage}">
                    <li class="disabled">
                        <a href="/TeacherAppointmentSystem_war_exploded/queryTeacherByPageServlet?currentPage=${page.currentPage}&rows=5&name=${condition.name[0]}&college=${condition.college[0]}&studentId=${studentUser.studentId}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${page.currentPage != page.totalPage}">
                    <li>
                        <a href="/TeacherAppointmentSystem_war_exploded/queryTeacherByPageServlet?currentPage=${page.currentPage + 1}&rows=5&name=${condition.name[0]}&college=${condition.college[0]}&studentId=${studentUser.studentId}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <span style="font-size: 30px;margin-left: 10px;">
                    共${page.totalCount}条记录,共${page.totalPage}页
                </span>
            </ul>
        </nav>
    </div>
</div>




</body>
</html>
