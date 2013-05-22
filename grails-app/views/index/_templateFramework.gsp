
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
                    <li>
                        <a href="" name="">
                            编辑组件内容
                        </a>
                        <ul name="templateFramework_menu_${templateFramework.id}" id="jqueryMenu">
                            <g:each in="${templateFramework.elementGroupCollection}" var="i">
                                `<li>${i.name}</li>
                            </g:each>
                        </ul>
                    </li>
                </g:if>
                <g:if test="${templateFramework.elementGroupCollection.size() > 1}">
                    <ul name="templateFramework_menu_${templateFramework.id}" id="jqueryMenu">
                        <g:each in="${templateFramework.elementGroupCollection}" var="i">
                            `<li>${i.name}</li>
                        </g:each>
                    </ul>
                </g:if>

            </g:if>

        </ul>
    </div>

</g:each>
<g:javascript library="jquery"/>
<script type="text/javascript">
    $(document).ready(
        function() {
            if($('a[name*="createElementGroup_"]').length > 0){
                initGenerateCommonElement();
            }
        }

        function() {
            if($('ul[name*="templateFramework_menu_"]').length > 0){
                initCommentElementMenu();
            }
        }
    );
</script>



