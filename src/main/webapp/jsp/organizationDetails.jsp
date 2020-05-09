<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="${organization.name}"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1><c:out value="${organization.name}"/></h1>
    </div>
    <p><strong>Description:</strong> <c:out value="${organization.description}"/></p>
    <p><strong>Phone:</strong> <c:out value="${organization.phone}"/></p>
    <p><strong>Email:</strong> <c:out value="${organization.email}"/></p>
    <p><strong>Location Name:</strong> <c:out value="${organization.location.name}"/></p>
    <p><strong>Location Description:</strong> <c:out value="${organization.location.description}"/></p> 
    <p><strong>Address:</strong> <c:out value="${organization.location.streetAddress}"/>, <c:out value="${organization.location.city}"/>, <c:out value="${organization.location.state.name}"/> <c:out value="${organization.location.zipCode}"/></p>
    <p><strong>Latitude:</strong> <c:out value="${organization.location.latitude}"/></p>
    <p><strong>Longitude:</strong> <c:out value="${organization.location.longitude}"/></p>
    <p><strong>Heroes/Villains:</strong></p>
    <ul>
        <c:forEach var="currentHero" items="${organization.heroes}">
            <li><c:out value="${currentHero.name}"/></li>
        </c:forEach>
    </ul>
    <p><a href="${pageContext.request.contextPath}/displayOrganizationsPage" 
          class="btn btn-default navbar-btn" role="button">Back</a></p>
</div>
<jsp:include page="_footer.jsp"/>