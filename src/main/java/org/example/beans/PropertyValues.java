package org.example.beans;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyValues {
    private final List<PropertyValue> list = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        list.add(pv);
    }

    public PropertyValue[] toArray() {
        return this.list.toArray(new PropertyValue[0]);
    }

    @Nullable
    public PropertyValue getPropertyValue(String propertyName) {
        Optional<PropertyValue> matchedPV = this.list.stream()
                .filter(pv -> pv.getName().equals(propertyName))
                .findFirst();
        return matchedPV.orElse(null);
    }
}
