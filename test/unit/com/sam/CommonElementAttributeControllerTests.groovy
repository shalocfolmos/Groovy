package com.sam



import org.junit.*
import grails.test.mixin.*

@TestFor(CommonElementAttributeController)
@Mock(CommonElementAttribute)
class CommonElementAttributeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/commonElementAttribute/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.commonElementAttributeInstanceList.size() == 0
        assert model.commonElementAttributeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.commonElementAttributeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.commonElementAttributeInstance != null
        assert view == '/commonElementAttribute/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/commonElementAttribute/show/1'
        assert controller.flash.message != null
        assert CommonElementAttribute.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/commonElementAttribute/list'

        populateValidParams(params)
        def commonElementAttribute = new CommonElementAttribute(params)

        assert commonElementAttribute.save() != null

        params.id = commonElementAttribute.id

        def model = controller.show()

        assert model.commonElementAttributeInstance == commonElementAttribute
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/commonElementAttribute/list'

        populateValidParams(params)
        def commonElementAttribute = new CommonElementAttribute(params)

        assert commonElementAttribute.save() != null

        params.id = commonElementAttribute.id

        def model = controller.edit()

        assert model.commonElementAttributeInstance == commonElementAttribute
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/commonElementAttribute/list'

        response.reset()

        populateValidParams(params)
        def commonElementAttribute = new CommonElementAttribute(params)

        assert commonElementAttribute.save() != null

        // test invalid parameters in update
        params.id = commonElementAttribute.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/commonElementAttribute/edit"
        assert model.commonElementAttributeInstance != null

        commonElementAttribute.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/commonElementAttribute/show/$commonElementAttribute.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        commonElementAttribute.clearErrors()

        populateValidParams(params)
        params.id = commonElementAttribute.id
        params.version = -1
        controller.update()

        assert view == "/commonElementAttribute/edit"
        assert model.commonElementAttributeInstance != null
        assert model.commonElementAttributeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/commonElementAttribute/list'

        response.reset()

        populateValidParams(params)
        def commonElementAttribute = new CommonElementAttribute(params)

        assert commonElementAttribute.save() != null
        assert CommonElementAttribute.count() == 1

        params.id = commonElementAttribute.id

        controller.delete()

        assert CommonElementAttribute.count() == 0
        assert CommonElementAttribute.get(commonElementAttribute.id) == null
        assert response.redirectedUrl == '/commonElementAttribute/list'
    }
}
