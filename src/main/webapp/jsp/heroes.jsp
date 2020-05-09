<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Heroes/Villains"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Heroes/Villains</h1>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-danger fade in alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong> <c:out value="${message}"/>
        </div>
    </c:if>
    <p><a href="displayCreateHeroForm" class="btn btn-primary" role="button">Create Hero/Villain</a></p>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th width="15%">Hero</th>
                    <th width="70%">Description</th>
                    <th width="15%"></th>
                </tr>
                <c:forEach var="currentHero" items="${heroList}">
                    <tr>
                        <td>
                            <a href="displayHeroDetails?heroId=${currentHero.heroId}"><c:out value="${currentHero.name}"/></a>
                        </td>
                        <td>
                            <c:out value="${currentHero.description}"/>
                        </td>
                        <td>
                            <a href="displayEditHeroForm?heroId=${currentHero.heroId}">Edit</a>
                             | 
                            <a href="#" onclick="return showDeleteConfirmation('Hero', '${currentHero.heroId}', '${currentHero.name}')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
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