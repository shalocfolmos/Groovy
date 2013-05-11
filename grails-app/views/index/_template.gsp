
<g:each in="${templateCollection}" var="template" status="templateIndex">
    <div style="padding-left: 30px; padding-top: 15px;">
        <div style="width: 500px;float: left;">${template?.templateTitle}</div>
        <ul style="list-style: none">
            <g:if test="${template.templateStatus != com.sam.TemplateStatus.CONFIRMED}">
                <li style="display:inline; padding-left: 15px;"><g:link controller="template" action="editElement" id="${template?.id}">编辑模板内容</g:link> </li>
                <li style="display:inline; padding-left: 15px;"><g:link controller="template" action="confirmTemplate" id="${template?.id}">模板编辑完成</g:link> </li>
            </g:if>
            <g:if test="${template.templateStatus == com.sam.TemplateStatus.CONFIRMED}">
                <li style="display:inline; padding-left: 15px;"><g:link controller="template" action=" exportTemplateContent" id="${template?.id}">导出模板内容</g:link> </li>
            </g:if>
            <li style="display:inline; padding-left: 15px;"><g:link controller="template" action="delete" id="${template?.id}">删除邮件模板</g:link> </li>
        </ul>
    </div>
</g:each>