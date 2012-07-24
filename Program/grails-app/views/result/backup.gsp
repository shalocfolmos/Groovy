<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>
<body>
<g:if test="${notLogin==true}">
    <g:form action="create">
        <g:textField name="username"/>
        <g:passwordField name="password"/>
        <input type="submit" value="登入"/>
    </g:form>
</g:if>
<g:else>
    <g:form action="create" controller="result">
        <div>
            消费者名称:<g:textField name="consumeName" value="${result?.consumeName}"/>
            <g:eachError var="err" bean="${result}" field="consumeName">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            联系人:<g:textField name="contact" value="${result?.contact}"/>
            <g:eachError bean="${result}" field="contact" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            身份证信息:<g:textField name="creditCard" value="${result?.creditCard}"/>
            <g:eachError bean="${result}" field="creditCard" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            联系电话:<g:textField name="phone" value="${result?.phone}"/>
            <g:eachError bean="${result}" field="phone" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            详细地址:<g:textField name="address" value="${result?.address}"/>
            <g:eachError bean="${result}" field="address" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            地行政区划代码:<g:textField name="postCode" value="${result?.postCode}"/>
            <g:eachError bean="${result}" field="postCode" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            政区划代码对应名称:<g:textField name="postName" value="${result?.postName}"/>
            <g:eachError bean="${result}" field="postName" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            销售时间:<g:textField name="saleDate" value="${result?.saleDate}"/>
            %{--<g:eachError bean="${result}" field="saleDate" var="err">--}%
            %{--<g:message error="${err}"/>--}%
            %{--</g:eachError>--}%
        </div>
        <div>
            企业名称:<g:textField name="enterprise" value="${result?.enterprise}"/>
            <g:eachError bean="${result}" field="enterprise" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            规格型号:<g:textField name="productType" value="${result?.productType}"/>
            <g:eachError bean="${result}" field="productType" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            产品唯一编码:<g:textField name="productNumber" value="${result?.productNumber}"/>
            <g:eachError bean="${result}" field="productNumber" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            销售价格（元）:<g:textField name="price" value="${result?.price}"/>
            <g:eachError bean="${result}" field="price" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            发票号:<g:textField name="receiptNumber" value="${result?.receiptNumber}"/>
            <g:eachError bean="${result}" field="receiptNumber" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            终端经销商名称:<g:textField name="dealerName" value="${result?.dealerName}"/>
            <g:eachError bean="${result}" field="dealerName" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <div>
            组织机构代码:<g:textField name="organizationCOde" value="${result?.organizationCOde}"/>
            <g:eachError bean="${result}" field="organizationCOde" var="err">
                <g:message error="${err}"/>
            </g:eachError>
        </div>
        <input type="submit" value="登入"/>
    </g:form>
</g:else>

</body>
</html>