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
            $('#menuContainer').menu("destroy");
            $('#menuContainer').html();
        }
        else if(!event.target.name.startsWith("groupMenuItem") && !event.target.name.startsWith("displayElementGroupMenuLink")){
            $('#menuContainer').hide();
            $('#menuContainer').menu("destroy");
            $('#menuContainer').html();
        }
        else if(event.target.name.startsWith("groupMenuItem")){
            event.preventDefault;
            showElementGroupPanel(event.target.attributes["menuitemid"].nodeValue);
        }

    });



    $('a[name*="displayElementGroupMenuLink_"]').each(
        function(i,obj) {
            var templateFrameworkId = $(obj).attr("templateFrameworkId");



            $(obj).click(
                function(event) {
                    event.preventDefault();
                    var objOffset = $(this).offset();
                    $('#menuContainer').html("");
                    $('#menuContainer').html($('#commonElementGroupMenu_' + $(this).attr("templateFrameworkId")).html());
                    $('#menuContainer').menu();
                    $('#menuContainer').css("z-index", "99");
                    $('#menuContainer').css("display", "inline");
                    $('#menuContainer').offset({left:objOffset.left + 40, top:objOffset.top + 20});
                    $('#menuContainer').show(2000);

                }
            );
//            $('a[name="groupMenuItem_'+ templateFrameworkId +'"]').each(
//                function() {
//                    $(this).click(
//                        function(event){
//                            event.preventDefault();
//                            showElementGroupPanel($(this).attr("menuItemId"));
//                        }
//                    );
//                }
//            )

        }
    )


}

function showElementGroupPanel (groupId) {
    $('#menuContainer').hide();
    var listElementLink = $('[name="listCommonGroupElement"]').attr("href");
    $.ajax(
        {
            type:"POST",
            async:false,
            url:listElementLink,
            data:{'id':groupId},
            success:function(data,textStatus) {
                $("#commonElementAttributeDialog").dialog("destroy");
                $("#commonElementAttributeDialog").html();
                $("#commonElementAttributeDialog").html("test");
                $("#commonElementAttributeDialog").dialog(
                    {
                        autoOpen:false,
                        width:800,
                        modal:true,
                        buttons: {
                            "创建":function(){
                                $("#segmentListDialog").dialog("close");
                            },
                            "关闭":function() {
                                $(this).dialog("close");
                            }
                        }
                    }
                );
                $("#commonElementAttributeDialog").dialog("open");

            },
            error:function() {
            }
        }
    )
}





