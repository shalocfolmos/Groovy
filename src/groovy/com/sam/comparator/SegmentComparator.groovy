package com.sam.comparator

import com.sam.TemplateElement
import com.sam.Segment

/**
 * Created with IntelliJ IDEA.
 * User: sam-sun
 * Date: 7/6/13
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
class SegmentComparator implements Comparator<Segment>{
    @Override
    int compare(Segment t, Segment t1) {
        return t.getName().compareTo(t1.getName())
    }
}
