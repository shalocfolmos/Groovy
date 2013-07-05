package com.sam

import com.sam.command.TemplateElementCollectionCommand
import org.springframework.dao.DataIntegrityViolationException
import com.sam.comparator.TemplateElementComparator

class TemplateController {

    static allowedMethods = [save: "POST", delete: "GET"]
    def templateService

    def index() {
        redirect(action: "list", params: params)
    }

    def createPreview() {
        def previewId = templateService.createPreview(params,session.id)
        render(previewId)
    }

    def preview(Long id) {
        def preview = Preview.findByPreviewId(String.valueOf(id))
        def previewHtml = preview.generatePreviewHtml()
        render(text:previewHtml,contentType:"text/html",encoding:"UTF-8")
    }

    def cancelPreview() {
        try
        {
            templateService.deletePreview(session.id)
        }
        catch(Exception e) {
            templateService.markShouldDelete(session.id)
        }

    }


    def create() {
        [templateInstance: new Template(params)]
    }

    def save() {
        def templateInstance = new Template(params)
        templateInstance.templateStatus = TemplateStatus.INIT
        if (!templateInstance.save(flush: true)) {
            render(view: "create", model: [templateInstance: templateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'template.label', default: 'Template'), templateInstance.id])
        flash.page="template"
        redirect(controller:"index")
    }

    def editElement(Long id) {
        def templateInstance = Template.get(id)
        if(!templateInstance){
            response.sendError(404)
            return
        }
        [templateElements:templateInstance.templateElements.sort(new TemplateElementComparator()),templateId:id]
    }

    def saveElement(TemplateElementCollectionCommand command) {
        templateService.setTemplateContent(command)
        flash.page = "template"
        redirect(controller:"index")
    }

    def exportTemplateContent(Long id) {
        def templateInstance = Template.get(id)
        response.setHeader("content-disposition","attachment;filename="+
                URLEncoder.encode(templateInstance.templateTitle,"UTF-8"))
        render(text:templateInstance.finalTemplateContent.trim().replaceAll("&amp;","&"),contentType:"text/html",encoding:"UTF-8")
    }

    def confirmTemplate(Long id) {
        Template.get(id).confirmTemplate()
        flash.page = "template"
        redirect(controller:"index")
    }

    def delete(Long id) {
        def templateInstance = Template.get(id)
        if (!templateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), id])
            redirect(controller: "index")
            return
        }

        try {
            Preview.deleteAll(Preview.findAllByTemplateId(id))

            templateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'template.label', default: 'Template'), id])
            flash.page = "template"
            redirect(controller: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'template.label', default: 'Template'), id])
            redirect(controller: "index")
        }
    }
    
    def applyCommonElementGroup() {
        def commonElementGroupId = params.commonElementGroupId
        def templateId = params.templateId
        try {
            templateService.applyCommonElementGroup(commonElementGroupId, templateId)
        }
        catch (Exception e) {
            render "服务器异常,请重新再试!"
            return
        }
        render "模板已修改!"
    }
}
