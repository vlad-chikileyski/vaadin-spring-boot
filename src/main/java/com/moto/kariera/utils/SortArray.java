package com.moto.kariera.utils;

public class SortArray {

    public void nonSortArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minE = array[i];
            int indE = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minE) {
                    minE = array[j];
                    indE = j;
                }
            }
            if (i != indE) {
                int temp = array[i];
                array[i] = array[indE];
                array[indE] = temp;
            }
        }
        System.out.println("[Total: array min:= " + array[0]);
    }
}
