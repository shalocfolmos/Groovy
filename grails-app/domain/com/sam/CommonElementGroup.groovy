package com.sam

class CommonElementGroup {

    String name
    String templateFrameworkId

    static hasMany = [commonElements:CommonElement]

    static constraints = {
        name nullable: false
        templateFrameworkId nullable: false
        commonElements cascade:"all"
    }
}
