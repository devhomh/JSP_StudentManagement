<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html lang="en">
<head>
    <title>학생 등록</title>
  <link rel="stylesheet" href="https://unpkg.com/mvp.css">
</head>
<body>
  <c:choose>
    <c:when test="${empty student}">
      <c:set var="action" value="/student/register.do" />
    </c:when>
    <c:otherwise>
      <c:set var="action" value="/student/update.do" />
    </c:otherwise>
  </c:choose>

  <form method="POST" action="${action}">
    <label for="id">ID</label>
    <input id="id" name="id" placeholder="abcd" type="text" value="${student.id}" required/>
    <br/>
    <label for="name">이름</label>
    <input id="name" name="name" placeholder="홍길동" type="text" value="${student.name}" required/>
    <br/>
    <label>성별</label>
    <label>
      <input class="gender" type="radio" name="gender" ${"M".equals(student.gender.toString()) ? "checked" : ""} value="male" required> 남
    </label>
    <label>
      <input class="gender" type="radio" name="gender" ${"F".equals(student.gender.toString()) ? "checked" : ""} value="female" required> 여
    </label>
    <br/>
    <label for="age">나이</label>
    <input id="age" name="age" type="number" value="${student.age}" required/>
    <br/>
    <button type="submit">
      <c:choose>
        <c:when test="${empty student}">
          등록
        </c:when>
        <c:otherwise>
          수정
        </c:otherwise>
      </c:choose>
    </button>
  </form>
</body>
</html>
