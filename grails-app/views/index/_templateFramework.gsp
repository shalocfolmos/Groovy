
<g:each in="${templateFrameworkCollection}" var="templateFramework" status="templateFrameworkIndex">
    <div style="padding-left: 30px; padding-top: 15px;">
        <div style="width: 500px;float: left;">${templateFramework?.templateName}</div>
        <ul style="list-style: none;">
            <g:if test="${templateFramework.templateFrameworkStatus != com.sam.TemplateFrameworkStatus.COMPILED}">
                <li style="display:inline; padding-left: 15px;">
                    <g:link controller="templateFramework" action="compile" id="${templateFramework?.id}">确认模板框架</g:link>
                    <g:link controller="templateFramework" action="edit" id="${templateFramework?.id}">编辑模板框架</g:link>
                </li>
            </g:if>
            <g:if test="${templateFramework?.templateFrameworkStatus == com.sam.TemplateFrameworkStatus.COMPILED}">
                <a href="" name="createElementGroup_${templateFramework?.id}">
                    创建公共组件
                </a>
            </g:if>
            <li style="display:inline; padding-left: 15px;">
                <g:link controller="templateFramework" action="delete" id="${templateFramework?.id}">删除模板框架</g:link>
            </li>
            <g:if test="${templateFramework?.templateFrameworkStatus == com.sam.TemplateFrameworkStatus.COMPILED && templateFramework.elementGroupCollection.size() > 0}">
                <g:if test="${templateFramework.elementGroupCollection.size() == 1}">
                    <li style="display:inline; padding-left: 15px;">
                        <a href="" name="editCommonElementGroupLink_${templateFramework.id}" groupId="${templateFramework.elementGroupCollection[0].id}">
                            编辑组件内容
                        </a>
                    </li>
                </g:if>
                <g:if test="${templateFramework.elementGroupCollection.size() > 1}">
                    <li style="display:inline; padding-left: 15px; ">
                        <a href=""  name="displayElementGroupMenuLink_${templateFramework?.id}" templateFrameworkId="${templateFramework?.id}">
                            编辑组件内容
                        </a>
                    </li>
                    <div style="display: none;">
                        <ul id="commonElementGroupMenu_${templateFramework.id}">
                            <g:each in="${templateFramework.elementGroupCollection}" var="i">
                                <li><a href="" name="groupMenuItem_${templateFramework.id}" menuItemId="${i.id}">${i.name}</a></li>
                            </g:each>
                        </ul>
                    </div>
                </g:if>

            </g:if>

        </ul>
    </div>
</g:each>
<ul id="menuContainer" style="display: none">


</ul>
<script type="text/javascript">
    $(document).ready(
        function() {
            if($('a[name*="createElementGroup_"]').length > 0){
                initGenerateCommonElement();
            }
            if($('a[name*="displayElementGroupMenuLink_"]').length > 0 || $('a[name*="editCommonElementGroupLink_"]').length > 0){
                initEditCommonElementGroupLink();
            }
        }
    );
</script>



