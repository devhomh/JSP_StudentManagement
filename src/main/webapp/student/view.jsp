<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="https://unpkg.com/mvp.css">
</head>
<body>
<table>
    <tbody>
        <tr>
            <th scope="row">아이디</th>
            <td>${student.id}</td>
        </tr>
        <tr>
            <th scope="row">이름</th>
            <td>${student.name}</td>
        </tr>
        <tr>
            <th scope="row">성별</th>
            <td>${student.gender == "M" ? "남자" : "여자"}</td>
        </tr>
        <tr>
            <th scope="row">나이</th>
            <td>${student.age}</td>
        </tr>
        <tr>
            <th scope="row">등록일</th>
            <td>${student.createAt}</td>
        </tr>
    </tbody>
</table>
<div>
    <button onclick="location.href='/student/list'">리스트</button>
    <c:url var="update_link" value="/student/update">
        <c:param name="id" value="${student.id}" />
    </c:url>
    <button onclick="location.href='${update_link}'">
        수정
    </button>
 </div>
<c:url var="delete_link" value="/student/delete">
    <c:param name="id" value="${student.id}" />
</c:url>
<form method="POST" action="${delete_link}">
    <button>삭제</button>
</form>
</body>
</html>