<%@ page import="com.sam.CommonElement" %>



<div class="fieldcontain ${hasErrors(bean: commonElementInstance, field: 'attributes', 'error')} ">
	<label for="attributes">
		<g:message code="commonElement.attributes.label" default="Attributes" />
		
	</label>
	<g:select name="attributes" from="${com.sam.CommonElementAttribute.list()}" multiple="multiple" optionKey="id" size="5" value="${commonElementInstance?.attributes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commonElementInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="commonElement.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${commonElementInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commonElementInstance, field: 'segment', 'error')} required">
	<label for="segment">
		<g:message code="commonElement.segment.label" default="Segment" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="segment" name="segment.id" from="${com.sam.Segment.list()}" optionKey="id" required="" value="${commonElementInstance?.segment?.id}" class="many-to-one"/>
</div>

