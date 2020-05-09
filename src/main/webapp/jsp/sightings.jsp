<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="_header.jsp">
    <jsp:param name="title" value="Sightings"/>
</jsp:include>
<div class="container">
    <div class="page-header">
        <h1>Sightings</h1>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-danger fade in alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong> <c:out value="${message}"/>
        </div>
    </c:if>
    <p><a href="displayCreateSightingForm" class="btn btn-primary" role="button">Create Sighting</a></p>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th width="20%">Date/Time</th>
                    <th width="40%">Location</th> 
                    <th width="25%">Heroes</th>
                    <th width="15%"></th>
                </tr>
                <c:forEach var="currentSighting" items="${sightingList}">
                    <tr>
                        <td>
                            <a href="displaySightingDetails?sightingId=${currentSighting.sightingId}"><c:out value="${localDateTimeFormat.format(currentSighting.dateTime)}"/></a>
                        </td>
                        <td>
                            <c:out value="${currentSighting.location.streetAddress}"/>, <c:out value="${currentSighting.location.city}"/>, <c:out value="${currentSighting.location.state.name}"/> <c:out value="${currentSighting.location.zipCode}"/>
                        </td>
                        <td>
                            <c:forEach var="currentHero" items="${currentSighting.heroes}" varStatus="loop">
                                <c:out value="${currentHero.name}"/><c:if test="${!loop.last}">, </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">Edit</a>
                             | 
                            <a href="#" onclick="return showDeleteConfirmation('Sighting', '${currentSighting.sightingId}', '${localDateTimeFormat.format(currentSighting.dateTime)}')">Delete</a>
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
                <p>Are you sure you want to delete the <span id="modal-text-name"></span> sighting?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a href="#" class="btn btn-primary" role="button" id="delete-confirmation-yes-button">Yes</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"/>