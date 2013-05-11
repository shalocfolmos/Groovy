
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
                <a href="" id="createCommonElement" onclick="selectAllSegments()">
                    创建公共组件
                </a>
            </g:if>
            <li style="display:inline; padding-left: 15px;">
                <g:link controller="templateFramework" action="delete" id="${templateFramework?.id}">删除模板框架</g:link>
            </li>
        </ul>
    </div>

</g:each>


