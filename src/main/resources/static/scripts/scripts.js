function del(id) {
    var confirmation = confirm("Are you sure you want to delete this asset?");
    if (confirmation) {
        location.href = '/assets/delete/' + id;
    }
}

document.getElementById('category').addEventListener('change', function() {
    if (this.value === '') {
        window.location.href = '/add-category';
    }
});
