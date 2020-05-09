<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Create Organization"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Create Organization</h1>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-danger fade in alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong> <c:out value="${message}"/>
        </div>
    </c:if>
    <form class="form-horizontal" role="form" action="createOrganization" method="POST">
        <div class="form-group">
            <label for="name" class="col-md-2 control-label">Name:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="name" id="name" 
                       placeholder="Organization Name" maxlength="50" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="description" class="col-md-2 control-label">Description:</label>
            <div class="col-md-10">
                <textarea class="form-control" name="description" id="description" 
                          placeholder="Organization Description" rows="3" maxlength="200"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-md-2 control-label">Phone:</label>
            <div class="col-md-10">
                <input type="tel" class="form-control" name="phone" id="phone" 
                       placeholder="Organization Phone" maxlength="20" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="description" class="col-md-2 control-label">Email:</label>
            <div class="col-md-10">
                <input type="email" class="form-control" name="email" id="email" 
                       placeholder="Organization Email" maxlength="30" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="location" class="col-md-2 control-label">Location:</label>
            <div class="col-md-10">
                <select class="form-control" name="location" id="location" required="required">
                    <option value="" selected="selected">Select a Location</option>
                    <c:forEach var="currentLocation" items="${locationList}">
                        <option value="${currentLocation.locationId}">
                            <c:out value="${currentLocation.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="heroes" class="col-md-2 control-label">Heroes:</label>
            <div class="col-md-10">
                <select class="form-control" name="heroes" id="heroes" 
                        multiple="multiple" size="10" required="required">
                    <c:forEach var="currentHero" items="${heroList}">
                        <option value="${currentHero.heroId}">
                            <c:out value="${currentHero.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="displayOrganizationsPage" class="btn btn-default" role="button">Cancel</a>
                <button type="submit" class="btn btn-primary">Create Organization</button>
            </div>
        </div>
    </form>
</div>
<jsp:include page="_footer.jsp"/>