<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: garan
  Date: 01.04.2023
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
    <link rel="stylesheet" href="valid.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <form class="mt-3 needs-validation" action="/register" method="post">
        <div class="row mb-3">
            <div class="col">
                <label for="Firstname" class="form-label">First name</label>
                <input name="firstName" type="text" class="form-control" id="Firstname" required pattern="([A-Za-z])*">
            </div>
            <div class="col">
                <label for="Lastname" class="form-label">Last name</label>
                <input name="lastName" type="text" class="form-control" id="Lastname" required pattern="([A-Za-z])*">
            </div>
        </div>
        <div class="mb-3">
            <label for="Email" class="form-label">Email address</label>
            <input name="email" type="email" class="form-control" id="Email" required pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
            <c:if test="${emailUsed != null}">
                <div class="alert alert-danger" role="alert">
                        ${emailUsed}
                </div>
            </c:if>
        </div>
        <div class="mb-3">
            <label for="Username" class="form-label">Username</label>
            <input name="username" type="text" class="form-control" id="Username" required pattern="\w*">
            <c:if test="${usernameUsed != null}">
                <div class="alert alert-danger" role="alert">
                        ${usernameUsed}
                </div>
            </c:if>
        </div>
        <div class="mb-3">
            <label for="Password" class="form-label">Password</label>
            <input name="password" type="password" class="form-control" id="Password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
