<%@ page import="com.sam.TemplateElement" %>



<div class="fieldcontain ${hasErrors(bean: templateElementInstance, field: 'segment', 'error')} required">
	<label for="segment">
		<g:message code="templateElement.segment.label" default="Segment" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="segment" name="segment.id" from="${com.sam.Segment.list()}" optionKey="id" required="" value="${templateElementInstance?.segment?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: templateElementInstance, field: 'attributes', 'error')} ">
	<label for="attributes">
		<g:message code="templateElement.attributes.label" default="Attributes" />
		
	</label>
	<g:select name="attributes" from="${com.sam.Attribute.list()}" multiple="multiple" optionKey="id" size="5" value="${templateElementInstance?.attributes*.id}" class="many-to-many"/>
</div>

