package program

class AppFilterFilters {

    def filters = {
        all(controller:'(person)|(result)',action:'*',actionExclude:"(login)|(start)") {
            before = {
                if(!session.userId) {
                    redirect(controller: "person", action: "start")
                    return false
                }
            }
        }

    }
}
