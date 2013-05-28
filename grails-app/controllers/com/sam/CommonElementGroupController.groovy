package com.sam

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON

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


    def listGroupElement(Long id) {
        try {
            def elementGroup = CommonElementGroup.get(id)
            if (!elementGroup) {
                render "组件群不存在!"
            }
            else {
                String content = ""
                Set<CommonElement>commonElementSet = elementGroup.commonElements
                for(commonElement in commonElementSet) {
                    if(commonElement.segment.segmentType==SegmentType.IMAGE) {
                        content += g.render(template:"/commonElement/imageEditTemplate",model:[element:commonElement]).toString()
                    }
                    else if (commonElement.segment.segmentType==SegmentType.ANCESTOR) {
                        content += g.render(template:"/commonElement/linkEditTemplate",model:[element:commonElement]).toString()
                    }
                }

                render content
            }
        } catch (Exception e) {
            render "创建失败,请重新尝试"
        }
    }


    def save() {
        def commonElementGroupInstance = new CommonElementGroup(params)
        if (!commonElementGroupInstance.save(flush: true)) {
            render(view: "create", model: [commonElementGroupInstance: commonElementGroupInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'commonElementGroup.label', default: 'CommonElementGroup'), commonElementGroupInstance.id])
        redirect(action: "show", id: commonElementGroupInstance.id)
    }
}
