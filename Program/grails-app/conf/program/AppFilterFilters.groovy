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

        adminFilter(controller: 'admin',action:'*') {
            before = {
                if(controllerName == "admin" && (actionName=="login" || actionName=="start"))
                {
                    return true
                }

                if(actionName=="create" || actionName == "save") {
                    return true
                }

                if(!session.superuser) {
                    redirect(controller: "admin", action: "start")
                    return false
                }
            }
        }


    }
}
