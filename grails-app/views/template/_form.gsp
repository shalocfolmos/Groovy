<%@ page import="com.sam.TemplateFrameworkStatus; com.sam.Template" %>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'templateTitle', 'error')} ">
    <label for="templateTitle">
        <g:message code="template.templateTitle.label" default="Template Title" />
    </label>
    <g:textField name="templateTitle" value="${templateInstance?.templateTitle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateInstance, field: 'templateFramework', 'error')} required">
	<label for="templateFramework">
		<g:message code="template.templateFramework.label" default="Template Framework" />
		<span class="required-indicator">*</span>
	</label>
	<g:select style="width: 190px" id="templateFramework" name="templateFramework.id" from="${com.sam.TemplateFramework.findAllByTemplateFrameworkStatus(TemplateFrameworkStatus.COMPILED)}" optionKey="id" required="" optionValue="templateName" value="${templateInstance?.templateFramework?.id}" class="many-to-one"/>
</div>
