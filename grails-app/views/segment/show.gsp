
<%@ page import="com.sam.Segment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'segment.label', default: 'Segment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-segment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-segment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list segment">
			
				<g:if test="${segmentInstance?.elementId}">
				<li class="fieldcontain">
					<span id="elementId-label" class="property-label"><g:message code="segment.elementId.label" default="Element Id" /></span>
					
						<span class="property-value" aria-labelledby="elementId-label"><g:fieldValue bean="${segmentInstance}" field="elementId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${segmentInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="segment.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${segmentInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${segmentInstance?.segmentType}">
				<li class="fieldcontain">
					<span id="segmentType-label" class="property-label"><g:message code="segment.segmentType.label" default="Segment Type" /></span>
					
						<span class="property-value" aria-labelledby="segmentType-label"><g:fieldValue bean="${segmentInstance}" field="segmentType"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${segmentInstance?.id}" />
					<g:link class="edit" action="edit" id="${segmentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
