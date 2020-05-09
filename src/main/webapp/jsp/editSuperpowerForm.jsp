<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Edit Superpower: ${superpower.name}"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Edit Superpower: <c:out value="${superpower.name}"/></h1>
    </div>
    <sf:form class="form-horizontal" role="form" modelAttribute="superpower" action="editSuperpower" method="POST">
        <div class="form-group">
            <label for="name" class="col-md-2 control-label">Name:</label>
            <div class="col-md-10">
                <sf:input type="text" class="form-control" id="name" path="name" placeholder="Superpower Name" maxlength="50" required="required"/>
                <sf:errors path="name" cssClass="error"/>
                <sf:hidden path="superpowerId"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="displaySuperpowersPage" class="btn btn-default" role="button">Cancel</a>
                <button type="submit" class="btn btn-primary">Update Superpower</button>
            </div>
        </div>
    </sf:form>
</div>
<jsp:include page="_footer.jsp"/>