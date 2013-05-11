
<%@ page import="com.sam.TemplateFramework" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'templateFramework.label', default: 'TemplateFramework')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-templateFramework" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-templateFramework" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list templateFramework">
				<g:if test="${templateFrameworkInstance?.segments}">
				<li class="fieldcontain">
					<span id="segments-label" class="property-label"><g:message code="templateFramework.segments.label" default="Segments" /></span>
					
						<g:each in="${templateFrameworkInstance.segments}" var="s">
						<span class="property-value" aria-labelledby="segments-label"><g:link controller="segment" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
				<g:if test="${templateFrameworkInstance?.templateName}">
				<li class="fieldcontain">
					<span id="templateName-label" class="property-label"><g:message code="templateFramework.templateName.label" default="Template Name" /></span>
					
						<span class="property-value" aria-labelledby="templateName-label"><g:fieldValue bean="${templateFrameworkInstance}" field="templateName"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${templateFrameworkInstance?.id}" />
					<g:link class="edit" action="edit" id="${templateFrameworkInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
