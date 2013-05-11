<%@ page import="com.sam.TemplateFramework" %>

<div class="fieldcontain ${hasErrors(bean: templateFrameworkInstance, field: 'templateName', 'error')} ">
	<label for="templateName">
		<g:message code="templateFramework.templateName.label"/>
	</label>
	<g:textField  name="templateName" value="${templateFrameworkInstance?.templateName}" size="50"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateFrameworkInstance, field: 'htmlContent', 'error')} ">
    <label for="htmlContent">
        模板内容(HTML代码)
    </label>
    <g:textArea  escapeHtml="true" name="htmlContent" value="${templateFrameworkInstance?.htmlContent}" style="width: 400px; height:300px;"/>
</div>

