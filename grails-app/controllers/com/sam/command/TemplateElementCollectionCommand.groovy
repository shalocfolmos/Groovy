package com.sam.command

import grails.validation.Validateable
import com.sam.TemplateElement
import org.apache.commons.collections.ListUtils
import org.apache.commons.collections.FactoryUtils

@Validateable
class TemplateElementCollectionCommand {
    List<TemplateElement> templateElements = ListUtils.lazyList(new ArrayList<TemplateElement>(), FactoryUtils.instantiateFactory(TemplateElement));
    Long segmentId
    Long templateId

}
