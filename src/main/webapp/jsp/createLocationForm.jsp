<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Create Location"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Create Location</h1>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-danger fade in alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong> <c:out value="${message}"/>
        </div>
    </c:if>
    <form class="form-horizontal" role="form" action="createLocation" method="POST">
        <div class="form-group">
            <label for="name" class="col-md-2 control-label">Name:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="name" id="name" 
                       placeholder="Location Name" maxlength="50" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="description" class="col-md-2 control-label">Description:</label>
            <div class="col-md-10">
                <textarea class="form-control" name="description" id="description" 
                          placeholder="Location Description" rows="3" maxlength="100"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="streetAddress" class="col-md-2 control-label">Street Address:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="streetAddress" id="streetAddress"
                       placeholder="Street Address" maxlength="50" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="city" class="col-md-2 control-label">City:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="city" id="city" 
                       placeholder="City" maxlength="50" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="state" class="col-md-2 control-label">State:</label>
            <div class="col-md-10">
                <select class="form-control" name="state" id="state" required="required">
                    <option value="" selected="selected">Select a State</option>
                    <c:forEach var="currentState" items="${stateList}">
                        <option value="${currentState.stateId}">
                            <c:out value="${currentState.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="zipCode" class="col-md-2 control-label">Zip Code:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="zipCode" id="zipCode" 
                       placeholder="Zip Code" maxlength="5" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="latitude" class="col-md-2 control-label">Latitude:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="latitude" id="latitude" 
                       placeholder="Latitude" maxlength="10" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="longitude" class="col-md-2 control-label">Longitude:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="longitude" id="longitude" 
                       placeholder="Longitude" maxlength="10" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="displayLocationsPage" class="btn btn-default" role="button">Cancel</a>
                <button type="submit" class="btn btn-primary">Create Location</button>
            </div>
        </div>
    </form>
</div>
<jsp:include page="_footer.jsp"/>