<%@ page import="java.util.List" %>
<%@ page import="com.example.springbootboard.data.dto.UserRequestDTO" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: chaedongim
  Date: 2023/07/13
  Time: 5:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
  List<String> teamList = (List<String>) request.getAttribute("teamList");
  UserRequestDTO userRequestDTO = (UserRequestDTO) request.getAttribute("userRequestDTO");
  System.out.println("userRequestDTO join jsp page= " + userRequestDTO);
  String ninkname = "";
  String userEmail = "";
  System.out.println("teamList = " + teamList);
  String teamName = teamList.get(0);
  if (userRequestDTO != null) {
    ninkname = Optional.ofNullable(userRequestDTO.getNickname()).orElse("");
    userEmail = Optional.ofNullable(userRequestDTO.getUserEmail()).orElse("");
    teamName = Optional.ofNullable(userRequestDTO.getTeamName()).orElse(teamList.get(0));
  }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous"/>
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/navbar.css">
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/base.css">
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <title>Admin Hana - join.jsp</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="../common/navbar.jsp" %>
    </nav>

    <main>
      <section class="vh-100" style="background-color: #eee;">
        <div class="container h-100">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
              <div class="card text-black" style="border-radius: 25px;">
                <div class="card-body p-md-5">
                  <div class="row justify-content-center">
                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                      <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                      <%-- names = userEmail nickname passwrod team--%>
                      <form class="mx-1 mx-md-4" method="post" action="join">
                        <div class="d-flex flex-row align-items-center mb-4">
                          <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input name="userEmail" type="email" id="form3Example3c" class="form-control"
                                   value="<%=userEmail%>"/>
                            <label class="form-label" for="form3Example3c">Your Email</label>
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-4">
                          <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input name="nickname" type="text" id="form3Example1c" class="form-control"
                                   minlength="2"
                                   maxlength="10"/>
                            <label class="form-label" for="form3Example1c">Your Nickname</label>
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-4">
                          <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input name="password" type="password" id="form3Example4c" class="form-control"
                                   minlength="4"
                                   maxlength="20"/>
                            <label class="form-label" for="form3Example4c">Password</label>
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-4">
                          <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input name="password" type="password" id="form3Example4cd" class="form-control"
                                   minlength="4"
                                   maxlength="20"/>
                            <label class="form-label" for="form3Example4cd">Repeat your password</label>
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-4">
                          <i class="fas fa-people-group fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <select name="teamName" class="form-control">
                              <%
                                for (int i = 0; i < teamList.size(); i++) {
                              %>
                              <option value="<%=teamList.get(i)%>"><%=teamList.get(i)%>
                              </option>
                              <%
                                }
                              %>
                            </select>
                          </div>
                        </div>

                        <div class="form-check d-flex justify-content-center mb-5">
                          <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3c"/>
                          <label class="form-check-label" for="form2Example3c">
                            I agree all statements in <a href="#!">Terms of service</a>
                          </label>
                        </div>

                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                          <button type="submit" class="btn btn-primary btn-lg">Register</button>
                        </div>

                      </form>

                    </div>
                    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">


                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>

  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
</body>
</html>