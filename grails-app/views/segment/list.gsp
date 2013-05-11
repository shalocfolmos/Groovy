
<%@ page import="com.sam.Segment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'segment.label', default: 'Segment')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-segment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-segment" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="elementId" title="${message(code: 'segment.elementId.label', default: 'Element Id')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'segment.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="segmentType" title="${message(code: 'segment.segmentType.label', default: 'Segment Type')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${segmentInstanceList}" status="i" var="segmentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${segmentInstance.id}">${fieldValue(bean: segmentInstance, field: "elementId")}</g:link></td>
					
						<td>${fieldValue(bean: segmentInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: segmentInstance, field: "segmentType")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${segmentInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
