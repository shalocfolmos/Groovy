package program

import org.springframework.dao.DataIntegrityViolationException

class AdminController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }


    def resultList(String personId) {
        render(view:"resultList",model:[resultInstanceList: Result.findAll([max:20]), resultInstanceTotal: Result.findAll().size()])
    }

    def resultShow(Long id) {
        def resultInstance = Result.get(id)
        if (!resultInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'result.label', default: 'Result'), id])
            redirect(action: "list")
            return
        }

        [resultInstance: resultInstance]
    }

    def resultDelete(Long id) {
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

    def start() {
        if(!session.superuser)
        {
            render(view:"/admin/login")
            return
        }
        return redirect(controller: "admin",action: "resultList")
    }

    def login() {
        def username = params["username"]
        def password = params["password"]
        def person = Person.findByUsernameAndPassword(username,password)
        if (person && person.superuser== true) {
            session.superuser = person.id
            return redirect(controller: "admin",action: "resultList")
        }
        else
        {
            return render(view:"/admin/login",model: [error:"True"])
        }
    }
}
