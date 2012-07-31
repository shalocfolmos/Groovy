
<%@ page import="program.Result" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'result.label', default: 'Result')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-result" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
                <li><a class="home" href="${createLink(controller: 'admin',action: 'resultList')}"><g:message code="admin.home.label"/></a></li>
				<li><g:link class="create" controller="person"  action="list"><g:message code="person.list.label" default="会员列表" /></g:link></li>
			</ul>
		</div>
		<div id="list-result" class="content scaffold-list" role="main">
			%{--<h1><g:message code="default.list.label" args="[entityName]" /></h1>--}%
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="consumeName" title="${message(code: 'result.consumeName.label', default: 'Consume Name')}" />
					
						<g:sortableColumn property="contact" title="${message(code: 'result.contact.label', default: 'Contact')}" />
					
						<g:sortableColumn property="creditCard" title="${message(code: 'result.creditCard.label', default: 'Credit Card')}" />
					
						<g:sortableColumn property="saleDate" title="${message(code: 'result.saleDate.label', default: 'Sale Date')}" />
					
						<g:sortableColumn property="phone" title="${message(code: 'result.phone.label', default: 'Phone')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'd', default: '创建人')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${resultInstanceList}" status="i" var="resultInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="resultShow" id="${resultInstance.id}">${fieldValue(bean: resultInstance, field: "consumeName")}</g:link></td>

						<td>${fieldValue(bean: resultInstance, field: "contact")}</td>

						<td>${fieldValue(bean: resultInstance, field: "creditCard")}</td>

						<td><g:formatDate date="${resultInstance.saleDate}" /></td>

						<td>${fieldValue(bean: resultInstance, field: "phone")}</td>

						<td><g:link action="show" controller="person" id="${resultInstance.person.id}">${fieldValue(bean: resultInstance, field: "person.username")}</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${resultInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
