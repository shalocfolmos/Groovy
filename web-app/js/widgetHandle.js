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

            $(obj).click(
                function(event) {
                    event.preventDefault();

                }
            );
            $('a[name="groupMenuItem_"'+ templateFrameworkId +']').each(
                function() {
                    $(this).click(
                        function(event){
                            event.preventDefault();
                            alert("v");
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
