package com.sam

class Attribute {


    AttributeType attribute_type
    String attribute_value


    static constraints = {
        attribute_value nullable: false
    }

    static mapping = {
        sort attrivute_type:"asc"
    }
}
