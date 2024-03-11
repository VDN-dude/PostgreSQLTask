<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
    <c:if test="${notFound != null}">
        <c:out value="${notFound}"/>
    </c:if>
    <c:if test="${notFound == null}">
    <form class="mt-3 needs-validation" action="/modify-user" method="post">
        <div class="row mb-3">
            <div class="col">
                <label for="Firstname" class="form-label">First name</label>
                <input name="firstname" type="text" class="form-control" id="Firstname" value="${user.firstname}"
                       required pattern="([A-Za-z])*">
            </div>
            <div class="col">
                <label for="Lastname" class="form-label">Last name</label>
                <input name="lastname" type="text" class="form-control" id="Lastname" value="${user.lastname}" required
                       pattern="([A-Za-z])*">
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-3">
                <label for="Age" class="form-label">Age</label>
                <input name="age" type="number" class="form-control" value="${user.age}" id="Age" min="1" required>
            </div>
            <div class="col-9">
                <label for="PhoneNumber" class="form-label">Phone number</label>
                <input name="phoneNumber" type="number" class="form-control" value="${user.phoneNumber}"
                       id="PhoneNumber" required pattern="^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Modify</button>
    </form>
    <form class="mb-0" action="/delete-user" method="post">
        <input type="hidden" name="id" value="${user.id}"/>
        <button type="submit" class="btn btn-danger align-self-center">delete</button>
    </form>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
