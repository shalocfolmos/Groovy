package com.sam

import com.sam.command.TemplateElementCollectionCommand

class TemplateService {

    def createPreview(Map params,String sessionId) {
        def attribute_id_array = params["attribute_id[]"],
        attribute_val_array = params["attribute_val[]"],
        preview = new Preview(templateId: params["templateId"],previewId:String.valueOf(Calendar.getInstance().getTime().getTime()))
        preview.setSessionId(sessionId)
        attribute_id_array.eachWithIndex {it,index->
            def val = attribute_val_array[index]
            def previewAttribute = new PreviewAttribute()
            previewAttribute.setAttributeId(Long.valueOf(it))
            previewAttribute.setAttribute_value(val)
            previewAttribute.save()
            preview.addToPreviewAttributes(previewAttribute)
        }
        preview.save()
        return preview.previewId
    }

    def deletePreview(String sessionId) {
        def previewList = Preview.findAllBySessionId(sessionId)
        previewList.each {it->
            it.delete()
        }
    }

    def markShouldDelete(String sessionId) {
        def previewList = Preview.findAllBySessionId(sessionId)
        previewList.each {it->
            it.setShouldDeleted(true)
            it.save()
        }
    }

    def setTemplateContent(TemplateElementCollectionCommand cmd) {
        if(!cmd.templateId || !Template.get(cmd.templateId))
        {
            throw new RuntimeException("Template Id wrong")
        }
        cmd.templateElements.each {it->
            it.attributes.each {attr->
                def attribute = Attribute.get(attr.id)
                attribute.save()
            }
        }
    }

}









