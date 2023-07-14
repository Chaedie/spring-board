<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.springbootboard.data.dto.PostResponseDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="true" %>
<%
  request.setCharacterEncoding("UTF-8");
  String contextPath = request.getContextPath();
  String listName = (String) request.getAttribute("listName");
  List<PostResponseDTO> responseDTOList = new ArrayList<>();
  List<PostResponseDTO> tempList = (List<PostResponseDTO>) request.getAttribute("responseDTOList");
  if (tempList != null) {
    responseDTOList = tempList;
  }
  Long totalCount = (Long) request.getAttribute("totalCount");
  String teamName = request.getParameter("teamName");
  String _page = request.getParameter("page");
  String size = request.getParameter("size");
  String search = request.getParameter("search");
  String sort = request.getParameter("sort");
  if (_page == null) {
    _page = "0";
  }
  if (size == null) {
    size = "10";
  }
  if (search == null) {
    search = "";
  }
  if (sort == null) {
    sort = "postId,DESC";
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
  <link rel="stylesheet" href="<%=contextPath%>/resources/css/listPage.css">
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <title>Admin Hana - responseDTOList.jsp</title>
</head>
<body>
  <div class="wrap">
    <nav id="layoutSidenav_nav">
      <%@ include file="../common/navbar.jsp" %>
    </nav>

    <main>
      <div class="card m-4">
        <div class="card-header">
          <svg height="20px" width="20px" class="svg-inline--fa fa-table me-1" aria-hidden="true" focusable="false"
               data-prefix="fas"
               data-icon="table" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg="">
            <path fill="currentColor"
                  d="M64 256V160H224v96H64zm0 64H224v96H64V320zm224 96V320H448v96H288zM448 256H288V160H448v96zM64 32C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H448c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64z"></path>
          </svg>
          <%=teamName%> 게시판
        </div>

        <div class="card-body">
          <div class="datatable-wrapper datatable-loading no-footer sortable searchable fixed-columns">
            <div class="datatable-top">
              <div class="datatable-dropdown"></div>
              <div class="datatable-search">
                <form class="searchForm">
                  <input class="datatable-input" name="searchInput" placeholder="Search..." type="search"
                         title="Search within table"
                         aria-controls="datatablesSimple">
                  <button type="submit">검색</button>
                </form>
              </div>
            </div>
            <div class="datatable-container">
              <table id="datatablesSimple" class="datatable-table w-100">
                <thead>
                <tr id="columnHead">

                </tr>

                </thead>
                <tbody>
                <%
                  for (int i = 0; i < responseDTOList.size(); i++) {
                %>

                <tr data-index="<%=i%>" style="cursor: pointer"
                    onclick="location.href='<%=contextPath%>/boards/<%=responseDTOList.get(i).getPostId()%>'">
                  <td style="width: 2rem"><%=responseDTOList.get(i).getPostId()%>
                  </td>
                  <td style="width: 2rem"><%=responseDTOList.get(i).getTeamName()%>
                  </td>
                  <td style="width: 4rem">
                    <div class="postTitleContainer">
                      <c:choose>
                        <c:when test="<%=responseDTOList.get(i).getUploadFiles().size() > 0%>">
                          <i class="fa-regular fa-image"></i>
                        </c:when>
                        <c:otherwise>
                          <i class="fa-regular fa-comment-dots"></i>
                        </c:otherwise>
                      </c:choose>
                      <span class="postTitle"><%=responseDTOList.get(i).getPostTitle()%></span>
                      [<%=responseDTOList.get(i).getComments().size()%>]
                    </div>
                  </td>
                  <td style="width: 2rem"><%=responseDTOList.get(i).getUpdatedAt().format(DateTimeFormatter.ISO_DATE)%>
                  </td>
                  <td style="width: 2rem"><%=responseDTOList.get(i).getUpdatedAt().format(DateTimeFormatter.ISO_DATE)%>
                  </td>
                </tr>

                <%
                  }
                %>
                </tbody>
              </table>
            </div>
            <div class="datatable-bottom">
              <div class="datatable-info">Showing <%=responseDTOList.size()%> <%=listName%>
                of <%=totalCount%> <%=listName%>s
              </div>
              <nav class="datatable-pagination">
                <ul class="datatable-pagination-list"></ul>
              </nav>
            </div>
          </div>
          <a href="<%=contextPath%>/boards/write?teamName=<%=teamName%>">
            <button type="button" class="btn btn-outline-secondary">글 쓰기</button>
          </a>
        </div>
      </div>


    </main>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
          crossorigin="anonymous"></script>
  <script>
    const page = <%=_page%>;
    const size = <%=size%>;
    const search = '<%=search %>';
    const sort = '<%=sort %>'
    const totalCount = <%=totalCount%>;
    const teamName = '<%=teamName%>';
    const columnHeaderList = [
      {id: 1, entityColumnName: "postId", listColumnName: "글 ID", sortable: "true"},
      {id: 2, entityColumnName: "teamName", listColumnName: "게시판 이름", sortable: "false"},
      {id: 3, entityColumnName: "postTitle", listColumnName: "글 제목", sortable: "false"},
      {id: 4, entityColumnName: "createdAt", listColumnName: "작성일", sortable: "true"},
      {id: 5, entityColumnName: "updatedAt", listColumnName: "수정일", sortable: "true"},
    ]
  </script>
  <script src="<%=contextPath%>/resources/js/listPage.js"></script>
</body>
</html>
