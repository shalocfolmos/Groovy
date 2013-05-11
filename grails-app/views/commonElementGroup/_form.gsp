<%@ page import="com.sam.CommonElementGroup" %>



<div class="fieldcontain ${hasErrors(bean: commonElementGroupInstance, field: 'commonElements', 'error')} ">
	<label for="commonElements">
		<g:message code="commonElementGroup.commonElements.label" default="Common Elements" />
		
	</label>
	<g:select name="commonElements" from="${com.sam.CommonElement.list()}" multiple="multiple" optionKey="id" size="5" value="${commonElementGroupInstance?.commonElements*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commonElementGroupInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="commonElementGroup.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${commonElementGroupInstance?.name}"/>
</div>

