
<%@ page import="com.sam.CommonElementAttribute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-commonElementAttribute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-commonElementAttribute" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list commonElementAttribute">
			
				<g:if test="${commonElementAttributeInstance?.attribute_type}">
				<li class="fieldcontain">
					<span id="attribute_type-label" class="property-label"><g:message code="commonElementAttribute.attribute_type.label" default="Attributetype" /></span>
					
						<span class="property-value" aria-labelledby="attribute_type-label"><g:fieldValue bean="${commonElementAttributeInstance}" field="attribute_type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${commonElementAttributeInstance?.attribute_value}">
				<li class="fieldcontain">
					<span id="attribute_value-label" class="property-label"><g:message code="commonElementAttribute.attribute_value.label" default="Attributevalue" /></span>
					
						<span class="property-value" aria-labelledby="attribute_value-label"><g:fieldValue bean="${commonElementAttributeInstance}" field="attribute_value"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${commonElementAttributeInstance?.id}" />
					<g:link class="edit" action="edit" id="${commonElementAttributeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
