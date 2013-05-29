package com.sam

import org.springframework.dao.DataIntegrityViolationException

class CommonElementController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [commonElementInstanceList: CommonElement.list(params), commonElementInstanceTotal: CommonElement.count()]
    }

    def create() {
        [commonElementInstance: new CommonElement(params)]
    }


    def save() {
        def commonElementInstance = new CommonElement(params)
        if (!commonElementInstance.save(flush: true)) {
            render(view: "create", model: [commonElementInstance: commonElementInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'commonElement.label', default: 'CommonElement'), commonElementInstance.id])
        redirect(action: "show", id: commonElementInstance.id)
    }

    def show(Long id) {
        def commonElementInstance = CommonElement.get(id)
        if (!commonElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElement.label', default: 'CommonElement'), id])
            redirect(action: "list")
            return
        }

        [commonElementInstance: commonElementInstance]
    }

    def edit(Long id) {
        def commonElementInstance = CommonElement.get(id)
        if (!commonElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElement.label', default: 'CommonElement'), id])
            redirect(action: "list")
            return
        }

        [commonElementInstance: commonElementInstance]
    }

    def update(Long id, Long version) {
        def commonElementInstance = CommonElement.get(id)
        if (!commonElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElement.label', default: 'CommonElement'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (commonElementInstance.version > version) {
                commonElementInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'commonElement.label', default: 'CommonElement')] as Object[],
                        "Another user has updated this CommonElement while you were editing")
                render(view: "edit", model: [commonElementInstance: commonElementInstance])
                return
            }
        }

        commonElementInstance.properties = params

        if (!commonElementInstance.save(flush: true)) {
            render(view: "edit", model: [commonElementInstance: commonElementInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'commonElement.label', default: 'CommonElement'), commonElementInstance.id])
        redirect(action: "show", id: commonElementInstance.id)
    }

    def delete(Long id) {
        def commonElementInstance = CommonElement.get(id)
        if (!commonElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElement.label', default: 'CommonElement'), id])
            redirect(action: "list")
            return
        }

        try {
            commonElementInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'commonElement.label', default: 'CommonElement'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'commonElement.label', default: 'CommonElement'), id])
            redirect(action: "show", id: id)
        }
    }
}
