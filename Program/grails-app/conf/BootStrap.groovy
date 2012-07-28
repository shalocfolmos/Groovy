import program.Person

class BootStrap {

    def init = { servletContext ->
        new Person(username: "sam",password: "123",superuser: true,"总部").save()
    }
    def destroy = {
    }
}
