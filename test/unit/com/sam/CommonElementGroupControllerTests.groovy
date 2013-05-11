package com.sam



import org.junit.*
import grails.test.mixin.*

@TestFor(CommonElementGroupController)
@Mock(CommonElementGroup)
class CommonElementGroupControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/commonElementGroup/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.commonElementGroupInstanceList.size() == 0
        assert model.commonElementGroupInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.commonElementGroupInstance != null
    }

    void testSave() {
        controller.save()

        assert model.commonElementGroupInstance != null
        assert view == '/commonElementGroup/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/commonElementGroup/show/1'
        assert controller.flash.message != null
        assert CommonElementGroup.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/commonElementGroup/list'

        populateValidParams(params)
        def commonElementGroup = new CommonElementGroup(params)

        assert commonElementGroup.save() != null

        params.id = commonElementGroup.id

        def model = controller.show()

        assert model.commonElementGroupInstance == commonElementGroup
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/commonElementGroup/list'

        populateValidParams(params)
        def commonElementGroup = new CommonElementGroup(params)

        assert commonElementGroup.save() != null

        params.id = commonElementGroup.id

        def model = controller.edit()

        assert model.commonElementGroupInstance == commonElementGroup
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/commonElementGroup/list'

        response.reset()

        populateValidParams(params)
        def commonElementGroup = new CommonElementGroup(params)

        assert commonElementGroup.save() != null

        // test invalid parameters in update
        params.id = commonElementGroup.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/commonElementGroup/edit"
        assert model.commonElementGroupInstance != null

        commonElementGroup.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/commonElementGroup/show/$commonElementGroup.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        commonElementGroup.clearErrors()

        populateValidParams(params)
        params.id = commonElementGroup.id
        params.version = -1
        controller.update()

        assert view == "/commonElementGroup/edit"
        assert model.commonElementGroupInstance != null
        assert model.commonElementGroupInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/commonElementGroup/list'

        response.reset()

        populateValidParams(params)
        def commonElementGroup = new CommonElementGroup(params)

        assert commonElementGroup.save() != null
        assert CommonElementGroup.count() == 1

        params.id = commonElementGroup.id

        controller.delete()

        assert CommonElementGroup.count() == 0
        assert CommonElementGroup.get(commonElementGroup.id) == null
        assert response.redirectedUrl == '/commonElementGroup/list'
    }
}
