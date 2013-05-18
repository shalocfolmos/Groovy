package com.sam

class CommonElementAttribute {

    AttributeType attribute_type
    String attribute_value

   static belongsTo = [commonElement:CommonElement]

    static constraints = {
        commonElement column:"common_element_fk"
    }
}
