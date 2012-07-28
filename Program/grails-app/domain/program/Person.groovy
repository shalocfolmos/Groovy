package program

class Person {


    String username
    String password
    String division
    Boolean superuser;
    static hasMany = [results:Result]

    static constraints = {
        username nullable: true, maxSize:30
        password nullable: true, maxSize: 80
        division nullable: true, maxSize: 30
        results unique: true
    }


}


