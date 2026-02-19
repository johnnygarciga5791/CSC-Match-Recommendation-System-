package cscmatch;

import jsjf.*;

import java.io.Serializable;
import java.util.Iterator;

public class Member implements Iterable<Interest>, Serializable {

    public static final int MIN_YEAR = 1;
    public static final int MAX_YEAR = 5;
    private String name;
    private int year;

    private OrderedListADT<Interest> intrList = new LinkedOrderedList<Interest>();

    public Iterator<Interest> iterator() {
        return intrList.iterator();
    }

    public Member(String name, int year) {
        setName(name);
        setYear(year);
    }

    public static String validateName(String name) {
        if (name == null) {
            return "Name may not be null";
        }
        if (name.isBlank() || name.contains(":")) {
            return "Name may not be blank or contain any : characters";
        }
        return null;
    }

    public static String validateYear(int year) {
        if (year < MIN_YEAR || year > MAX_YEAR) {
            return "Year " + year +
                    " is invalid; please specify between " +
                    MIN_YEAR + "-" + MAX_YEAR;
        }
        return null;
    }

    public void setName(String name) {
        String errMsg = validateName(name);
        if (errMsg != null) {
            throw new IllegalArgumentException(errMsg);
        }
        this.name = name;
    }

    public void setYear(int year) {
        String errMsg = validateYear(year);
        if (errMsg != null) {
            throw new IllegalArgumentException(errMsg);
        }
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public Interest addInterest(String topic, int level) {

        Interest existing = findInterest(topic);

        if (existing != null) {
            intrList.remove(existing);
        }

        Interest newinterest = new Interest(topic, level);

        intrList.add(newinterest);

        return newinterest;
    }

    public Interest findInterest(String topic) {
        for (Interest intr : intrList) {
            if (intr.getTopic().equalsIgnoreCase(topic)) {
                return intr;
            }
        }
        return null;
    }

    public String toString() {
        String result = "Name: " + name + ", Year: " + year + "\n";

        for (Interest intr : intrList) {
            result += "  " + intr.getTopic() + ": " + intr.getLevel() + "\n";
        }
        return result;
    }
}
