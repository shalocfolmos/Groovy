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
            templateFrameworkCollection.each ({it ->
                TemplateFramework.metaClass.elementGroupCollection = []
                def commonElementGroupCollection = CommonElementGroup.findAllByTemplateFrameworkId(it.id)
                if(commonElementGroupCollection && commonElementGroupCollection.size() > 0) {
                    TemplateFramework.metaClass.elementGroupCollection = commonElementGroupCollection
                }
            }
            );
            render(template: "templateFramework",model: [templateFrameworkCollection:templateFrameworkCollection])
        }
        else {
            def templateCollection = Template.findAll()
            render(template: "/index/template",model: [templateCollection:templateCollection])
        }
    }
}
