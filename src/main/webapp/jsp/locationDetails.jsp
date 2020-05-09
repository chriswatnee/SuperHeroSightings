<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="${location.name}"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1><c:out value="${location.name}"/></h1>
    </div>
    <p><strong>Description:</strong> <c:out value="${location.description}"/></p>
    <p><strong>Address:</strong> <c:out value="${location.streetAddress}"/>, <c:out value="${location.city}"/>, <c:out value="${location.state.name}"/> <c:out value="${location.zipCode}"/></p>
    <p><strong>Latitude:</strong> <c:out value="${location.latitude}"/></p>
    <p><strong>Longitude:</strong> <c:out value="${location.longitude}"/></p>
    <p><a href="${pageContext.request.contextPath}/displayLocationsPage" 
          class="btn btn-default navbar-btn" role="button">Back</a></p>
</div>
<jsp:include page="_footer.jsp"/>