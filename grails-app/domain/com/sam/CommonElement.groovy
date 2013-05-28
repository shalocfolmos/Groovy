package com.sam

class CommonElement {

    static belongsTo = [group:CommonElementGroup]
    Segment segment


    static hasMany = [attributes:CommonElementAttribute]


    static constraints = {
        group column:"template_framework_fk"
        segment column:"segment_fk"
        attributes(lazy: false, cascade: "all")
    }

    def afterInsert(){
        if(this.segment.segmentType == SegmentType.ANCESTOR) {
            def hrefAttr =  new CommonElementAttribute(attribute_type: AttributeType.ANCESOR_HREF_ATTR,attribute_value:"")
            this.addToAttributes(hrefAttr)
        }
        else if(this.segment.segmentType == SegmentType.IMAGE){
            def imageAttr =  new CommonElementAttribute(attribute_type: AttributeType.IMAGE_SRC,attribute_value:"")
            this.addToAttributes(imageAttr)
            def altAttr =  new CommonElementAttribute(attribute_type: AttributeType.IMAGE_ALT,attribute_value:"")
            this.addToAttributes(altAttr)
        }
        if(this.segment.shouldModifyContext) {
            def textAttr =  new CommonElementAttribute(attribute_type: AttributeType.TEXT_CONTACT,attribute_value:"")
            this.addToAttributes(textAttr)
        }
        this.save()
    }
}
