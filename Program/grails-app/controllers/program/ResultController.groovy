package program

import org.springframework.dao.DataIntegrityViolationException

class ResultController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }


    def list(String personId) {
        def resultInstanceTotal = Result.findAllByPerson(Person.get(personId))
        render(view:"list",model:[resultInstanceList: Result.findAllByPerson(Person.get(personId),[max:20]), resultInstanceTotal: resultInstanceTotal.size()])
    }

    def create() {
        [resultInstance: new Result(params)]
    }

    def save() {
        def resultInstance = new Result(params)
        if (!resultInstance.save(flush: true)) {
            render(view: "create", model: [resultInstance: resultInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'result.label', default: 'Result'), resultInstance.id])
        redirect(action: "show", id: resultInstance.id)
    }

    def show(Long id) {
        def resultInstance = Result.get(id)
        if (!resultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'result.label', default: 'Result'), id])
            redirect(action: "list")
            return
        }

        [resultInstance: resultInstance]
    }

    def edit(Long id) {
        def resultInstance = Result.get(id)
        if (!resultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'result.label', default: 'Result'), id])
            redirect(action: "list")
            return
        }

        [resultInstance: resultInstance]
    }

    def update(Long id, Long version) {
        def resultInstance = Result.get(id)
        if (!resultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'result.label', default: 'Result'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (resultInstance.version > version) {
                resultInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'result.label', default: 'Result')] as Object[],
                          "Another user has updated this Result while you were editing")
                render(view: "edit", model: [resultInstance: resultInstance])
                return
            }
        }

        resultInstance.properties = params

        if (!resultInstance.save(flush: true)) {
            render(view: "edit", model: [resultInstance: resultInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'result.label', default: 'Result'), resultInstance.id])
        redirect(action: "show", id: resultInstance.id)
    }

    def delete(Long id) {
        def resultInstance = Result.get(id)
        if (!resultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'result.label', default: 'Result'), id])
            redirect(action: "list")
            return
        }

        try {
            resultInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'result.label', default: 'Result'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'result.label', default: 'Result'), id])
            redirect(action: "show", id: id)
        }
    }
}
