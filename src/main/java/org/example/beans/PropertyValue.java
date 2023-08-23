package org.example.beans;

import lombok.Getter;

@Getter
public class PropertyValue {
    /**
     * Property的name
     */
    private final String name;
    /**
     * 用于设置此Property的值
     */
    private final Object val;

    public PropertyValue(String name, Object val) {
        this.name = name;
        this.val = val;
    }
}
