package com.sam

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import com.sam.comparator.SegmentComparator

class TemplateFrameworkController {

    static allowedMethods = [save: "POST", update: "POST", delete: "GET"]
    def templateFrameworkService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [templateFrameworkInstanceList: TemplateFramework.list(params), templateFrameworkInstanceTotal: TemplateFramework.count()]
    }

    def create() {
        [templateFrameworkInstance: new TemplateFramework(params)]
    }

    def save() {
        def f = request.getFile('templateFrameworkFile')
        def fileContent = new String(f.getBytes())

        def templateName = params.templateName

        def templateFrameworkInstance = new TemplateFramework()
        templateFrameworkInstance.templateName = templateName
        templateFrameworkInstance.htmlContent = fileContent
        templateFrameworkInstance.templateFrameworkStatus=TemplateFrameworkStatus.UN_COMPILED
        if (!templateFrameworkInstance.save(flush: true)) {
            flash.page = "templateFramework"
            redirect(controller:"index")
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'templateFramework.label', default: 'TemplateFramework'), templateFrameworkInstance.id])
        flash.page = "templateFramework"
        redirect(controller:"index")
    }

    def compile(Long id) {
        templateFrameworkService.compileFramework(id)
        flash.page = "templateFramework"
        redirect(controller: "index")
    }

    def show(Long id) {
        def templateFrameworkInstance = TemplateFramework.get(id)
        if (!templateFrameworkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'templateFramework.label', default: 'TemplateFramework'), id])
            redirect(action: "list")
            return
        }

        [templateFrameworkInstance: templateFrameworkInstance]
    }

    def edit(Long id) {
        def templateFrameworkInstance = TemplateFramework.get(id)
        if (!templateFrameworkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'templateFramework.label', default: 'TemplateFramework'), id])
            flash.page = "templateFramework"
            redirect(controller:"index")
            return
        }

        [templateFrameworkInstance: templateFrameworkInstance]
    }

    def update(Long id, Long version) {
        def templateFrameworkInstance = TemplateFramework.get(id)
        if (!templateFrameworkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'templateFramework.label', default: 'TemplateFramework'), id])
            flash.page = "templateFramework"
            redirect(controller:"index")
            return
        }

        if (version != null) {
            if (templateFrameworkInstance.version > version) {
                templateFrameworkInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'templateFramework.label', default: 'TemplateFramework')] as Object[],
                          "Another user has updated this TemplateFramework while you were editing")
                render(view: "edit", model: [templateFrameworkInstance: templateFrameworkInstance])
                return
            }
        }

        templateFrameworkInstance.properties = params
        templateFrameworkInstance.templateFrameworkStatus = TemplateFrameworkStatus.UN_COMPILED
        if (!templateFrameworkInstance.save(flush: true)) {
            render(view: "edit", model: [templateFrameworkInstance: templateFrameworkInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'templateFramework.label', default: 'TemplateFramework'), templateFrameworkInstance.id])
        flash.page = "templateFramework"
        redirect(controller:"index")
    }

    def getAllSegments(Long id) {
        def templateFrameworkInstance = TemplateFramework.get(id)
        if(!templateFrameworkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'templateFramework.label', default: 'TemplateFramework'), id])
            flash.page = "templateFramework"
            redirect(controller: "index")
            return
        }
        def segmentNameList = []

        templateFrameworkInstance.getSegments().each {it->
            segmentNameList << it
        }
        sortedSegmentNameList = segmentNameList.sort new SegmentComparator()
        render sortedSegmentNameList as JSON

    }

    def delete(Long id) {
        def templateFrameworkInstance = TemplateFramework.get(id)
        if (!templateFrameworkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'templateFramework.label', default: 'TemplateFramework'), id])
            flash.page = "templateFramework"
            redirect(controller: "index")
            return
        }

        try {
            templateFrameworkService.deleteCommentElementGroupAndTemplate(id)
            templateFrameworkInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'templateFramework.label', default: 'TemplateFramework'), id])
            flash.page = "templateFramework"
            redirect(controller: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'templateFramework.label', default: 'TemplateFramework'), id])
            flash.page = "templateFramework"
            redirect(controller: "index")
        }
    }
}
