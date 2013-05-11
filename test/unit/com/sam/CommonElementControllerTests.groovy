package com.sam



import org.junit.*
import grails.test.mixin.*

@TestFor(CommonElementController)
@Mock(CommonElement)
class CommonElementControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/commonElement/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.commonElementInstanceList.size() == 0
        assert model.commonElementInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.commonElementInstance != null
    }

    void testSave() {
        controller.save()

        assert model.commonElementInstance != null
        assert view == '/commonElement/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/commonElement/show/1'
        assert controller.flash.message != null
        assert CommonElement.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/commonElement/list'

        populateValidParams(params)
        def commonElement = new CommonElement(params)

        assert commonElement.save() != null

        params.id = commonElement.id

        def model = controller.show()

        assert model.commonElementInstance == commonElement
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/commonElement/list'

        populateValidParams(params)
        def commonElement = new CommonElement(params)

        assert commonElement.save() != null

        params.id = commonElement.id

        def model = controller.edit()

        assert model.commonElementInstance == commonElement
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/commonElement/list'

        response.reset()

        populateValidParams(params)
        def commonElement = new CommonElement(params)

        assert commonElement.save() != null

        // test invalid parameters in update
        params.id = commonElement.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/commonElement/edit"
        assert model.commonElementInstance != null

        commonElement.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/commonElement/show/$commonElement.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        commonElement.clearErrors()

        populateValidParams(params)
        params.id = commonElement.id
        params.version = -1
        controller.update()

        assert view == "/commonElement/edit"
        assert model.commonElementInstance != null
        assert model.commonElementInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/commonElement/list'

        response.reset()

        populateValidParams(params)
        def commonElement = new CommonElement(params)

        assert commonElement.save() != null
        assert CommonElement.count() == 1

        params.id = commonElement.id

        controller.delete()

        assert CommonElement.count() == 0
        assert CommonElement.get(commonElement.id) == null
        assert response.redirectedUrl == '/commonElement/list'
    }
}
