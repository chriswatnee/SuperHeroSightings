// Show delete confirmation
function showDeleteConfirmation(type, id, name) {
    $('#delete-confirmation-yes-button').attr('href', 'delete' + type + '?' + type.toLowerCase() + 'Id=' + id);
    $('#modal-text-name').text(name);
    $('#delete-confirmation-div').modal('show');
    return false;
}