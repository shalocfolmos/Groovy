package com.sam

class CommonElementService {

    def serviceMethod() {

    }


    def generateCommonElementGroup (def request) {
        def templateFrameworkId = request.post["templateFrameworkId"]
        def checkedSegment = request.post["checkedSegment"]
        def contentGroupName = request.post["contentGroupName"]
        def commonElementGroup = new CommonElementGroup(name:contentGroupName,templateFrameworkId:templateFrameworkId)
        checkedSegment.each() {it ->
            def segment = Segment.findBySegmentId(it)
            def commonElement = new CommonElement(segment:segment)
            commonElement.save()
        }
        commonElementGroup.save()
    }
}
