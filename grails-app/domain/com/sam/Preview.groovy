package com.sam


import javax.xml.parsers.DocumentBuilderFactory
import com.sun.org.apache.xpath.internal.XPathAPI
import org.w3c.dom.Element
import javax.xml.transform.TransformerFactory
import javax.xml.transform.Transformer
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class Preview {

    String  templateId

    String previewId

    String sessionId

    static hasMany = [previewAttributes:PreviewAttribute]

    String previewHtml

    boolean shouldDeleted = false

    static mapping = {
        previewHtml type: 'text'
        previewAttributes (lazy: false,cascade: "all")
    }

    def generatePreviewHtml() {
        def factory = DocumentBuilderFactory.newInstance()
        def template = Template.get(templateId)
        factory.setValidating(false)
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        def builder = factory.newDocumentBuilder()
        def inputStream = new ByteArrayInputStream(template.middleTemplateContent.trim().bytes)
        def documentElement = builder.parse(inputStream).documentElement
        XPathAPI.selectNodeList(documentElement,"//@element_id/..").each {
            def templateElement = TemplateElement.findByElementId(it.getAttribute("element_id"))
            def element = (Element)it
            templateElement.attributes.each {attr->
                this.getPreviewAttributes().each { item->
                    if(item.attributeId==attr.id) {
                        if(attr.attribute_type == AttributeType.TEXT_CONTACT)
                        {
                            element.firstChild.setNodeValue(item.attribute_value)
                        }
                        else
                        {
                            element.setAttribute(attr.attribute_type.displayContent,item.attribute_value)
                        }
                    }
                }
            }
        }
        TransformerFactory tf = TransformerFactory.newInstance()
        Transformer transform = tf.newTransformer()
        transform.setOutputProperty("encoding", "UTF-8")
        ByteArrayOutputStream bos = new ByteArrayOutputStream()
        transform.transform(new DOMSource(documentElement), new StreamResult(bos))
        return bos.toString().trim().replaceAll("&amp;","&")
    }

    static constraints = {
        previewHtml(blank: true,nullable: true)
        previewAttributes(nullable:true)
    }
}
