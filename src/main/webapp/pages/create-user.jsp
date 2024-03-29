<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form class="mt-3 needs-validation" action="/create-user" method="post">
        <div class="row mb-3">
            <div class="col">
                <label for="Firstname" class="form-label">First name</label>
                <input name="firstname" type="text" class="form-control" id="Firstname" required pattern="([A-Za-z])*">
            </div>
            <div class="col">
                <label for="Lastname" class="form-label">Last name</label>
                <input name="lastname" type="text" class="form-control" id="Lastname" required pattern="([A-Za-z])*">
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-3">
                <label for="Age" class="form-label">Age</label>
                <input name="age" type="number" class="form-control" id="Age" min="1" required>
            </div>
            <div class="col-9">
                <label for="PhoneNumber" class="form-label">Phone number</label>
                <input name="phoneNumber" type="number" class="form-control" id="PhoneNumber" required pattern="^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$">
            </div>
            <c:if test="${phoneUsed != null}">
                <div class="alert alert-danger" role="alert">
                    ${phoneUsed}
                </div>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
