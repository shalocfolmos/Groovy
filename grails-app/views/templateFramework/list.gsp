
<%@ page import="com.sam.TemplateFrameworkStatus; com.sam.TemplateFramework" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'templateFramework.label', default: 'TemplateFramework')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-templateFramework" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-templateFramework" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="templateContent" title="${message(code: 'templateFramework.templateContent.label', default: 'Template Content')}" />
                        <th></th>
                        <th></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${templateFrameworkInstanceList}" status="i" var="templateFrameworkInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: templateFrameworkInstance, field: "templateName")}</td>
                        <td>
                            <g:if test="${templateFrameworkInstance?.templateFrameworkStatus == TemplateFrameworkStatus.UN_COMPILED}">
                                <g:link action="compile" id="${templateFrameworkInstance.id}">
                                    确认模板
                                </g:link>
                            </g:if>

                        </td>
                        <td><g:link class="edit" action="edit" id="${templateFrameworkInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${templateFrameworkInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
