package com.sam

class attributesCommonElement {

    static belongsTo = [group:CommonElementGroup]
    Segment segment


    static hasMany = [attributes:CommonElementAttribute]


    static constraints = {
        group column:"template_framework_fk"
        segment column:"segment_fk"
        attributes(lazy: false, cascade: "all")

    }
}
