package com.sam

import javax.xml.parsers.DocumentBuilderFactory
import com.sun.org.apache.xpath.internal.XPathAPI
import javax.xml.transform.TransformerFactory
import javax.xml.transform.Transformer
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class TemplateFrameworkService {

    def createBaseFramework(String baseTemplateFrameworkContent,String templateFrameworkName) {
        def createBaseTemplateDate = new Date()
        def templateFramework = new TemplateFramework(templateContent:baseTemplateFrameworkContent,templateName:templateFrameworkName,templateCode:String.valueOf(createBaseTemplateDate.getTime()))
        templateFramework.save()
    }

    def compileFramework(Long templateId) {
        def templateFramework = TemplateFramework.get(templateId)
        templateFramework.compile()
        templateFramework.templateFrameworkStatus = TemplateFrameworkStatus.COMPILED
        templateFramework.save(flush:true)
    }

    def deleteCommentElementGroupAndTemplate(Long frameworkId) {
        def groupList = CommonElementGroup.findAllByTemplateFrameworkId(String.valueOf(frameworkId))
        if(groupList) {
            groupList.each {it ->
                it.delete()
            }
        }
        def templateList = Template.findAllByTemplateFramework(TemplateFramework.get(frameworkId))
        if (templateList) {
            templateList.each {it ->
                it.delete()
            }
        }
    }
}

