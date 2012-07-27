package program

class AppFilterFilters {

    def filters = {
        userResultFilter(controller:'result',action:'*') {
            before = {
                if(actionName == "list" || actionName== "index" || actionName=="delete") {
                    redirect(controller: "person", action: "start")
                    return false;
                }
                if(!session.userId) {
                    redirect(controller: "person", action: "start")
                    return false
                }
            }
        }

        adminPersonFilter(controller: 'person',action:'*',excludeAction:"(login)|(start)") {
            if(!session.superuser) {
                redirect(controller: "person", action: "start")
                return false
            }
        }

    }
}
