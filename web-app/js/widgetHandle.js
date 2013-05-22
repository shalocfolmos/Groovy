function initCommentElementMenu() {
    $('ul[name*="templateFramework_menu_"]').each(
        function() {
            $(this).menu();
        }
    )
}
