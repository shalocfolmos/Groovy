package com.sam.comparator

import com.sam.TemplateElement


class TemplateElementComparator implements Comparator<TemplateElement>{
    @Override
    int compare(TemplateElement t, TemplateElement t1) {
        return t.getSegment().getName().compareTo(t1.getSegment().getName())
    }
}
