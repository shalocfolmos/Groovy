package com.sam

import grails.gsp.PageRenderer

class IndexController {

    def index() {
        def templateCollection = Template.findAll()
        if(!flash["page"]) {

            render(view:"/index",model: [templateCollection:templateCollection,page:"template"])
        }
        else {
            render(view:"/index",model: [templateCollection:templateCollection,page:flash.page])
        }
    }

    def ajaxContent() {
        if(params.module == 'templateFramework') {
            def templateFrameworkCollection = TemplateFramework.findAll()
            def fakeTemplateFrameworkCollection = []
            templateFrameworkCollection.each ({it ->
                def fakeTemplateFramework = new Expando()
                def commonElementGroupCollection = CommonElementGroup.findAllByTemplateFrameworkId(it.id)
                fakeTemplateFramework.id = it.id
                fakeTemplateFramework.templateFrameworkStatus = it.templateFrameworkStatus
                fakeTemplateFramework.templateName = it.templateName
                fakeTemplateFramework.segments = it.segments
                if(commonElementGroupCollection && commonElementGroupCollection.size() > 0) {

                    fakeTemplateFramework.elementGroupCollection = commonElementGroupCollection
                }
                fakeTemplateFrameworkCollection << fakeTemplateFramework
            }
            );
            render(template: "templateFramework",model: [templateFrameworkCollection:fakeTemplateFrameworkCollection])
        }
        else {
            def templateCollection = Template.findAll()
            render(template: "/index/template",model: [templateCollection:templateCollection])
        }
    }
}
