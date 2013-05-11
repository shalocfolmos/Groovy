package com.sam



import org.junit.*
import grails.test.mixin.*

@TestFor(TemplateElementController)
@Mock(TemplateElement)
class TemplateElementControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/templateElement/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.templateElementInstanceList.size() == 0
        assert model.templateElementInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.templateElementInstance != null
    }

    void testSave() {
        controller.save()

        assert model.templateElementInstance != null
        assert view == '/templateElement/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/templateElement/show/1'
        assert controller.flash.message != null
        assert TemplateElement.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/templateElement/list'

        populateValidParams(params)
        def templateElement = new TemplateElement(params)

        assert templateElement.save() != null

        params.id = templateElement.id

        def model = controller.show()

        assert model.templateElementInstance == templateElement
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/templateElement/list'

        populateValidParams(params)
        def templateElement = new TemplateElement(params)

        assert templateElement.save() != null

        params.id = templateElement.id

        def model = controller.edit()

        assert model.templateElementInstance == templateElement
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/templateElement/list'

        response.reset()

        populateValidParams(params)
        def templateElement = new TemplateElement(params)

        assert templateElement.save() != null

        // test invalid parameters in update
        params.id = templateElement.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/templateElement/edit"
        assert model.templateElementInstance != null

        templateElement.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/templateElement/show/$templateElement.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        templateElement.clearErrors()

        populateValidParams(params)
        params.id = templateElement.id
        params.version = -1
        controller.update()

        assert view == "/templateElement/edit"
        assert model.templateElementInstance != null
        assert model.templateElementInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/templateElement/list'

        response.reset()

        populateValidParams(params)
        def templateElement = new TemplateElement(params)

        assert templateElement.save() != null
        assert TemplateElement.count() == 1

        params.id = templateElement.id

        controller.delete()

        assert TemplateElement.count() == 0
        assert TemplateElement.get(templateElement.id) == null
        assert response.redirectedUrl == '/templateElement/list'
    }
}
