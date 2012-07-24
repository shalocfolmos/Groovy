<%@ page import="program.Person" %>



<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="person.username.label" default="Username" />
		
	</label>
	<g:textField name="username" maxlength="30" value="${personInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'password', 'error')} ">
	<label for="password">
		<g:message code="person.password.label" default="Password" />
		
	</label>
	<g:textField name="password" maxlength="80" value="${personInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'division', 'error')} ">
	<label for="division">
		<g:message code="person.division.label" default="Division" />
		
	</label>
	<g:textField name="division" maxlength="30" value="${personInstance?.division}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'results', 'error')} ">
	<label for="results">
		<g:message code="person.results.label" default="Results" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${personInstance?.results?}" var="r">
    <li><g:link controller="result" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="result" action="create" params="['person.id': personInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'result.label', default: 'Result')])}</g:link>
</li>
</ul>

</div>

