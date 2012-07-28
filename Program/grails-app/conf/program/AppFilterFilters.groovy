package program

class AppFilterFilters {

    def filters = {
        userResultFilter(controller:'result',action:'*') {
            before = {
                if(!session.userId) {
                    redirect(controller: "person", action: "start")
                    return false
                }
            }
        }

        personRulter(controller: "person", action: "*", excludeAction: "(start)|(login)") {
            before = {
                if(!session.superuser) {
                    redirect(controller: "admin", action: "start")
                    return false
                }
            }
        }

        adminFilter(controller: 'admin',action:'*') {
            before = {
                if(actionName=="login" || actionName=="start")
                {
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
