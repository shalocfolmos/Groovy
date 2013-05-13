package com.sam

class CommonElement {

    static belongsTo = [group:CommonElementGroup]
    Segment segment


    static hasMany = [attributes:CommonElementAttribute]


    static constraints = {
        name unique:true,nullable: false
        group column:"template_framework_fk"
        segment column:"segment_fk"
    }
}
