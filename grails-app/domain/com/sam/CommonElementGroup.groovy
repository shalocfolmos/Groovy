package com.sam

class CommonElementGroup {

    String name

    static hasMany = [commonElements:CommonElement]

    static constraints = {
        name nullable: false
    }
}
