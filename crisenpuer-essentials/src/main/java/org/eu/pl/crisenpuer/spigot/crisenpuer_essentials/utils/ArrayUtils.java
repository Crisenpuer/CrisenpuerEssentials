package org.eu.pl.crisenpuer.spigot.crisenpuer_essentials.utils;

import java.lang.reflect.Array;

public class ArrayUtils<type> {
    
    /**
     * Removes the first element from the given array.
     * @param arr The original array.
     * @return A new array without the first element, or an empty array if the original array is empty.
     */
    public type[] removeFirstElement(type[] arr) {
        if (arr == null || arr.length == 0) {
            return (type[]) Array.newInstance(arr.getClass().getComponentType(), 0);
        }

        type[] newArray = (type[]) Array.newInstance(arr.getClass().getComponentType(), arr.length - 1);

        System.arraycopy(arr, 1, newArray, 0, arr.length-1);

        return newArray;
    }
}
