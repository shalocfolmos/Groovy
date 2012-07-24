
<%@ page import="program.Result" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'result.label', default: 'Result')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-result" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-result" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list result">
			
				<g:if test="${resultInstance?.consumeName}">
				<li class="fieldcontain">
					<span id="consumeName-label" class="property-label"><g:message code="result.consumeName.label" default="Consume Name" /></span>
					
						<span class="property-value" aria-labelledby="consumeName-label"><g:fieldValue bean="${resultInstance}" field="consumeName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.contact}">
				<li class="fieldcontain">
					<span id="contact-label" class="property-label"><g:message code="result.contact.label" default="Contact" /></span>
					
						<span class="property-value" aria-labelledby="contact-label"><g:fieldValue bean="${resultInstance}" field="contact"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.creditCard}">
				<li class="fieldcontain">
					<span id="creditCard-label" class="property-label"><g:message code="result.creditCard.label" default="Credit Card" /></span>
					
						<span class="property-value" aria-labelledby="creditCard-label"><g:fieldValue bean="${resultInstance}" field="creditCard"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.saleDate}">
				<li class="fieldcontain">
					<span id="saleDate-label" class="property-label"><g:message code="result.saleDate.label" default="Sale Date" /></span>
					
						<span class="property-value" aria-labelledby="saleDate-label"><g:formatDate date="${resultInstance?.saleDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.phone}">
				<li class="fieldcontain">
					<span id="phone-label" class="property-label"><g:message code="result.phone.label" default="Phone" /></span>
					
						<span class="property-value" aria-labelledby="phone-label"><g:fieldValue bean="${resultInstance}" field="phone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="result.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${resultInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.postCode}">
				<li class="fieldcontain">
					<span id="postCode-label" class="property-label"><g:message code="result.postCode.label" default="Post Code" /></span>
					
						<span class="property-value" aria-labelledby="postCode-label"><g:fieldValue bean="${resultInstance}" field="postCode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.postName}">
				<li class="fieldcontain">
					<span id="postName-label" class="property-label"><g:message code="result.postName.label" default="Post Name" /></span>
					
						<span class="property-value" aria-labelledby="postName-label"><g:fieldValue bean="${resultInstance}" field="postName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.enterprise}">
				<li class="fieldcontain">
					<span id="enterprise-label" class="property-label"><g:message code="result.enterprise.label" default="Enterprise" /></span>
					
						<span class="property-value" aria-labelledby="enterprise-label"><g:fieldValue bean="${resultInstance}" field="enterprise"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.productType}">
				<li class="fieldcontain">
					<span id="productType-label" class="property-label"><g:message code="result.productType.label" default="Product Type" /></span>
					
						<span class="property-value" aria-labelledby="productType-label"><g:fieldValue bean="${resultInstance}" field="productType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.productNumber}">
				<li class="fieldcontain">
					<span id="productNumber-label" class="property-label"><g:message code="result.productNumber.label" default="Product Number" /></span>
					
						<span class="property-value" aria-labelledby="productNumber-label"><g:fieldValue bean="${resultInstance}" field="productNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.price}">
				<li class="fieldcontain">
					<span id="price-label" class="property-label"><g:message code="result.price.label" default="Price" /></span>
					
						<span class="property-value" aria-labelledby="price-label"><g:fieldValue bean="${resultInstance}" field="price"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.receiptNumber}">
				<li class="fieldcontain">
					<span id="receiptNumber-label" class="property-label"><g:message code="result.receiptNumber.label" default="Receipt Number" /></span>
					
						<span class="property-value" aria-labelledby="receiptNumber-label"><g:fieldValue bean="${resultInstance}" field="receiptNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.dealerName}">
				<li class="fieldcontain">
					<span id="dealerName-label" class="property-label"><g:message code="result.dealerName.label" default="Dealer Name" /></span>
					
						<span class="property-value" aria-labelledby="dealerName-label"><g:fieldValue bean="${resultInstance}" field="dealerName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.organizationCOde}">
				<li class="fieldcontain">
					<span id="organizationCOde-label" class="property-label"><g:message code="result.organizationCOde.label" default="Organization CO de" /></span>
					
						<span class="property-value" aria-labelledby="organizationCOde-label"><g:fieldValue bean="${resultInstance}" field="organizationCOde"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${resultInstance?.person}">
				<li class="fieldcontain">
					<span id="person-label" class="property-label"><g:message code="result.person.label" default="Person" /></span>
					
						<span class="property-value" aria-labelledby="person-label"><g:link controller="person" action="show" id="${resultInstance?.person?.id}">${resultInstance?.person?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${resultInstance?.id}" />
					<g:link class="edit" action="edit" id="${resultInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
