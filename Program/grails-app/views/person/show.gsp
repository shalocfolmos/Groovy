
<%@ page import="program.Person" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-person" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
                <li><a class="home" href="${createLink(controller: "admin",action: "start")}"><g:message code="admin.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="person.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="person.create.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-person" class="content scaffold-show" role="main">
			%{--<h1><g:message code="default.show.label" args="[entityName]" /></h1>--}%
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list person">
			
				<g:if test="${personInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="person.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${personInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="person.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${personInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.division}">
				<li class="fieldcontain">
					<span id="division-label" class="property-label"><g:message code="person.division.label" default="Division" /></span>
					
						<span class="property-value" aria-labelledby="division-label"><g:fieldValue bean="${personInstance}" field="division"/></span>
					
				</li>
				</g:if>
			
				%{--<g:if test="${personInstance?.results}">--}%
				%{--<li class="fieldcontain">--}%
					%{--<span id="results-label" class="property-label"><g:message code="person.results.label" default="Results" /></span>--}%
					%{----}%
						%{--<g:each in="${personInstance.results}" var="r">--}%
						%{--<span class="property-value" aria-labelledby="results-label"><g:link controller="result" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></span>--}%
						%{--</g:each>--}%
					%{----}%
				%{--</li>--}%
				%{--</g:if>--}%
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${personInstance?.id}" />                               					<g:link class="edit" action="edit" id="${personInstance?.id}"><g:message code="person.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'person.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
