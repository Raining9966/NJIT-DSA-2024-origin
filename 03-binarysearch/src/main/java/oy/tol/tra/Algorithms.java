package oy.tol.tra;

/**
 * A collection of generic algorithms for sorting, searching, and manipulating arrays.
 */
public class Algorithms {

    /**
     * Swaps two elements in an array.
     *
     * @param array the array containing the elements to be swapped
     * @param index1 the index of the first element to be swapped
     * @param index2 the index of the second element to be swapped
     */
    public static <T> void swap(T[] array, int index1, int index2) {
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    /**
     * Sorts an array using the quicksort algorithm.
     *
     * @param array the array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null || array.length < 2) {
            return; // Nothing to sort
        }
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Reverses the order of elements in an array.
     *
     * @param array the array to be reversed
     */
    public static <T> void reverse(T[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            swap(array, left++, right--);
        }
    }

    /**
     * Performs a binary search on a sorted array.
     *
     * @param value the value to search for
     * @param array the sorted array to search in
     * @param fromIndex the starting index of the search
     * @param toIndex the ending index of the search
     * @return the index of the value if found, otherwise -1
     */
    public static <T extends Comparable<T>> int binarySearch(T value, T[] array, int fromIndex, int toIndex) {
        if (array == null || fromIndex > toIndex) {
            return -1; // Invalid input
        }
        int mid;
        while (fromIndex <= toIndex) {
            mid = fromIndex + (toIndex - fromIndex) / 2;
            int result = value.compareTo(array[mid]);

            if (result == 0) {
                return mid;
            } else if (result < 0) {
                toIndex = mid - 1;
            } else {
                fromIndex = mid + 1;
            }
        }
        return -1; // Value not found
    }

    /**
     * Sorts an array using the quicksort algorithm.
     *
     * @param array the array to be sorted
     * @param begin the starting index of the array
     * @param end the ending index of the array
     */
    private static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    /**
     * Partitions the array around a pivot for the quicksort algorithm.
     *
     * @param array the array to be partitioned
     * @param begin the starting index of the array
     * @param end the ending index of the array
     * @return the index of the pivot element
     */
    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        E pivot = array[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, end);
        return i + 1;
    }
}
