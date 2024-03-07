<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="container justify-content-center">
        <div class="row">
            <c:out value="${itsEmpty}"/>
            <div class="row">
                <div class="col-3 text-center">
                    <c:out value="Firstname"/>
                </div>
                <div class="col-3 text-center">
                    <c:out value="Lastname"/>
                </div>
                <div class="col-1 text-center">
                    <c:out value="Age"/>
                </div>
                <div class="col-3 text-center">
                    <c:out value="Phone Number"/>
                </div>
                <div class="col-2 text-center">
                </div>
            </div>
            <ul class="mt-3" style="list-style-type: none">
                <c:forEach items="${pageableUserList.userList}" var="user">
                    <li class="mt-2 border border-black">
                        <div class="row">
                            <div class="col-3 text-center align-self-center">
                                <c:out value="${user.firstname}"/>
                            </div>
                            <div class="col-3 text-center align-self-center">
                                <c:out value="${user.lastname}"/>
                            </div>
                            <div class="col-1 text-center align-self-center">
                                <c:out value="${user.age}"/>
                            </div>
                            <div class="col-3 text-center align-self-center">
                                <c:out value="${user.phoneNumber}"/>
                            </div>
                            <div class="col-1 text-center align-self-center">
                                <a href="/modify-user?id=${user.id}">
                                    <button type="button" class="btn btn-primary">modify</button>
                                </a>
                            </div>
                            <div class="col-1 text-center">
                                <form class="mb-0" action="/delete-user" method="post">
                                    <input type="hidden" name="id" value="${user.id}" />
                                    <button type="submit" class="btn btn-danger align-self-center">delete</button>
                                </form>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <c:if test="${pageableUserList.countOfPages > 1}">
        <div class="container">
            <div class="row">
                <ul class="pagination justify-content-center">
                    <c:choose>
                        <c:when test="${(page == 1) or (page == 0) or (page == null)}">
                            <li class="page-item disabled">
                                <a class="page-link" href="/?page=${1}"
                                   aria-label="First">
                                    <span aria-hidden="true">First</span>
                                </a>
                            </li>
                        </c:when>
                        <c:when test="${page != 1}">
                            <li class="page-item">
                                <a class="page-link" href="/?page=${1}"
                                   aria-label="First">
                                    <span aria-hidden="true">First</span>
                                </a>
                            </li>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${(page == 1) or (page == 0) or (page == null)}">
                            <li class="page-item disabled">
                                <a class="page-link" href="/?page=${page-1}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:when>
                        <c:when test="${page != 1}">
                            <li class="page-item">
                                <a class="page-link" href="/?page=${page-1}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:when>
                    </c:choose>
                    <c:if test="${pageableUserList.countOfPages > 1}">
                        <div class="btn-group">
                            <button type="button" class="btn btn-secondary dropdown-toggle"
                                    data-bs-toggle="dropdown" aria-expanded="false">
                                    ${page}
                            </button>
                            <ul class="dropdown-menu">
                                <c:forEach begin="1" var="i" end="${pageableUserList.countOfPages}" step="1">
                                    <li>
                                        <a class="dropdown-item" href="/?page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <c:choose>
                        <c:when test="${page == pageableUserList.countOfPages}">
                            <li class="page-item disabled">
                                <a class="page-link" href="/?page=${page+1}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:when>
                        <c:when test="${(page == 0) or (page == null)}">
                            <li class="page-item">
                                <a class="page-link" href="/?page=2"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:when>
                        <c:when test="${page != pageableUserList.countOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="/?page=${page+1}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${page == pageableUserList.countOfPages}">
                            <li class="page-item disabled">
                                <a class="page-link" href="/?page=${pageableUserList.countOfPages}"
                                   aria-label="Last">
                                    <span aria-hidden="true">Last</span>
                                </a>
                            </li>
                        </c:when>
                        <c:when test="${page != pageableUserList.countOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="/?page=${pageableUserList.countOfPages}"
                                   aria-label="Last">
                                    <span aria-hidden="true">Last</span>
                                </a>
                            </li>
                        </c:when>
                    </c:choose>
                </ul>
            </div>
        </div>
    </c:if>
</div>

<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>