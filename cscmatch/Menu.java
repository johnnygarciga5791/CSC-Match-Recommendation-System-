package cscmatch;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import jsjf.LinkedOrderedList;

public class Menu {

    private Membership mbrshp;
    private Scanner scnr;
    private boolean running;
    private boolean unsavedChanges;

    public Menu() {
        mbrshp = new Membership();
        scnr = new Scanner(System.in);
        running = true;
        unsavedChanges = false;
    }

    public void run() {

        int choice;

        while (running) {

            System.out.println("1. Load the Members");
            System.out.println("2. Save the Members");
            System.out.println("3. List All Members");
            System.out.println("4. Add a Member");
            System.out.println("5. Remove a Member");
            System.out.println("6. List Members");
            System.out.println("7. Add an interst to a member");
            System.out.println("8. Quit\n");

            choice = getBoundedInt("Enter a choice from the Menu above (1-8): ", 1, 8);
            handleMethod(choice);
        }
    }

    private void handleMethod(int choice) {
        switch (choice) {

        case 1:
            doLoadMembers();
            break;
        case 2:
            doSaveMembers();
            break;
        case 3:
            doListAllMembers();
            break;
        case 4:
            doAddMember();
            break;
        case 5:
            doRemoveMember();
            break;
        case 6:
            doListMember();
            break;
        case 7:
            doAddInterest();
            break;
        case 8:
            doQuit();
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private void doLoadMembers() {
        while (true) {
            System.out.println("Load Members");
            String fileName = scnr.nextLine();

            if (fileName.isEmpty()) {
                return;
            }

            try {
                mbrshp = Membership.load(fileName);
                unsavedChanges = false;
                System.out.println("The file operation successfully worked");
                return;
            } catch (IOException e) {
                System.out.println("Load failed: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found: " + e.getMessage());
            }

        }
    }

    private void doSaveMembers() {
        while (true) {
            System.out.println("Save Members");

            String fileName = scnr.nextLine();

            if (fileName.isEmpty()) {
                return;
            }

            try {
                mbrshp.save(fileName);
                unsavedChanges = false;
                System.out.println("File operation Succesfully works");
                return;
            } catch (IOException e) {
                System.out.println("Saved failed: " + e.getMessage());
            }

        }
    }

    private void doListAllMembers() {
        System.out.println("List All Members");
        System.out.println(mbrshp);
    }

    private void doAddMember() {
        String errMsg;
        System.out.println("Add Member");
        System.out.println("Name: ");
        String name = scnr.nextLine();
        Member mbr = mbrshp.findMember(name);
        if (mbr != null) {
            System.out.println("That member already exists");
            return;
        }
        errMsg = Member.validateName(name);
        if (errMsg != null) {
            System.out.println(errMsg);
            return;
        }

        int year = getBoundedInt(" Year: ", Member.MIN_YEAR, Member.MAX_YEAR);
        mbrshp.addMember(name, year);
        unsavedChanges = true;
    }

    private void doRemoveMember() {
        System.out.println("Remove Member");

        Iterator<Member> iter = mbrshp.iterator();
        boolean found = false;
        String name = scnr.nextLine();

        while (iter.hasNext()) {
            Member mbr = iter.next();
            if (mbr.getName().equals(name)) {
                iter.remove();
                found = true;
                System.out.println(name + " has been removed");
                unsavedChanges = true;
                break;
            }
        }

        if (!found) {
            System.out.println(name + " was not found in the members ");
        }

    }

    private void doListMember() {
        System.out.println("List Member");

        String name = scnr.nextLine();
        Member mbr = mbrshp.findMember(name);

        if (mbr != null) {
            System.out.println(mbr);

            LinkedOrderedList<Compatibility> compaList = new LinkedOrderedList<>();

            for (Member otherMbr : mbrshp) {
                if (!otherMbr.equals(mbr)) {
                    compaList.add(new Compatibility(mbr, otherMbr));
                }
            }

            System.out.println("Top 5 Matches");
            int count = 0;
            for (Compatibility comp : compaList) {
                if (count >= 5)
                    break;
                System.out.println(comp.getOtherMbr().getName() + ": " + comp.getScore());
                count++;
            }

        } else {
            System.out.println("The user could not be found");
        }
    }

    private void doAddInterest() {

        System.out.println("Name: ");
        String name = scnr.nextLine();
        Member mbr = mbrshp.findMember(name);
        if (mbr == null) {
            System.out.println("Member does not exist");
            return;
        }

        System.out.println("Topic: ");
        String topic = scnr.nextLine();

        String errMsg;

        errMsg = Interest.validateTopic(topic);
        if (errMsg != null) {
            System.out.println(errMsg);
            return;

        }

        int level = getBoundedInt("Level: ", Interest.MIN_LEVEL, Interest.MAX_LEVEL);

        mbr.addInterest(topic, level);

        unsavedChanges = true;

    }

    private void doQuit() {
        System.out.println("Quit");
        String Yes = "yes";
        String No = "No";

        if (unsavedChanges) {
            String reply = "";

            while (true) {

                System.out.println("You still have unsaved changes, are you sure you want to quit? (Yes or No)");
                reply = scnr.nextLine();

                if ((reply.equalsIgnoreCase(Yes))) {
                    System.out.println("Quitting");
                    running = false;
                    return;
                }

                else if ((reply.equalsIgnoreCase(No))) {
                    System.out.println("Cancled, Please save your changes");
                    return;
                }

            }

        }

        running = false;
    }

    private int getBoundedInt(String prompt, int min, int max) {
        int result;
        while (true) {
            System.out.print(prompt);
            if (scnr.hasNextInt()) {
                result = scnr.nextInt();
                scnr.nextLine();
                if (result >= min && result <= max) {
                    return result;
                }
                System.out.printf("Number must be between %d and %d\n", min, max);
            } else {
                System.out.println("That is not a number");
                scnr.nextLine();
            }
        }
    }

}
