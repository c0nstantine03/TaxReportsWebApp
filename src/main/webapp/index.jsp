<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Tax Office Website</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <c:if test="${empty sessionScope.user}">
            <jsp:include page="/jsp/home.jsp"/>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <jsp:include page="/jsp/authorized.jsp"/>
        </c:if>

        <div class="container">
            <div class="jumbotron text-center">
                <h1>Welcome to My Website</h1>
                <p>This is a simple example of a JSP page with a navbar.</p>
            </div>
        </div>
    </body>
</html>
