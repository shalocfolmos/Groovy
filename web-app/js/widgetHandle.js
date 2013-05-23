function initCommentElementMenu() {

    $('a[name*="editElementMultipleLink_"]').click(
        function(event) {
            event.preventDefault();
            var commonElementGroupId = $(this).attr("name").substr(23);
            alert(commonElementGroupId);
            displayCommonElementContent(commonElementGroupId);
        }

    );


    $('a[name*="editElementSingleLink_"]').click(
        function(event) {
            event.preventDefault();
        }

    );


    $('ul[name*="templateFramework_menu_"]').each(
        function() {
            $(this).menu();
        }
    )
}

function displayCommonElementContent(commonElementGroupId) {
    alert("commonElementGroupId--" + commonElementGroupId);
}
