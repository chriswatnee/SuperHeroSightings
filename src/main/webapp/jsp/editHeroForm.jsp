<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Edit Hero/Villain: ${hero.name}"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Edit Hero/Villain: <c:out value="${hero.name}"/></h1>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-danger fade in alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong> <c:out value="${message}"/>
        </div>
    </c:if>
    <sf:form class="form-horizontal" role="form" modelAttribute="hero" action="editHero" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name" class="col-md-2 control-label">Name:</label>
            <div class="col-md-10">
                <sf:input type="text" class="form-control" id="name" path="name" 
                          placeholder="Hero/Villain Name" maxlength="50" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="description" class="col-md-2 control-label">Description:</label>
            <div class="col-md-10">
                <sf:textarea class="form-control" id="description" path="description" 
                             placeholder="Hero/Villain Description" rows="3" maxlength="500"/>
            </div>
        </div>
        <div class="form-group">
            <label for="picture" class="col-md-2 control-label">Picture:</label>
            <div class="col-md-10">
                <input type="file" class="form-control" name="picture" id="picture" accept=".jpg, .jpeg, .png"/>
            </div>
        </div>
        <div class="form-group">
            <label for="superpowers" class="col-md-2 control-label">Superpowers:</label>
            <div class="col-md-10">
                <sf:select class="form-control" id="superpowers" path="superpowers" 
                           multiple="true" size="10" required="required">
                    <sf:options items="${superpowerList}" itemValue="superpowerId" itemLabel="name"/>
                </sf:select>
                <sf:hidden path="heroId"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="displayHeroesPage" class="btn btn-default" role="button">Cancel</a>
                <button type="submit" class="btn btn-primary">Update Hero/Villain</button>
            </div>
        </div>
    </sf:form>
</div>
<jsp:include page="_footer.jsp"/>