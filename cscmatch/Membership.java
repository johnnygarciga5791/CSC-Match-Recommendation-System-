package cscmatch;

import jsjf.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

public class Membership implements Iterable<Member>, Serializable {

    private UnorderedListADT<Member> mbrList = new LinkedUnorderedList<Member>();

    public Iterator<Member> iterator() {
        return mbrList.iterator();
    }

    public String toString() {
        String result = "";
        for (Member mbr : mbrList) {
            result += mbr.getName() + "\n";
        }
        return result;
    }

    public Member findMember(String name) {
        for (Member mbr : mbrList) {
            if (mbr.getName().equals(name)) {
                return mbr;
            }
        }
        return null;
    }

    public Member addMember(String name, int year) {

        Member newMember = new Member(name, year);

        mbrList.addToRear(newMember);

        return newMember;

    }

    public void save(String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        }
    }

    public static Membership load(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Membership pos = (Membership) ois.readObject();
            return pos;
        }
    }
}
