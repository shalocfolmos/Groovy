package com.sam

import org.springframework.dao.DataIntegrityViolationException

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
            if (!elementGroup){
                render "组件群不存在!"
            }
            else {
                Set<CommonElement> commonElementSet = elementGroup.commonElements

                commonElementService.generateCommonElementGroup(params)
                render "T"
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




    def delete(Long id) {
        def commonElementGroupInstance = CommonElementGroup.get(id)
        if (!commonElementGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElementGroup.label', default: 'CommonElementGroup'), id])
            redirect(action: "list")
            return
        }

        try {
            commonElementGroupInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'commonElementGroup.label', default: 'CommonElementGroup'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'commonElementGroup.label', default: 'CommonElementGroup'), id])
            redirect(action: "show", id: id)
        }
    }
}
