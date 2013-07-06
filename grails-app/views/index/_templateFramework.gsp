
<g:each in="${templateFrameworkCollection}" var="templateFramework" status="templateFrameworkIndex">
    <div style="padding-left: 30px; padding-top: 15px;">
        <div style="width: 750px;float: left;">${templateFramework?.templateName}</div>
        <ul style="list-style: none;">
            <g:if test="${templateFramework.templateFrameworkStatus != com.sam.TemplateFrameworkStatus.COMPILED}">
                <li style="display:inline; padding-left: 15px;">
                    <g:link controller="templateFramework" action="compile" id="${templateFramework?.id}">
                        <r:img uri="/images/icon/check.png" width="20" height="20" alt="确认初始模板" title="确认初始模板"/>
                    </g:link>
                </li>
            </g:if>
            <g:if test="${templateFramework?.templateFrameworkStatus == com.sam.TemplateFrameworkStatus.COMPILED}">
                <li style="display:inline; padding-left: 15px;">
                    <a href="" name="createElementGroup_${templateFramework?.id}">
                        <r:img uri="/images/icon/plus.png" width="20" height="20" alt="创建公共组件" title="创建公共组件"/>

                    </a>
                </li>
            </g:if>
            <li style="display:inline; padding-left: 15px;">
                <g:link controller="templateFramework" action="delete" id="${templateFramework?.id}">
                    <r:img uri="/images/icon/delete.png" width="20" height="20" alt="删除模板框架" title="删除模板框架"/>
                </g:link>
            </li>
            <g:if test="${templateFramework?.templateFrameworkStatus == com.sam.TemplateFrameworkStatus.COMPILED && templateFramework?.elementGroupCollection?.size() > 0}">
                <g:if test="${templateFramework.elementGroupCollection.size() > 0}">
                    <li style="display:inline; padding-left: 15px; ">
                        <a href=""  name="displayElementGroupMenuLink_${templateFramework?.id}" templateFrameworkId="${templateFramework?.id}">
                            <r:img uri="/images/icon/config.png" width="20" height="20" alt="编辑公共组件内容" title="编辑公共组件内容"/>

                        </a>
                    </li>
                    <div style="display: none;">
                        <ul id="commonElementGroupMenu_${templateFramework.id}">
                            <g:each in="${templateFramework.elementGroupCollection}" var="i">
                                <li>
                                    <a style="float: left;padding-right: 20px;" href="" name="groupMenuItem_${templateFramework.id}" menuItemId="${i.id}">${i.name}</a>
                                    <a href="" name="deleteMenuItem_${templateFramework.id}" menuItemId="${i.id}">删除</a>
                                    <div style="clear: both;"></div>
                                </li>
                            </g:each>
                        </ul>
                    </div>
                </g:if>

            </g:if>

        </ul>
    </div>
</g:each>
<div id="segmentListDialog" style="display: none;">
    <div>
        <div style="float: left;">
            公共组件群名称:
        </div>
        <div style="padding-left: 150px;">
            <input type="text" id="elementGroupName" style="border: #4169e1;background-color: #dcdcdc;"/>
        </div>
        <div style="clear: both;">

        </div>
    </div>
    <div>
        <table id="segmentListTable">


        </table>
    </div>
    <input type="hidden" id="templateFrameworkId"/>
</div>
<g:link controller="templateFramework" action="getAllSegments" style="display: none;" name="getAllSegmentsLink"/>
<g:link controller="commonElementGroup" action="generateCommonElementGroup" style="display: none;" name="postCommonGroupLink"/>
<g:link controller="commonElementGroup" action="listGroupElement" style="display: none;" name="listCommonGroupElement"/>
<g:link controller="commonElementGroup" action="saveElementContent" style="display: none;" name="editCommonGroupElement"/>
<g:link controller="commonElementGroup" action="deleteCommonElementGroup" style="display: none;" name="deleteCommonElementGroup"/>
<ul id="menuContainer" style="display: none"></ul>
<div id="commonElementAttributeDialog" style="display: none;"></div>
<script type="text/javascript">

    function initElementDialog(data,tempalteFrameworkId) {
        var segmentListTable = $("#segmentListTable");
        $("tr[name*='segmentLine']").remove();
        $(data).each(
                function(index,element) {
                    var content = "<tr name='segmentLine'>" +
                            "<td>" +
                            element.name+"</td>"+
                            "<td><input type='radio' name='segmentRadio_"+ element.segmentId +"'/></td></tr>";
                    segmentListTable.append(content);
                }
        );
        $("#elementGroupName").val("");
        $("#templateFrameworkId").val(tempalteFrameworkId);
        $("#segmentListDialog").dialog("open");
    }


    function initGenerateCommonElement() {
        var getSegmentsLink = $('[name="getAllSegmentsLink"]').attr("href");
        $('a[name*="createElementGroup_"]').each (
                function() {
                    var tempalteFrameworkId = $(this).attr("name").substr(19);
                    $(this).click(
                            function(event) {
                                event.preventDefault();
                                jQuery.ajax(
                                        {type:'GET',
                                            data:{'id': tempalteFrameworkId},
                                            url:getSegmentsLink,
                                            success:
                                                    function(data,textStatus){
                                                        if($(data).length > 0) {
                                                            initElementDialog(data,tempalteFrameworkId);
                                                        }
                                                        else{
                                                            alert("模板不满足生成组件的条件!")
                                                        }
                                                    },
                                            error:function(XMLHttpRequest,textStatus,errorThrown){alert("服务器错误,请重新刷新页面")}}
                                );


                            }
                    )
                }
        )
    }


    $(document).ready(
        function() {

            $("#segmentListDialog").dialog(
                    {
                        autoOpen:false,
                        width:800,
                        modal:true,
                        buttons: {
                            "创建":function(){
                                var contentGroupName = $("#elementGroupName").val(),
                                        checkedSegment="",
                                        templateFrameworkId = $("#templateFrameworkId").val(),
                                        generateGroupLink = $('[name="postCommonGroupLink"]').attr("href");
                                $("input[name*='segmentRadio_']").each(
                                        function() {
                                            if(this.checked) {
                                                checkedSegment+=$(this).attr("name").substring(13)+",";
                                            }
                                        }
                                );
                                if(contentGroupName.length == 0) {
                                    alert("组名不能为空");
                                }
                                else if(checkedSegment.length == 0) {
                                    alert("至少需要选择一个元素");
                                }else {
                                    $.ajax(
                                            {
                                                type:"POST",
                                                async:false,
                                                url:generateGroupLink,
                                                data:{'contentGroupName':contentGroupName,
                                                    'checkedSegment':checkedSegment,
                                                    'templateFrameworkId':templateFrameworkId
                                                },
                                                success:function(data,textStatus) {

                                                    if(data == 'T')
                                                    {
                                                        $("#segmentListDialog").dialog("close");
                                                        alert("创建成功!");
                                                        location.reload();
                                                    }
                                                    else {
                                                        alert(data);
                                                    }
                                                },
                                                error:function() {
                                                    $("#segmentListDialog").dialog("close");
                                                    alert("创建失败,请重新尝试");
                                                }
                                            }
                                    )
                                }
                            },
                            "关闭":function() {
                                $(this).dialog("close");
                            }
                        }
                    }
            );



            if($('a[name*="createElementGroup_"]').length > 0){
                initGenerateCommonElement();
            }
            if($('a[name*="displayElementGroupMenuLink_"]').length > 0){
                initEditCommonElementGroupLink();
            }
        }
    );
</script>



