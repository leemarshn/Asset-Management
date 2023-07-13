function del(id) {
    var confirmation = confirm("Are you sure you want to delete this asset?");
    if (confirmation) {
        location.href = '/assets/delete/' + id;
    }
}