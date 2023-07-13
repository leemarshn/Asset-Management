function deleteAsset(id) {
    var confirmation = prompt("Type 'delete' to confirm deletion:");

    if (confirmation === "delete") {
        // User confirmed the deletion, proceed with the delete request
        // You can make an AJAX request to delete the asset or perform any other necessary actions
        // Here's an example of making an AJAX request using jQuery:
        $.ajax({
            url: '/assets/' + id,
            type: 'DELETE',
            success: function(response) {
                // Handle successful deletion
                alert("Asset deleted successfully");
                // Optionally, you can redirect the user to another page or update the asset list
            },
            error: function(xhr) {
                // Handle error
                alert("Error deleting asset");
            }
        });
    } else {
        // User did not confirm the deletion, do nothing
    }
}