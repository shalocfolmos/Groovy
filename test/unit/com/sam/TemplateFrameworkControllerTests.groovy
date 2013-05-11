package com.sam



import org.junit.*
import grails.test.mixin.*

@TestFor(TemplateFrameworkController)
@Mock(TemplateFramework)
class TemplateFrameworkControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/templateFramework/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.templateFrameworkInstanceList.size() == 0
        assert model.templateFrameworkInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.templateFrameworkInstance != null
    }

    void testSave() {
        controller.save()

        assert model.templateFrameworkInstance != null
        assert view == '/templateFramework/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/templateFramework/show/1'
        assert controller.flash.message != null
        assert TemplateFramework.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/templateFramework/list'

        populateValidParams(params)
        def templateFramework = new TemplateFramework(params)

        assert templateFramework.save() != null

        params.id = templateFramework.id

        def model = controller.show()

        assert model.templateFrameworkInstance == templateFramework
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/templateFramework/list'

        populateValidParams(params)
        def templateFramework = new TemplateFramework(params)

        assert templateFramework.save() != null

        params.id = templateFramework.id

        def model = controller.edit()

        assert model.templateFrameworkInstance == templateFramework
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/templateFramework/list'

        response.reset()

        populateValidParams(params)
        def templateFramework = new TemplateFramework(params)

        assert templateFramework.save() != null

        // test invalid parameters in update
        params.id = templateFramework.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/templateFramework/edit"
        assert model.templateFrameworkInstance != null

        templateFramework.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/templateFramework/show/$templateFramework.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        templateFramework.clearErrors()

        populateValidParams(params)
        params.id = templateFramework.id
        params.version = -1
        controller.update()

        assert view == "/templateFramework/edit"
        assert model.templateFrameworkInstance != null
        assert model.templateFrameworkInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/templateFramework/list'

        response.reset()

        populateValidParams(params)
        def templateFramework = new TemplateFramework(params)

        assert templateFramework.save() != null
        assert TemplateFramework.count() == 1

        params.id = templateFramework.id

        controller.delete()

        assert TemplateFramework.count() == 0
        assert TemplateFramework.get(templateFramework.id) == null
        assert response.redirectedUrl == '/templateFramework/list'
    }
}
