

function initEditCommonElementGroupLink() {

    $(document).click(function(event) {

        if(!event.target.attributes["name"]) {
            $('#menuContainer').hide();
            $('#menuContainer').menu("destroy");
            $('#menuContainer').html();
        }
        else if(!event.target.name.startsWith("groupMenuItem") && !event.target.name.startsWith("displayElementGroupMenuLink")
            && !event.target.name.startsWith("deleteMenuItem_")){
            $('#menuContainer').hide();
            $('#menuContainer').menu("destroy");
            $('#menuContainer').html();
        }
        else if(event.target.name.startsWith("groupMenuItem")){
            event.preventDefault();
            $('#menuContainer').hide();
            showElementGroupPanel(event.target.attributes["menuitemid"].nodeValue);
        }
        else if(event.target.name.startsWith("deleteMenuItem_")){
            event.preventDefault();
            $('#menuContainer').hide();
            deleteElementGroup(event.target.attributes["menuitemid"].nodeValue);
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
                    $('#menuContainer').menu("destroy");
                    $('#menuContainer').html($('#commonElementGroupMenu_' + $(this).attr("templateFrameworkId")).html());
                    $('#menuContainer').menu();
                    $('#menuContainer').css("z-index", "99");
                    $('#menuContainer').css("display", "inline");
                    $('#menuContainer').offset({left:objOffset.left + 40, top:objOffset.top + 20});
                    $('#menuContainer').show(2000);

                }
            );

        }
    )
}

function deleteElementGroup(groupId){
    var deleteElementGroupLink = $('[name="deleteCommonElementGroup"]').attr("href");
    $.ajax(
        {
            type:"POST",
            async:false,
            url:deleteElementGroupLink,
            data:{'id':groupId},
            success:function(data,textStatus) {
                alert(data);
                location.reload();
            },
            error:function() {
                alert("服务器异常,重新尝试!");
            }
        }
    );
}



function showElementGroupPanel (groupId) {

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
                $("#commonElementAttributeDialog").html(data);
                $("#commonElementAttributeDialog").dialog(
                    {
                        autoOpen:false,
                        width:800,
                        modal:true,
                        buttons: {
                            "创建":function(){

                                var parameter = "",
                                editCommonGroupElementLink = $("a[name='editCommonGroupElement']").attr("href");
                                $('div[name*="common_attribute_"]').each(
                                    function() {
                                        parameter+=$(this).attr("attribute_id")+":"+$("[name='common_attribute_value_" + $(this).attr("attribute_id") + "']").attr("value");
                                        parameter += ",";
                                    }
                                );

                                $.ajax(
                                    {
                                        type:"POST",
                                        url:editCommonGroupElementLink,
                                        data:{content:parameter},
                                        success:function(data) {
                                            alert(data);
                                        },
                                        error:function() {
                                            alert("服务器异常,重新尝试!");
                                        }
                                    }

                                );

                                $("#commonElementAttributeDialog").dialog("close");
                            },
                            "关闭":function() {
                                $("#commonElementAttributeDialog").dialog("close");
                            }
                        }
                    }
                );
                $("#commonElementAttributeDialog").dialog("open");

            },
            error:function() {
                alert("服务器异常,重新尝试!");
            }
        }
    )
}





