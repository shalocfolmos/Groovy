<%@ page import="program.Result" %>



<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'consumeName', 'error')} required">
	<label for="consumeName">
		<g:message code="result.consumeName.label" default="Consume Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="consumeName" maxlength="20" required="" value="${resultInstance?.consumeName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'contact', 'error')} required">
	<label for="contact">
		<g:message code="result.contact.label" default="Contact" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="contact" maxlength="15" required="" value="${resultInstance?.contact}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'creditCard', 'error')} required">
	<label for="creditCard">
		<g:message code="result.creditCard.label" default="Credit Card" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="creditCard" maxlength="18" required="" value="${resultInstance?.creditCard}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'saleDate', 'error')} required">
	<label for="saleDate">
		<g:message code="result.saleDate.label" default="Sale Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="saleDate" precision="day"  value="${resultInstance?.saleDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'phone', 'error')} required">
	<label for="phone">
		<g:message code="result.phone.label" default="Phone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="phone" required="" value="${resultInstance?.phone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="result.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="address" maxlength="60" required="" value="${resultInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'postCode', 'error')} required">
	<label for="postCode">
		<g:message code="result.postCode.label" default="Post Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="postCode" maxlength="20" required="" value="${resultInstance?.postCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'postName', 'error')} required">
	<label for="postName">
		<g:message code="result.postName.label" default="Post Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="postName" maxlength="60" required="" value="${resultInstance?.postName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'enterprise', 'error')} required">
	<label for="enterprise">
		<g:message code="result.enterprise.label" default="Enterprise" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="enterprise" maxlength="50" required="" value="${resultInstance?.enterprise}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'productType', 'error')} required">
	<label for="productType">
		<g:message code="result.productType.label" default="Product Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="productType" maxlength="30" required="" value="${resultInstance?.productType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'productNumber', 'error')} required">
	<label for="productNumber">
		<g:message code="result.productNumber.label" default="Product Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="productNumber" maxlength="30" required="" value="${resultInstance?.productNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'price', 'error')} required">
	<label for="price">
		<g:message code="result.price.label" default="Price" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="price" value="${fieldValue(bean: resultInstance, field: 'price')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'receiptNumber', 'error')} required">
	<label for="receiptNumber">
		<g:message code="result.receiptNumber.label" default="Receipt Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="receiptNumber" maxlength="30" required="" value="${resultInstance?.receiptNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'dealerName', 'error')} required">
	<label for="dealerName">
		<g:message code="result.dealerName.label" default="Dealer Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dealerName" maxlength="50" required="" value="${resultInstance?.dealerName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'organizationCOde', 'error')} required">
	<label for="organizationCOde">
		<g:message code="result.organizationCOde.label" default="Organization CO de" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="organizationCOde" maxlength="30" required="" value="${resultInstance?.organizationCOde}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'person', 'error')} required">
	<label for="person">
		<g:message code="result.person.label" default="Person" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="person" name="person.id" from="${program.Person.list()}" optionKey="id" required="" value="${resultInstance?.person?.id}" class="many-to-one"/>
</div>

