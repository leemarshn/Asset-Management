// function del(id) {
//     var confirmation = confirm("Are you sure you want to delete this asset?");
//     if (confirmation) {
//         location.href = '/assets/delete/' + id;
//     }
// }
//

function deleteCategory(id) {
    var confirmation = confirm("Are you sure you want to delete this Category?");
    if (confirmation) {
        $.ajax({
            url: '/category/delete/' + id,
            type: 'DELETE',
            success: function(response) {
                if (response.success) {
                    // Display success message and update the list
                    alert("Category deleted successfully!");
                    // Reload the page or update the category list using DOM manipulation
                    location.reload();
                } else {
                    // Handle error gracefully
                    alert(response.error);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // Handle AJAX errors
                console.error("AJAX error:", textStatus, errorThrown);
                alert("An error occurred while deleting the category. Please try again.");
            }
        });
    }
}
