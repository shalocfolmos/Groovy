<%@ page import="com.sam.TemplateFramework" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'templateFramework.label', default: 'TemplateFramework')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-templateFramework" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="create-templateFramework" class="content scaffold-create" role="main">
			<h1>创建初始模板</h1>
			<g:if test="${flash.message}">
			    <div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${templateFrameworkInstance}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${templateFrameworkInstance}" var="error">

                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
			</g:hasErrors>
        <g:uploadForm action="save">
            <fieldset class="form">
                <g:render template="form"/>
            </fieldset>
            <fieldset class="buttons">
                <g:submitButton name="create" class="save" value="创建模板框架" />
            </fieldset>
        </g:uploadForm>

		</div>
	</body>
</html>
