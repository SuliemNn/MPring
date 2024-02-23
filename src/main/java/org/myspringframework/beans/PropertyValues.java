package org.myspringframework.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {

        /*
        toArray(T[] a):
        接受一个类型为 `T[]` 的数组作为参数，并将 `ArrayList` 中的元素转换为该数组中的元素。
        如果传入的数组长度小于 `ArrayList` 的大小，则会创建一个新的数组，并将`ArrayList` 中的元素复制到新数组中；
        如果传入的数组长度大于等于 `ArrayList` 的大小，则会直接将元素复制到传入的数组中，并返回该数组。
        如果传入的数组类型与 `ArrayList` 中的元素类型不兼容，则会抛出 `ArrayStoreException` 异常。
        如果传入的数组长度大于 `ArrayList` 的大小，且数组中存在空闲位置（即数组中的某些元素为 `null`），
        则会将 `ArrayList` 中的元素复制到数组中，并将空闲位置设置为 `null`；如果数组中不存在空闲位置，则会创建一个新的数组。
        */

        //不直接返回this.propertyValueList.toArray()是因为这样的操作会返回一个Object[], 需要强制的进行类型转换
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

}
