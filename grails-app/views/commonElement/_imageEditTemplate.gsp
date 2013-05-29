<%@ page import="com.sam.AttributeType" %>
<div>
    <h5 style="font-size: 18px;font-weight:bolder;height: 30px; padding-left: 15px;padding-top: 30px;">${element?.segment?.name}</h5>
</div>
<g:each in="${element.attributes}" var="attribute" status="attributeIndex">
    <div style="padding-top: 15px;border-top: 1px">
        <g:if test="${attribute.attribute_type == AttributeType.IMAGE_SRC}">
            <div name="common_attribute_${attribute.id}" attribute_id="${attribute.id}">
                <span style="font-size: 15px;padding-left: 15px; display: block;width: 120px; float: left;" >图片地址:</span>
                <g:textField  style="width: 250px;height: 20px;" name="common_attribute_value_${attribute.id}" value="${attribute.attribute_value}"></g:textField>
            </div>
        </g:if>
        <g:if test="${attribute.attribute_type == AttributeType.IMAGE_ALT}">
            <div name="common_attribute_${attribute.id}" attribute_id="${attribute.id}">
                <span style="font-size: 15px;padding-left: 15px;width: 120px; float: left;">图片显示信息:</span>
                <g:textField style="width: 250px;height: 20px;" name="common_attribute_value_${attribute.id}" value="${attribute.attribute_value}"></g:textField>
            </div>
        </g:if>
        <g:if test="${attribute.attribute_type == AttributeType.TEXT_CONTACT}">
            <div name="common_attribute_${attribute.id}" attribute_id="${attribute.id}">
                <span style="font-size: 15px;padding-left: 15px;display: block;width: 120px; float: left;">显示文字:</span>
                <g:textField style="width: 250px;height: 20px;" name="common_attribute_value_${attribute.id}" value="${attribute.attribute_value}"></g:textField>
            </div>
        </g:if>
    </div>
</g:each>
