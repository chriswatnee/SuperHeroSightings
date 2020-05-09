<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Edit Location: ${location.name}"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Edit Location: <c:out value="${location.name}"/></h1>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-danger fade in alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong> <c:out value="${message}"/>
        </div>
    </c:if>
    <sf:form class="form-horizontal" role="form" modelAttribute="location" action="editLocation" method="POST">
        <div class="form-group">
            <label for="name" class="col-md-2 control-label">Name:</label>
            <div class="col-md-10">
                <sf:input type="text" class="form-control" id="name" path="name" placeholder="Location Name" maxlength="50" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="description" class="col-md-2 control-label">Description:</label>
            <div class="col-md-10">
                <sf:textarea class="form-control" id="description" path="description" 
                             placeholder="Location Description" rows="3" maxlength="100"/>
            </div>
        </div>
        <div class="form-group">
            <label for="streetAddress" class="col-md-2 control-label">Street Address:</label>
            <div class="col-md-10">
                <sf:input type="text" class="form-control" id="streetAddress" path="streetAddress"
                          placeholder="Street Address" maxlength="50" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="city" class="col-md-2 control-label">City:</label>
            <div class="col-md-10">
                <sf:input type="text" class="form-control" id="city" path="city" 
                          placeholder="City" maxlength="50" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="state" class="col-md-2 control-label">State:</label>
            <div class="col-md-10">
                <sf:select class="form-control" id="state" path="state" required="required">
                    <sf:options items="${stateList}" itemValue="stateId" itemLabel="name"/>
                </sf:select>
            </div>
        </div>
        <div class="form-group">
            <label for="zipCode" class="col-md-2 control-label">Zip Code:</label>
            <div class="col-md-10">
                <sf:input type="text" class="form-control" id="zipCode" path="zipCode" 
                          placeholder="Zip Code" maxlength="5" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="latitude" class="col-md-2 control-label">Latitude:</label>
            <div class="col-md-10">
                <sf:input type="text" class="form-control" id="latitude" path="latitude" 
                          placeholder="Latitude" maxlength="10" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="longitude" class="col-md-2 control-label">Longitude:</label>
            <div class="col-md-10">
                <sf:input type="text" class="form-control" id="longitude" path="longitude" 
                          placeholder="Longitude"/>
                <sf:hidden path="locationId" maxlength="10" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="displayLocationsPage" class="btn btn-default" role="button">Cancel</a>
                <button type="submit" class="btn btn-primary">Update Location</button>
            </div>
        </div>
    </sf:form>
</div>
<jsp:include page="_footer.jsp"/>