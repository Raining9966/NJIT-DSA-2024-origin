package oy.tol.tra;

import java.util.Arrays;

public class Grades<T extends Comparable<T>> {

    private T[] grades;

    public Grades(T[] grades) {
        this.grades = grades.clone();
    }

    public void reverse() {
        for (int i = 0; i < grades.length / 2; i++) {
            T temp = grades[i];
            grades[i] = grades[grades.length - 1 - i];
            grades[grades.length - 1 - i] = temp;
        }
    }

    public void sort() {
        Arrays.sort(grades);
    }

    public T[] getArray() {
        return grades.clone();
    }
}
