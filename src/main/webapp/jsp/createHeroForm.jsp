<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Create Hero/Villain"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Create Hero/Villain</h1>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-danger fade in alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong> <c:out value="${message}"/>
        </div>
    </c:if>
    <form class="form-horizontal" role="form" action="createHero" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name" class="col-md-2 control-label">Name:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="name" id="name" 
                       placeholder="Hero/Villain Name" maxlength="50" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <label for="description" class="col-md-2 control-label">Description:</label>
            <div class="col-md-10">
                <textarea class="form-control" name="description" id="description" 
                          placeholder="Hero/Villain Description" rows="3" maxlength="500"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="picture" class="col-md-2 control-label">Picture:</label>
            <div class="col-md-10">
                <input type="file" name="picture" id="picture" accept=".jpg, .jpeg, .png"/>
            </div>
        </div>
        <div class="form-group">
            <label for="superpowers" class="col-md-2 control-label">Superpowers:</label>
            <div class="col-md-10">
                <select class="form-control" name="superpowers" id="superpowers" 
                        multiple="multiple" size="10" required="required">
                    <c:forEach var="currentSuperpower" items="${superpowerList}">
                        <option value="${currentSuperpower.superpowerId}">
                            <c:out value="${currentSuperpower.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="displayHeroesPage" class="btn btn-default" role="button">Cancel</a>
                <button type="submit" class="btn btn-primary">Create Hero/Villain</button>
            </div>
        </div>
    </form>
</div>
<jsp:include page="_footer.jsp"/>