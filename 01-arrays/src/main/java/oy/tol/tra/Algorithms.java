package oy.tol.tra;

public class Algorithms {
    
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    flag = false;
                    swap(array, j, j + 1);
                }
            }
            if (flag == true) {
                break;
            }
        }
    }

    public static <T> void reverse(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        
        int i = 0;
        while (i < array.length / 2) {
            swap(array, i, array.length - i - 1);
            i++;
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

