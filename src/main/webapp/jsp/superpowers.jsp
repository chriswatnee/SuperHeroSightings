<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Superpowers"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Superpowers</h1>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-danger fade in alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong> <c:out value="${message}"/>
        </div>
    </c:if>
    <div class="row">
        <div class="col-md-6">
            <h3>List of Superpowers</h3>
            <table class="table table-hover">
                <tr>
                    <th width="80%">Superpower</th>
                    <th width="20%"></th>
                </tr>
                <c:forEach var="currentSuperpower" items="${superpowerList}">
                    <tr>
                        <td>
                            <c:out value="${currentSuperpower.name}"/>
                        </td>
                        <td>
                            <a href="displayEditSuperpowerForm?superpowerId=${currentSuperpower.superpowerId}">Edit</a>
                             | 
                            <a href="#" onclick="return showDeleteConfirmation('Superpower', '${currentSuperpower.superpowerId}', '${currentSuperpower.name}')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col-md-6">
            <h3>Create Superpower</h3>
            <sf:form class="form-horizontal" role="form" modelAttribute="superpower" action="createSuperpower" method="POST">
                <div class="form-group">
                    <label for="name" class="col-md-2 control-label">Name:</label>
                    <div class="col-md-10">
                        <sf:input type="text" class="form-control" id="name" path="name" placeholder="Superpower Name" maxlength="50" required="required"/>
                        <sf:errors path="name" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">Create Superpower</button>
                    </div>
                </div>
            </sf:form>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" id="delete-confirmation-div">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p>Are you sure you want to delete <span id="modal-text-name"></span>?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a href="#" class="btn btn-primary" role="button" id="delete-confirmation-yes-button">Yes</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"/>