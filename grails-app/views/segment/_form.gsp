<%@ page import="com.sam.Segment" %>



<div class="fieldcontain ${hasErrors(bean: segmentInstance, field: 'elementId', 'error')} ">
	<label for="elementId">
		<g:message code="segment.elementId.label" default="Element Id" />
		
	</label>
	<g:textField name="elementId" value="${segmentInstance?.elementId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: segmentInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="segment.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${segmentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: segmentInstance, field: 'segmentType', 'error')} required">
	<label for="segmentType">
		<g:message code="segment.segmentType.label" default="Segment Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="segmentType" from="${com.sam.SegmentType?.values()}" keys="${com.sam.SegmentType.values()*.name()}" required="" value="${segmentInstance?.segmentType?.name()}"/>
</div>

