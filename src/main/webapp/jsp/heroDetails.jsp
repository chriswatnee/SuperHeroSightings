<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="${hero.name}"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1><c:out value="${hero.name}"/></h1>
    </div>
    <div class="row">
        <div class="col-md-6">
            <p><strong>Description:</strong> <c:out value="${hero.description}"/></p>
            <p><strong>Superpowers:</strong></p>
            <ul>
                <c:forEach var="currentSuperpower" items="${hero.superpowers}">
                    <li><c:out value="${currentSuperpower.name}"/></li>
                </c:forEach>
            </ul>
        </div>
        <c:if test="${hero.pictureFilename != null}">
            <div class="col-md-6">
                <img src="${pageContext.request.contextPath}/${hero.pictureFilename}"
                                 class="img-thumbnail" alt="Picture of ${hero.name}">
            </div>
        </c:if>
    </div>
    <p><a href="${pageContext.request.contextPath}/displayHeroesPage" 
          class="btn btn-default navbar-btn" role="button">Back</a></p>
</div>
<jsp:include page="_footer.jsp"/>