package com.sam

import org.springframework.dao.DataIntegrityViolationException

class SegmentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [segmentInstanceList: Segment.list(params), segmentInstanceTotal: Segment.count()]
    }

    def create() {
        [segmentInstance: new Segment(params)]
    }

    def save() {
        def segmentInstance = new Segment(params)
        if (!segmentInstance.save(flush: true)) {
            render(view: "create", model: [segmentInstance: segmentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'segment.label', default: 'Segment'), segmentInstance.id])
        redirect(action: "show", id: segmentInstance.id)
    }

    def show(Long id) {
        def segmentInstance = Segment.get(id)
        if (!segmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'segment.label', default: 'Segment'), id])
            redirect(action: "list")
            return
        }

        [segmentInstance: segmentInstance]
    }

    def edit(Long id) {
        def segmentInstance = Segment.get(id)
        if (!segmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'segment.label', default: 'Segment'), id])
            redirect(action: "list")
            return
        }

        [segmentInstance: segmentInstance]
    }

    def update(Long id, Long version) {
        def segmentInstance = Segment.get(id)
        if (!segmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'segment.label', default: 'Segment'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (segmentInstance.version > version) {
                segmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'segment.label', default: 'Segment')] as Object[],
                        "Another user has updated this Segment while you were editing")
                render(view: "edit", model: [segmentInstance: segmentInstance])
                return
            }
        }

        segmentInstance.properties = params

        if (!segmentInstance.save(flush: true)) {
            render(view: "edit", model: [segmentInstance: segmentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'segment.label', default: 'Segment'), segmentInstance.id])
        redirect(action: "show", id: segmentInstance.id)
    }

    def delete(Long id) {
        def segmentInstance = Segment.get(id)
        if (!segmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'segment.label', default: 'Segment'), id])
            redirect(action: "list")
            return
        }

        try {
            segmentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'segment.label', default: 'Segment'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'segment.label', default: 'Segment'), id])
            redirect(action: "show", id: id)
        }
    }
}
