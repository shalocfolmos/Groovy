
<g:each in="${templateCollection}" var="template" status="templateIndex">
    <div style="padding-left: 30px; padding-top: 15px;">
        <div style="width: 600px;float: left;">${template?.templateTitle}</div>
        <ul style="list-style: none;">
            <g:if test="${template.templateStatus != com.sam.TemplateStatus.CONFIRMED}">
                <li style="display:inline; padding-left: 15px;">
                    <g:link controller="template" action="editElement" id="${template?.id}">
                        <r:img uri="/images/icon/pen.png" width="20" height="20" alt="编辑模板内容" title="编辑模板内容"/>
                    </g:link>
                </li>
                <li style="display:inline; padding-left: 15px;">
                    <g:link controller="template" action="confirmTemplate" id="${template?.id}">
                        <r:img uri="/images/icon/world.png" width="20" height="20" alt="模板编辑完成" title="模板编辑完成"/>
                    </g:link>
                </li>
                <li style="display:inline; padding-left: 15px;">
                    <a href="" name="applyCommonElementGroupLink_${template.id}" templateId="${template.id}">
                        <r:img uri="/images/icon/plus.png" width="20" height="20" alt="应用公共元素组" title="应用公共元素组"/>
                    </a>
                </li>
            </g:if>
            <g:if test="${template.templateStatus == com.sam.TemplateStatus.CONFIRMED}">
                <li style="display:inline; padding-left: 15px;"><g:link controller="template" action="exportTemplateContent" id="${template?.id}">导出模板内容</g:link> </li>
            </g:if>
            <li style="display:inline; padding-left: 15px;">
                <g:link controller="template" action="delete" id="${template?.id}">
                    <r:img uri="/images/icon/delete.png" width="20" height="20" alt="删除邮件模板" title="删除邮件模板"/>
                </g:link> </li>
        </ul>
    </div>
</g:each>
<g:link style="display: none;" controller="commonElementGroup" action="getCommonElementGroup" name="getCommonElementGroupLink"></g:link>
<g:link style="display: none;" controller="template" action="applyCommonElementGroup" name="applyCommonElementGroupLink"></g:link>
<ul id="applyElementGroupMenu" style="display: none"></ul>
<script type="text/javascript">
    function applyCommonElementGroup(commonElementGroupId,templateId) {
        var applyCommonElementGroupLink = $("a[name='applyCommonElementGroupLink']").attr("href");
        jQuery.ajax(
                {type:'GET',
                    data:{'commonElementGroupId': commonElementGroupId,
                    'templateId':templateId},
                    url:applyCommonElementGroupLink,
                    success:
                            function(data,textStatus){
                                alert(data);
                            },
                    error:function(XMLHttpRequest,textStatus,errorThrown){alert("服务器错误,请重新刷新页面")}}
        );

    }

    $(document).click(function(event) {

        if(!event.target.attributes["name"]) {
            $('#applyElementGroupMenu').hide();
        }
        else if(!event.target.name.startsWith("getCommonElementGroupLink")){
            $('#applyElementGroupMenu').hide();
        }
    })

    $(document).ready(
        function(event) {
            $('a[name*="applyCommonElementGroupLink_"]').each (
                function () {
                    $(this).click(
                        function(event)
                        {
                            event.preventDefault();
                            var templateId = $(this).attr("templateId"),
                            getCommonElementGroupLink = $('a[name="getCommonElementGroupLink"]').attr("href");
                            var objOffset = $(this).offset();
                            jQuery.ajax(
                                    {type:'GET',
                                        data:{'templateId': templateId},
                                        url:getCommonElementGroupLink,
                                        success:
                                                function(data,textStatus){
                                                    $('#applyElementGroupMenu').menu("destroy");
                                                    $('#applyElementGroupMenu').html("");
                                                    $('#applyElementGroupMenu').html(data);
                                                    $('#applyElementGroupMenu').menu();
                                                    $('#applyElementGroupMenu').css("z-index", "99");
                                                    $('#applyElementGroupMenu').css("display", "inline");
                                                    $('#applyElementGroupMenu').offset({left:objOffset.left + 40, top:objOffset.top + 20});
                                                    $('#applyElementGroupMenu').show(2000);
                                                },
                                        error:function(XMLHttpRequest,textStatus,errorThrown){alert("服务器错误,请重新刷新页面")}}
                            );
                        }
                    );
                }
            );

        }
    )
</script>