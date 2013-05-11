package com.sam

public enum AttributeType {
    ANCESOR_HREF_ATTR("href"),
    IMAGE_SRC("src"),
    IMAGE_ALT("alt"),
    TEXT_CONTACT("text")
    String displayContent
    AttributeType(String displayContent) {
        this.displayContent = displayContent
    }
}