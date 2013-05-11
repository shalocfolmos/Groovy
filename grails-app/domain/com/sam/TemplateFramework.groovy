 package com.sam

import javax.xml.parsers.DocumentBuilderFactory
import com.sun.org.apache.xpath.internal.XPathAPI
import javax.xml.transform.TransformerFactory
import javax.xml.transform.Transformer
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import java.sql.Clob
 import org.w3c.dom.Element
 import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl

 class TemplateFramework {

    String templateName

    String htmlContent

    TemplateFrameworkStatus templateFrameworkStatus

    static hasMany = [segments:Segment]

    def compile() {
        def compiledTemplateContent = htmlContent.trim().replaceAll("&","&amp;")
        def factory = DocumentBuilderFactory.newInstance()
        factory.setValidating(false)
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        def builder = factory.newDocumentBuilder()
        def inputStream = new ByteArrayInputStream(compiledTemplateContent.bytes)
        def documentElement = builder.parse(inputStream).documentElement
        XPathAPI.selectNodeList(documentElement,"//@segment/..").each {
            def name = it.getAttribute("segment")
            def nowDate = new Date()
            def segmentId = String.valueOf(nowDate.getTime())
            def segment = null
            String nodeValue = null
            if(it.getNodeName().toUpperCase() == "A")
            {
                segment = new Segment(name:name,segmentId:segmentId,segmentType:SegmentType.ANCESTOR)
            }
            else if(it.getNodeName().toUpperCase() == "IMG")
            {
                segment = new Segment(name:name,segmentId:segmentId,segmentType:SegmentType.IMAGE)
            }
            else {
                segment = new Segment(name:name,segmentId:segmentId,segmentType:SegmentType.OTHERS)
            }
            if(segment)
            {
                it.setAttribute("segment_id",segmentId)
                it.removeAttribute("segment")
                Element ele = (Element)it
                if(ele.firstChild != null && ele.firstChild.getNodeType() == Element.TEXT_NODE)
                {
                    nodeValue = ele.firstChild.getNodeValue().toString()
                    nodeValue = nodeValue.replace("\t","").replace("\r","").replace("\\s","").replace("\n","")
                    if(nodeValue.size() >= 1)
                    {
                        segment.shouldModifyContext = true
                    }
                }
                segment.save(flush: true)
            }
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transform = tf.newTransformer();
        transform.setOutputProperty("encoding", "UTF-8");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        transform.transform(new DOMSource(documentElement), new StreamResult(bos))
        this.htmlContent =  bos.toString()
        this.save()
    }

    static constraints = {
        templateName(nullable: false,blank: false,unique: true)
        htmlContent(nullable: false,blank: false)
    }

    static mapping = {
        htmlContent(type: "text")
        segments(default:[],cascade:"all")

    }
}
