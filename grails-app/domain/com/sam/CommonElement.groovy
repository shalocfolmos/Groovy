package com.sam

class CommonElement {

    String name
    Segment segment
    TemplateFramework templateFramework

    static hasMany = [attributes:CommonElementAttribute]

    static constraints = {
        name unique:true,nullable: false
    }
}
