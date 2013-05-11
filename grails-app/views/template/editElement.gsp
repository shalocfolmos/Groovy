<%@ page import="com.sam.SegmentType" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>编辑模板元素</title>
  <meta content="main" name="layout">
</head>
<body>
    <a href="#list-template" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        </ul>
    </div>
    <g:form action="saveElement">
        <g:hiddenField id="templateId" name="templateId" value="${templateId}"/>
        <g:each in="${templateElements}" var="element" status="elementIndex">
            <g:if test="${element?.segment?.segmentType==SegmentType.ANCESTOR}">
                <div>
                <g:render template="./segment/anchor" model="[element:element,element_index:elementIndex]"/>
                </div>
            </g:if>
            <g:if test="${element?.segment?.segmentType==SegmentType.IMAGE}">
                <div>
                <g:render template="./segment/img" model="[element:element,element_index:elementIndex]"/>
                </div>
            </g:if>
            <g:if test="${element?.segment?.segmentType==SegmentType.OTHERS}">
                <div>
                    <g:render template="./segment/img" model="[element:element,element_index:elementIndex]"/>
                </div>
            </g:if>
        </g:each>
        <div style="padding-left: 30px;padding-top: 20px;">
            <g:submitButton name="提交" value="提交" />
            <input type="button" id="preview" value="预览"/>
        </div>
    </g:form>
    <g:javascript library="jquery"/>
    <script type="text/javascript">
        window.onbeforeunload   =   function(){
            $.ajax(
                    {
                        type:"GET",
                        url:"${createLink(uri:"/template/cancelPreview")}"
                    }
            );
        };

        $(document).ready(
            function() {
                $('#preview').click(
                    function(){
                        var attribute_elements = $('input[name*=".id"]'),
                        attribute_id_array = [],
                        attribute_val_array = [];
                        for(var i=0;i<attribute_elements.length;i++)
                        {
                            var ele = attribute_elements[i],
                            eleName = ele.name,
                            element_index = eleName.substr(eleName.indexOf('s')+2,eleName.indexOf(']')-(eleName.indexOf('s')+2)),
                            attribute_index = eleName.substr(eleName.indexOf('s',18)+2,eleName.indexOf(']',20)-(eleName.indexOf('s',18)+2)),
                            attribute_id = $('input[name="templateElements['+element_index+'].attributes['+attribute_index+'].id"]').val(),
                            attribute_val = $('input[name="templateElements['+element_index+'].attributes['+attribute_index+'].attribute_value"]').val();
                            attribute_id_array[i] = attribute_id;
                            attribute_val_array[i] = attribute_val;
                        }
                        $.ajax(
                            {
                                type: "POST",
                                url:  "${createLink(uri:"/template/createPreview")}",
                                data: {
                                    templateId:$("#templateId").val(),
                                    attribute_id:attribute_id_array,
                                    attribute_val:attribute_val_array
                                },
                                success: function (msg) {
                                    window.open("${createLink(uri:"/template/preview")}"+"/"+msg);
                                },
                                error: function (msg){
                                    alert('error');
                                }
                            }
                        );
                    }
                )
            }
        )
    </script>

</body>
</html>