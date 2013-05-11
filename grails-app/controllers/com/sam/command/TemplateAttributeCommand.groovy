package com.sam.command

import grails.validation.Validateable
import com.sam.AttributeType

@Validateable
class TemplateAttributeCommand {
    AttributeType attribute_type
    String attribute_value
}
