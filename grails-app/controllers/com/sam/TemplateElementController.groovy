package com.sam

import org.springframework.dao.DataIntegrityViolationException

class TemplateElementController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [templateElementInstanceList: TemplateElement.list(params), templateElementInstanceTotal: TemplateElement.count()]
    }

    def create() {
        [templateElementInstance: new TemplateElement(params)]
    }

    def save() {
        def templateElementInstance = new TemplateElement(params)
        if (!templateElementInstance.save(flush: true)) {
            render(view: "create", model: [templateElementInstance: templateElementInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'templateElement.label', default: 'TemplateElement'), templateElementInstance.id])
        redirect(action: "show", id: templateElementInstance.id)
    }

    def show(Long id) {
        def templateElementInstance = TemplateElement.get(id)
        if (!templateElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'templateElement.label', default: 'TemplateElement'), id])
            redirect(action: "list")
            return
        }

        [templateElementInstance: templateElementInstance]
    }

    def edit(Long id) {
        def templateElementInstance = TemplateElement.get(id)
        if (!templateElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'templateElement.label', default: 'TemplateElement'), id])
            redirect(action: "list")
            return
        }

        [templateElementInstance: templateElementInstance]
    }

    def update(Long id, Long version) {
        def templateElementInstance = TemplateElement.get(id)
        if (!templateElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'templateElement.label', default: 'TemplateElement'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (templateElementInstance.version > version) {
                templateElementInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'templateElement.label', default: 'TemplateElement')] as Object[],
                        "Another user has updated this TemplateElement while you were editing")
                render(view: "edit", model: [templateElementInstance: templateElementInstance])
                return
            }
        }

        templateElementInstance.properties = params

        if (!templateElementInstance.save(flush: true)) {
            render(view: "edit", model: [templateElementInstance: templateElementInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'templateElement.label', default: 'TemplateElement'), templateElementInstance.id])
        redirect(action: "show", id: templateElementInstance.id)
    }

    def delete(Long id) {
        def templateElementInstance = TemplateElement.get(id)
        if (!templateElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'templateElement.label', default: 'TemplateElement'), id])
            redirect(action: "list")
            return
        }

        try {
            templateElementInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'templateElement.label', default: 'TemplateElement'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'templateElement.label', default: 'TemplateElement'), id])
            redirect(action: "show", id: id)
        }
    }
}
