<%@ page import="com.sam.CommonElementAttribute" %>



<div class="fieldcontain ${hasErrors(bean: commonElementAttributeInstance, field: 'attribute_type', 'error')} required">
	<label for="attribute_type">
		<g:message code="commonElementAttribute.attribute_type.label" default="Attributetype" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="attribute_type" from="${com.sam.AttributeType?.values()}" keys="${com.sam.AttributeType.values()*.name()}" required="" value="${commonElementAttributeInstance?.attribute_type?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commonElementAttributeInstance, field: 'attribute_value', 'error')} ">
	<label for="attribute_value">
		<g:message code="commonElementAttribute.attribute_value.label" default="Attributevalue" />
		
	</label>
	<g:textField name="attribute_value" value="${commonElementAttributeInstance?.attribute_value}"/>
</div>

