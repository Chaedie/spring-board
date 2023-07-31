<%@ page import="com.example.springbootboard.domain.users.dto.UserResponseDTO" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  UserResponseDTO userSession = Optional.ofNullable((UserResponseDTO) session.getAttribute("user")).orElseGet(UserResponseDTO::new);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"/>
  <link rel="stylesheet" href="${request.contexPath}/resources/css/navbar.css">
  <link rel="stylesheet" href="${request.contexPath}/resources/css/base.css">
  <link rel="stylesheet" href="${request.contexPath}/resources/css/index.css">
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <title>Admin Hana - index.jsp</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="common/navbar.jsp" %>
    </nav>

    <main>
      <h1>Spring 게시판 !!!</h1>
      <br>
      <h3>로그인한 유저 정보</h3>
      <c:choose>
        <c:when test="${empty sessionScope.user}">
          <span>비회원입니다!!!</span>
        </c:when>
        <c:otherwise>
          <table>
            <tr class="d-flex flex-column">
              <td>유저네임 : ${sessionScope.user.username}</td>
              <td>유저 이메일 : ${sessionScope.user.userEmail}</td>
              <td>유저 팀 : ${sessionScope.user.team.teamName}</td>
            </tr>
          </table>
        </c:otherwise>
      </c:choose>

      <%-- Code Here! --%>
    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
</body>
</html>
