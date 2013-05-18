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

    def save() {
        def commonElementGroupInstance = new CommonElementGroup(params)
        if (!commonElementGroupInstance.save(flush: true)) {
            render(view: "create", model: [commonElementGroupInstance: commonElementGroupInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'commonElementGroup.label', default: 'CommonElementGroup'), commonElementGroupInstance.id])
        redirect(action: "show", id: commonElementGroupInstance.id)
    }

    def show(Long id) {
        def commonElementGroupInstance = CommonElementGroup.get(id)
        if (!commonElementGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElementGroup.label', default: 'CommonElementGroup'), id])
            redirect(action: "list")
            return
        }

        [commonElementGroupInstance: commonElementGroupInstance]
    }

    def edit(Long id) {
        def commonElementGroupInstance = CommonElementGroup.get(id)
        if (!commonElementGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElementGroup.label', default: 'CommonElementGroup'), id])
            redirect(action: "list")
            return
        }

        [commonElementGroupInstance: commonElementGroupInstance]
    }

    def update(Long id, Long version) {
        def commonElementGroupInstance = CommonElementGroup.get(id)
        if (!commonElementGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElementGroup.label', default: 'CommonElementGroup'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (commonElementGroupInstance.version > version) {
                commonElementGroupInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'commonElementGroup.label', default: 'CommonElementGroup')] as Object[],
                        "Another user has updated this CommonElementGroup while you were editing")
                render(view: "edit", model: [commonElementGroupInstance: commonElementGroupInstance])
                return
            }
        }

        commonElementGroupInstance.properties = params

        if (!commonElementGroupInstance.save(flush: true)) {
            render(view: "edit", model: [commonElementGroupInstance: commonElementGroupInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'commonElementGroup.label', default: 'CommonElementGroup'), commonElementGroupInstance.id])
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
