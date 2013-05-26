function initEditCommonElementGroupLink() {

    $('a[name*="editCommonElementGroupLink_"]').each(
        function(i,obj) {
            $(obj).click(
                function(event) {
                    event.preventDefault();
                    alert($(this).attr("groupId"));
                }
            );
        }
    )


    $('a[name*="displayElementGroupMenuLink_"]').each(
        function(i,obj) {
            var templateFrameworkId = $(obj).attr("templateFrameworkId");



            $('#commonElementGroupMenu_'+templateFrameworkId).menu();
            $('#commonElementGroupMenu_' + templateFrameworkId).hide();

            $(obj).click(
                function(event) {
                    event.preventDefault();
                    var objOffset = $(this).offset();
                    $('#commonElementGroupMenu_' + $(this).attr("templateFrameworkId")).css("z-index", "99");
                    $('#commonElementGroupMenu_' + $(this).attr("templateFrameworkId")).css("display", "inline");
                    $('#commonElementGroupMenu_' + $(this).attr("templateFrameworkId")).offset({left:objOffset.left + 40, top:objOffset.top + 20});
                    $('#commonElementGroupMenu_' + $(this).attr("templateFrameworkId")).show(2000);

                }
            );
            $('a[name="groupMenuItem_'+ templateFrameworkId +'"]').each(
                function() {
                    $(this).click(
                        function(event){
                            event.preventDefault();
                            $('#commonElementGroupMenu_' + templateFrameworkId).hide();
                        }
                    );
                }
            )

        }
    )

//
//
//
//    $('a[name*="editTemplateFrameworkElementGroupLink_"]').click(
//        function(event) {
//            event.preventDefault();
//            var elementGroupId = $(this).attr("groupId");
//            alert(elementGroupId);
//        }
//
//
//    );
//
//
//    $('ul[name*="templateFramework_menu_"]').each(
//        function() {
//            $(this).menu();
//        }
//    )
}

function displayCommonElementContent(commonElementGroupId) {
    alert("commonElementGroupId--" + commonElementGroupId);
}
