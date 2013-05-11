
<%@ page import="com.sam.Template" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'template.label', default: 'Template')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-template" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-template" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list template">
			
				<g:if test="${templateInstance?.templateFramework}">
				<li class="fieldcontain">
					<span id="templateFramework-label" class="property-label"><g:message code="template.templateFramework.label" default="Template Framework" /></span>

						<span class="property-value" aria-labelledby="templateFramework-label"><g:link controller="templateFramework" action="show" id="${templateInstance?.templateFramework?.id}">${templateInstance?.templateFramework?.templateName?.encodeAsHTML()}</g:link></span>

				</li>
				</g:if>
			
				<g:if test="${templateInstance?.templateTitle}">
				<li class="fieldcontain">
					<span id="templateTitle-label" class="property-label"><g:message code="template.templateTitle.label" default="Template Title" /></span>
					
						<span class="property-value" aria-labelledby="templateTitle-label"><g:fieldValue bean="${templateInstance}" field="templateTitle"/></span>
					
				</li>
				</g:if>
			%{----}%
				%{--<g:if test="${templateInstance?.templateElements}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="templateElements-label" class="property-label"><g:message code="template.templateElements.label" default="Template Elements" /></span>--}%
						%{--<g:each in="${templateInstance.templateElements}" var="t">--}%
                            %{--<span class="property-value" aria-labelledby="templateElements-label">--}%
                                %{--${t?.segment.name.encodeAsHTML()}--}%
                            %{--</span>--}%
						%{--</g:each>--}%
				%{--</li>--}%
				%{--</g:if>--}%
			
				<g:if test="${templateInstance?.finalTemplateContent}">
				<li class="fieldcontain">
					<span id="finalTemplateContent-label" class="property-label"><g:message code="template.finalTemplateContent.label" default="Final Template Content" /></span>
					
						<span class="property-value" aria-labelledby="finalTemplateContent-label"><g:fieldValue bean="${templateInstance}" field="finalTemplateContent"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${templateInstance?.id}" />
					<g:link class="edit" action="edit" id="${templateInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:link action="editElement" id="${templateInstance?.id}"><g:message code="template.element.label" default="添加模板内容"/></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
