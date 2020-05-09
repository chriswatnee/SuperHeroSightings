<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Sighting"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Sighting</h1>
    </div>
    <p><strong>Date/Time:</strong> <c:out value="${localDateTimeFormat.format(sighting.dateTime)}"/></p>
    <p><strong>Location Name:</strong> <c:out value="${sighting.location.name}"/></p>
    <p><strong>Location Description:</strong> <c:out value="${sighting.location.description}"/></p> 
    <p><strong>Address:</strong> <c:out value="${sighting.location.streetAddress}"/>, <c:out value="${sighting.location.city}"/>, <c:out value="${sighting.location.state.name}"/> <c:out value="${sighting.location.zipCode}"/></p>
    <p><strong>Latitude:</strong> <c:out value="${sighting.location.latitude}"/></p>
    <p><strong>Longitude:</strong> <c:out value="${sighting.location.longitude}"/></p>
    <p><strong>Heroes/Villains:</strong></p>
    <ul>
        <c:forEach var="currentHero" items="${sighting.heroes}">
            <li><c:out value="${currentHero.name}"/></li>
        </c:forEach>
    </ul>
    <c:forEach var="currentHero" items="${sighting.heroes}">
        <c:if test="${currentHero.pictureFilename != null}">
            <img src="${pageContext.request.contextPath}/${currentHero.pictureFilename}"
                             class="img-thumbnail" alt="Picture of ${currentHero.name}">
        </c:if>
    </c:forEach>
    <p><a href="${pageContext.request.contextPath}/displaySightingsPage" 
          class="btn btn-default navbar-btn" role="button">Back</a></p>
</div>
<jsp:include page="_footer.jsp"/>