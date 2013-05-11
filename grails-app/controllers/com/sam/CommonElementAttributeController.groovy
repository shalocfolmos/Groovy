package com.sam

import org.springframework.dao.DataIntegrityViolationException

class CommonElementAttributeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [commonElementAttributeInstanceList: CommonElementAttribute.list(params), commonElementAttributeInstanceTotal: CommonElementAttribute.count()]
    }

    def create() {
        [commonElementAttributeInstance: new CommonElementAttribute(params)]
    }

    def save() {
        def commonElementAttributeInstance = new CommonElementAttribute(params)
        if (!commonElementAttributeInstance.save(flush: true)) {
            render(view: "create", model: [commonElementAttributeInstance: commonElementAttributeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute'), commonElementAttributeInstance.id])
        redirect(action: "show", id: commonElementAttributeInstance.id)
    }

    def show(Long id) {
        def commonElementAttributeInstance = CommonElementAttribute.get(id)
        if (!commonElementAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute'), id])
            redirect(action: "list")
            return
        }

        [commonElementAttributeInstance: commonElementAttributeInstance]
    }

    def edit(Long id) {
        def commonElementAttributeInstance = CommonElementAttribute.get(id)
        if (!commonElementAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute'), id])
            redirect(action: "list")
            return
        }

        [commonElementAttributeInstance: commonElementAttributeInstance]
    }

    def update(Long id, Long version) {
        def commonElementAttributeInstance = CommonElementAttribute.get(id)
        if (!commonElementAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (commonElementAttributeInstance.version > version) {
                commonElementAttributeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute')] as Object[],
                        "Another user has updated this CommonElementAttribute while you were editing")
                render(view: "edit", model: [commonElementAttributeInstance: commonElementAttributeInstance])
                return
            }
        }

        commonElementAttributeInstance.properties = params

        if (!commonElementAttributeInstance.save(flush: true)) {
            render(view: "edit", model: [commonElementAttributeInstance: commonElementAttributeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute'), commonElementAttributeInstance.id])
        redirect(action: "show", id: commonElementAttributeInstance.id)
    }

    def delete(Long id) {
        def commonElementAttributeInstance = CommonElementAttribute.get(id)
        if (!commonElementAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute'), id])
            redirect(action: "list")
            return
        }

        try {
            commonElementAttributeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'commonElementAttribute.label', default: 'CommonElementAttribute'), id])
            redirect(action: "show", id: id)
        }
    }
}
