<%@ page import="com.sam.Attribute" %>



<div class="fieldcontain ${hasErrors(bean: attributeInstance, field: 'key', 'error')} ">
	<label for="key">
		<g:message code="attribute.key.label" default="Key" />
	</label>
	<g:textField name="key" value="${attributeInstance?.key}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: attributeInstance, field: 'value', 'error')} ">
	<label for="value">
		<g:message code="attribute.value.label" default="Value" />
		
	</label>
	<g:textField name="value" value="${attributeInstance?.value}"/>
</div>

