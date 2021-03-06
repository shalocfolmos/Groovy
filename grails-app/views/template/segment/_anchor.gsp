<%@ page import="com.sam.AttributeType" %>
<h5 style="font-size: 18px;font-weight:bolder;height: 30px; padding-left: 15px;padding-top: 30px;">${element?.segment?.name}</h5>
<g:each in="${element.attributes}" var="attribute" status="attributeIndex">
    <div style="padding-top: 15px;border-top: 1px">
        <g:if test="${attribute.attribute_type == AttributeType.ANCESOR_HREF_ATTR}">
         <div>
            <span style="font-size: 15px;padding-left: 15px;display: block;width: 120px; float: left;">链接地址:</span>
            <g:textField style="width: 250px;height: 20px;" name="templateElements[${element_index}].attributes[${attributeIndex}].attribute_value" value="${attribute.attribute_value}"></g:textField>
            <g:hiddenField name="templateElements[${element_index}].attributes[${attributeIndex}].attribute_type" value="ANCESOR_HREF_ATTR"/>
            <g:hiddenField name="templateElements[${element_index}].attributes[${attributeIndex}].id" value="${attribute.id}"/>
         </div>
        </g:if>
        <g:if test="${attribute.attribute_type == AttributeType.TEXT_CONTACT}">
            <span style="font-size: 15px;padding-left: 15px;display: block;width: 120px; float: left;">显示文字:</span>
            <g:textField style="width: 250px;height: 20px;" name="templateElements[${element_index}].attributes[${attributeIndex}].attribute_value" value="${attribute.attribute_value}"></g:textField>
            <g:hiddenField name="templateElements[${element_index}].attributes[${attributeIndex}].attribute_type" value="TEXT_CONTACT"/>
            <g:hiddenField name="templateElements[${element_index}].attributes[${attributeIndex}].id" value="${attribute.id}"/>
        </g:if>
    </div>
</g:each>
<g:hiddenField name="templateElements[${element_index}].elementId" value="${element?.elementId}"/>
