<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<div class="jumbotron">
    <div class="container">
        <h1>It’s a Bird... It’s a Plane...</h1>
        <p>It's a Superhero Sightings Web Application. Use this application to enter and track superhero sightings.<br/>Start by using the following button to create a superpower.</p>
        <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/displaySuperpowersPage" role="button">Create Superpower</a></p>  
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h3>Newsfeed</h3>
            <table class="table table-hover">
                <tr>
                    <th width="30%">Date/Time</th>
                    <th width="35%">Location</th> 
                    <th width="35%">Heroes</th>
                </tr>
                <c:forEach var="currentSighting" items="${sightingList}">
                    <tr>
                        <td class="small">
                            <a href="${pageContext.request.contextPath}/displaySightingDetails?sightingId=${currentSighting.sightingId}"><c:out value="${localDateTimeFormat.format(currentSighting.dateTime)}"/></a>
                        </td>
                        <td class="small">
                            <c:out value="${currentSighting.location.streetAddress}"/><br/>
                            <c:out value="${currentSighting.location.city}"/>, <c:out value="${currentSighting.location.state.name}"/> <c:out value="${currentSighting.location.zipCode}"/>
                        </td>
                        <td class="small">
                            <c:forEach var="currentHero" items="${currentSighting.heroes}" varStatus="loop">
                                <c:out value="${currentHero.name}"/><c:if test="${!loop.last}">, </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col-md-6">
            <h2>Map</h2>
            <div id="map"></div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/map.js"></script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAVsWjGWHARbbi4CBCE9JkEOnGiGLGPU5c&callback=initMap">
</script>
<jsp:include page="_footer.jsp"/>
