package com.sam

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import com.sam.comparator.CommonElementComparator

class CommonElementGroupController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def commonElementService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [commonElementGroupInstanceList: CommonElementGroup.list(params), commonElementGroupInstanceTotal: CommonElementGroup.count()]
    }

    def create() {
        [commonElementGroupInstance: new CommonElementGroup(params)]
    }

    def deleteCommonElementGroup(Long id) {

        def group = CommonElementGroup.get(id)
        if (!group) {
            render "操作异常,请重新尝试"
            return
        }
        try {
            group.delete()
            render "删除成功!"
        }
        catch (Exception e) {
            render "服务器异常,请重新尝试"
        }

    }

    def generateCommonElementGroup() {
        try {

            if (CommonElementGroup.findByName(params.contentGroupName) != null){
                render "组件名不能重复!"
            }
            else {
                commonElementService.generateCommonElementGroup(params)
                render "T"
            }
        } catch (Exception e) {
            render "创建失败,请重新尝试"
        }
    }

    def saveElementContent() {
        try {
            params.content.split(",").each {it ->
                def attributeId = it.split(":")[0]
                def attributeValue = it.split(":")[1]
                def commonElementAttribute = CommonElementAttribute.get(attributeId)
                if (commonElementAttribute) {
                    commonElementAttribute.attribute_value = attributeValue
                    commonElementAttribute.save()
                }
            }
            render "编辑成功!"
        }
        catch (Exception e) {
            render "服务器发生异常,请重新尝试"
        }

    }

    def listGroupElement(Long id) {
        try {
            def elementGroup = CommonElementGroup.get(id)
            if (!elementGroup) {
                render "组件群不存在!"
            }
            else {
                String content = ""
                Set<CommonElement>commonElementSet = elementGroup.commonElements.sort(new CommonElementComparator())

                for(commonElement in commonElementSet) {
                    if(commonElement.segment.segmentType==SegmentType.IMAGE) {
                        content += g.render(template:"/commonElement/imageEditTemplate",model:[element:commonElement]).toString()
                    }
                    else if (commonElement.segment.segmentType==SegmentType.ANCESTOR) {
                        content += g.render(template:"/commonElement/linkEditTemplate",model:[element:commonElement]).toString()
                    }
                    else if (commonElement.segment.segmentType == SegmentType.OTHERS) {
                        content += g.render(template:"/commonElement/otherEditTemplate",model:[element:commonElement]).toString()
                    }
                }

                render content
            }
        } catch (Exception e) {
            render "发生异常,请重新尝试"
        }
    }

    def getCommonElementGroup(Long templateId) {
        def template = Template.get(templateId),
        templateFrameworkId = template.templateFramework.id,
        commonElementGroupCollection =  CommonElementGroup.findAllByTemplateFrameworkId(templateFrameworkId),
        content = g.render(template:"commonElementGroupMenu",model:[commonElementGroupCollection:commonElementGroupCollection,templateId:templateId])
        render content
    }

}
