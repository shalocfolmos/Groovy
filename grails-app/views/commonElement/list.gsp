
<%@ page import="com.sam.CommonElement" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'commonElement.label', default: 'CommonElement')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-commonElement" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-commonElement" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'commonElement.name.label', default: 'Name')}" />
					
						<th><g:message code="commonElement.segment.label" default="Segment" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${commonElementInstanceList}" status="i" var="commonElementInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${commonElementInstance.id}">${fieldValue(bean: commonElementInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: commonElementInstance, field: "segment")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${commonElementInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
