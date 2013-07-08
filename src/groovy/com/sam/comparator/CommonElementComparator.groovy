package com.sam.comparator

import com.sam.Segment
import com.sam.CommonElement

/**
 * Created with IntelliJ IDEA.
 * User: sam-sun
 * Date: 7/8/13
 * Time: 1:10 PM
 * To change this template use File | Settings | File Templates.
 */
class CommonElementComparator implements Comparator<CommonElement>{
    @Override
    int compare(CommonElement t, CommonElement t1) {
        return t.getSegment().getName().compareTo(t1.getSegment().getName())
    }
}
