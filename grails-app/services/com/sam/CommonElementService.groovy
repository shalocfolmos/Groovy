package com.sam

class CommonElementService {

    def serviceMethod() {

    }


    def generateCommonElementGroup (def params) {

        def templateFrameworkId = params.templateFrameworkId
        def checkedSegment = params.checkedSegment
        def contentGroupName = params.contentGroupName
        def checkedSegments = checkedSegment[0..checkedSegment.size()-2].split(",")
        def commonElementGroup = new CommonElementGroup(name:contentGroupName,templateFrameworkId:templateFrameworkId)
        checkedSegments.each() {it ->
            def segment = Segment.findBySegmentId(it)
            def commonElement = new CommonElement(segment:segment)
            commonElementGroup.addToCommonElements(commonElement)
        }
        commonElementGroup.save()
    }
}
