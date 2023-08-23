package org.example.beans.factory;


import lombok.Getter;
import lombok.Setter;
import org.example.beans.PropertyValues;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@SuppressWarnings({"rawtypes"})
public class BeanDefinition {

    @NotNull
    private Class beanClass;

    @NotNull
    private PropertyValues propertyValues;

    public BeanDefinition(@NotNull Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(@NotNull Class beanClass, @Nullable PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
}
