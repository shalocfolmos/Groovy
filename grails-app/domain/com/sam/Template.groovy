package com.sam

import javax.xml.parsers.DocumentBuilderFactory
import com.sun.org.apache.xpath.internal.XPathAPI
import javax.xml.transform.TransformerFactory
import javax.xml.transform.Transformer
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import org.w3c.dom.Element

class Template {

    String templateTitle

    TemplateFramework templateFramework

    String finalTemplateContent

    String middleTemplateContent

    TemplateStatus templateStatus

    static hasMany = [templateElements:TemplateElement]

    static mapping = {
        templateElements (lazy: false,cascade:"all")
        templateFramework (lazy: false,cascade:"none")
        finalTemplateContent type: "text"
        middleTemplateContent type: "text"
    }

    def afterInsert() {
        def factory = DocumentBuilderFactory.newInstance()
        factory.setValidating(false)
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        def builder = factory.newDocumentBuilder()
        def inputStream = new ByteArrayInputStream(templateFramework.htmlContent.trim().bytes)
        try {
            def documentElement = builder.parse(inputStream).documentElement
            XPathAPI.selectNodeList(documentElement,"//@segment_id/..").each {
                def segment = Segment.findBySegmentId(it.getAttribute("segment_id"))
                def nowDate = new Date()
                def templateElement = new TemplateElement(segment:segment,elementId:String.valueOf(nowDate.getTime()))
                this.addToTemplateElements(templateElement)
                def element = (Element)it
                element.removeAttribute("segment_id")
                element.setAttribute("element_id",templateElement.elementId)
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transform = tf.newTransformer();
            transform.setOutputProperty("encoding", "UTF-8");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            transform.transform(new DOMSource(documentElement), new StreamResult(bos))
            this.middleTemplateContent =  bos.toString()
        }catch(Exception e)
        {
            e.toString()
        }
    }

    def confirmTemplate () {
        def factory = DocumentBuilderFactory.newInstance()
        factory.setValidating(false)
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        def builder = factory.newDocumentBuilder()
        def inputStream = new ByteArrayInputStream(this.middleTemplateContent.trim().bytes)
        def documentElement = builder.parse(inputStream).documentElement
        XPathAPI.selectNodeList(documentElement,"//@element_id/..").each {
            def templateElement = TemplateElement.findByElementId(it.getAttribute("element_id"))
            def element = (Element)it
            templateElement.attributes.each {
                if(it.attribute_type == AttributeType.TEXT_CONTACT)
                {
                    element.firstChild.setNodeValue(it.attribute_value)
                }
                else
                {
                    element.setAttribute(it.attribute_type.displayContent,it.attribute_value)
                }
            }
        }
        TransformerFactory tf = TransformerFactory.newInstance()
        Transformer transform = tf.newTransformer()
        transform.setOutputProperty("encoding", "UTF-8")
        ByteArrayOutputStream bos = new ByteArrayOutputStream()
        transform.transform(new DOMSource(documentElement), new StreamResult(bos))
        this.finalTemplateContent =  bos.toString()
        this.templateStatus = TemplateStatus.CONFIRMED
        this.save(flush: true)
    }


    static constraints = {
        templateFramework nullable: false
        templateTitle(nullable: false,blank:false,unique:true)
        templateElements(default:[])
        finalTemplateContent nullable: true
        middleTemplateContent(nullable: true)
    }

}