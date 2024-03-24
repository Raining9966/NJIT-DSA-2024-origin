package oy.tol.tra;

public class Grades<T extends Comparable<T>> {

    private T[] grades;

    public Grades(T[] grades) {
        if (grades == null) {
            throw new IllegalArgumentException("Grades array cannot be null.");
        }
        this.grades = grades.clone();
    }

    public void reverse() {
        int start = 0;
        int end = grades.length - 1;

        while (start < end) {
            T temp = grades[start];
            grades[start] = grades[end];
            grades[end] = temp;
            start++;
            end--;
        }
    }

    public void sort() {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < grades.length; i++) {
                if (grades[i - 1].compareTo(grades[i]) > 0) {
                    T temp = grades[i - 1];
                    grades[i - 1] = grades[i];
                    grades[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public T[] getArray() {
        return grades.clone();
    }
}

