<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html lang="en">
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="https://unpkg.com/mvp.css">
</head>

<body>
<h1>학생 리스트</h1>
<p><a href="/student/register" >학생(등록)</a></p>
<table>
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
        <th>cmd</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${studentList}">
        <tr>
            <th>${student.id}</th>
            <th>${student.name}</th>
            <th>${student.gender}</th>
            <th>${student.age}</th>
            <th><a href="/student/view?id=${student.id}">조회</a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>