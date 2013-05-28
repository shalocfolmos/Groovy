package com.sam

class TemplateElement {

    String elementId
    Segment segment
    static hasMany = [attributes:Attribute]

    static mapping = {
        elementId sort:"desc"
        attributes (lazy:false,cascade:"all")
    }

    static constraints = {
        segment nullable: false
        attributes(default:[])
    }

    def afterInsert(){
        if(this.segment.segmentType == SegmentType.ANCESTOR) {
            def hrefAttr =  new Attribute(attribute_type: AttributeType.ANCESOR_HREF_ATTR,attribute_value:"")
            this.addToAttributes(hrefAttr)
        }
        else if(this.segment.segmentType == SegmentType.IMAGE){
            def imageAttr =  new Attribute(attribute_type: AttributeType.IMAGE_SRC,attribute_value:"")
            this.addToAttributes(imageAttr)
            def altAttr =  new Attribute(attribute_type: AttributeType.IMAGE_ALT,attribute_value:"")
            this.addToAttributes(altAttr)
        }
        if(this.segment.shouldModifyContext) {
            def textAttr =  new Attribute(attribute_type: AttributeType.TEXT_CONTACT,attribute_value:"")
            this.addToAttributes(textAttr)
        }
        this.save()
    }
}
