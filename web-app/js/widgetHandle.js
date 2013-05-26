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


    $(document).click(function(event) {

        if(!event.target.attributes["name"]) {
            $('#menuContainer').hide();
        }
        else if(!event.target.attributes["name"].startwith("groupMenuItem") && !event.target.attributes["name"].startwith("displayElementGroupMenuLink")){
            $('#menuContainer').hide();
        }

    });



    $('a[name*="displayElementGroupMenuLink_"]').each(
        function(i,obj) {
            var templateFrameworkId = $(obj).attr("templateFrameworkId");



            $('#commonElementGroupMenu_'+templateFrameworkId).menu();
            $('#commonElementGroupMenu_' + templateFrameworkId).hide();

            $(obj).click(
                function(event) {
                    event.preventDefault();
                    var objOffset = $(this).offset();
                    $('#menuContainer').html();
                    $('#menuContainer').html($('#commonElementGroupMenu_' + $(this).attr("templateFrameworkId")).html());
                    $('#menuContainer').menu();
                    $('#menuContainer').css("z-index", "99");
                    $('#menuContainer').css("display", "inline");
                    $('#menuContainer').offset({left:objOffset.left + 40, top:objOffset.top + 20});
                    $('#menuContainer').show(2000);

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
