package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;

public class Grades<T extends Number & Comparable<T>> {

    private T[] grades;
    private Comparator<T> comparator;

    public Grades(T[] grades, Comparator<T> comparator) {
        if (grades == null || comparator == null) {
            throw new IllegalArgumentException("Input array and comparator cannot be null.");
        }
        this.grades = grades.clone();
        this.comparator = comparator;
    }

    public void reverse() {
        for (int i = 0; i < grades.length / 2; i++) {
            T temp = grades[i];
            grades[i] = grades[grades.length - 1 - i];
            grades[grades.length - 1 - i] = temp;
        }
    }

    public void sort() {
        Arrays.sort(grades, comparator);
    }

    public T[] getArray() {
        return grades.clone();
    }

    public double calculateAverage() {
        double sum = 0;
        for (T grade : grades) {
            sum += grade.doubleValue();
        }
        return sum / grades.length;
    }

    public T findHighestGrade() {
        T highest = grades[0];
        for (T grade : grades) {
            if (grade.compareTo(highest) > 0) {
                highest = grade;
            }
        }
        return highest;
    }
}
