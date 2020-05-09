<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Edit Sighting"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Edit Sighting</h1>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-danger fade in alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong> <c:out value="${message}"/>
        </div>
    </c:if>
    <sf:form class="form-horizontal" role="form" modelAttribute="sighting" action="editSighting" method="POST">
        <div class="form-group">
            <label for="name" class="col-md-2 control-label">Date/Time:</label>
            <div class="col-md-10">
                <input type="datetime-local" class="form-control" name="dateTime" 
                       id="dateTime" value="${sighting.dateTime}" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="location" class="col-md-2 control-label">Location:</label>
            <div class="col-md-10">
                <sf:select class="form-control" id="location" path="location" required="required">
                    <sf:options items="${locationList}" itemValue="locationId" itemLabel="name"/>
                </sf:select>
            </div>
        </div>
        <div class="form-group">
            <label for="heroes" class="col-md-2 control-label">Heroes:</label>
            <div class="col-md-10">
                <sf:select class="form-control" id="heroes" path="heroes" 
                           multiple="true" size="10" required="required">
                    <sf:options items="${heroList}" itemValue="heroId" itemLabel="name"/>
                </sf:select>
                <sf:hidden path="sightingId"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="displaySightingsPage" class="btn btn-default" role="button">Cancel</a>
                <button type="submit" class="btn btn-primary">Update Sighting</button>
            </div>
        </div>
    </sf:form>
</div>
<jsp:include page="_footer.jsp"/>