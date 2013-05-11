
<%@ page import="com.sam.CommonElementAttribute" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-commonElementAttribute" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-commonElementAttribute" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="attribute_type" title="${message(code: 'commonElementAttribute.attribute_type.label', default: 'Attributetype')}" />
					
						<g:sortableColumn property="attribute_value" title="${message(code: 'commonElementAttribute.attribute_value.label', default: 'Attributevalue')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${commonElementAttributeInstanceList}" status="i" var="commonElementAttributeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${commonElementAttributeInstance.id}">${fieldValue(bean: commonElementAttributeInstance, field: "attribute_type")}</g:link></td>
					
						<td>${fieldValue(bean: commonElementAttributeInstance, field: "attribute_value")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${commonElementAttributeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
