package edu.utdallas;

import java.util.HashMap;
import java.util.LinkedList;


public class Drops
{
    public static void main(String [] args) {


    }


    public static String modify(String drops, String dropsToOperateOn, String operation) {
        // Please write your code here.

        String answer = "INVALID";

        //we will have at most 2 linked lists to operate on
        LinkedList<String> dropList1 = new LinkedList<String>();
        LinkedList<String> dropList2 = new LinkedList<String>();

        //splits the drops string into two
        String[] dropArr = drops.split(",", -2);

        //for add the second elem will be added to the first
        String[] dropOperands = dropsToOperateOn.split(",", -2);

        //these two for loops populate the two linked lists with their drops
        for (int i = 0; i < dropArr[0].length(); i++) {
            char current = dropArr[0].charAt(i);
            if (Character.isLetter(current)) {
                dropList1.add(Character.toString(current));
            }
        }

        for (int i = 0; i < dropArr[1].length(); i++) {
            char current = dropArr[1].charAt(i);
            if (Character.isLetter(current)) {
                dropList2.add(Character.toString(current));
            }
        }

        //swap
        if (operation.equals("swap")) {
            //do swap

            int index1;
            int index2;
            String temp = "";

            //both in list1
            if (dropList1.contains(dropOperands[0]) && dropList1.contains(dropOperands[1])) {
                index1 = dropList1.indexOf(dropOperands[0]);
                index2 = dropList1.indexOf(dropOperands[1]);

                temp = dropList1.get(index1);
                dropList1.set(index1, dropList1.get(index2));
                dropList1.set(index2, temp);
            }
            //both in list2
            else if (dropList2.contains(dropOperands[0]) && dropList2.contains(dropOperands[1])) {
                index1 = dropList2.indexOf(dropOperands[0]);
                index2 = dropList2.indexOf(dropOperands[1]);

                temp = dropList2.get(index1);
                dropList2.set(index1, dropList2.get(index2));
                dropList2.set(index2, temp);
            }
            //op1 in list1 and op2 in list2
            else if (dropList1.contains(dropOperands[0]) && dropList2.contains(dropOperands[1])) {
                index1 = dropList1.indexOf(dropOperands[0]);
                index2 = dropList2.indexOf(dropOperands[1]);

                temp = dropList1.get(index1);
                dropList1.set(index1, dropList2.get(index2));
                dropList2.set(index2, temp);
            }
            //op1 in list2 and op2 in list 1
            else if (dropList2.contains(dropOperands[0]) && dropList1.contains(dropOperands[1])) {
                index1 = dropList2.indexOf(dropOperands[0]);
                index2 = dropList1.indexOf(dropOperands[1]);

                temp = dropList2.get(index1);
                dropList2.set(index1, dropList1.get(index2));
                dropList1.set(index2, temp);
            }
            //elements not found
            else {
                return "INVALID";
            }
        }
        //remove
        else if (operation.equals("remove")) {
            //do remove
            if (dropList1.contains(dropsToOperateOn)) {
                dropList1.remove(dropsToOperateOn);
            }
            else if (dropList2.contains(dropsToOperateOn)) {
                dropList2.remove(dropsToOperateOn);
            }
            else {
                return "INVALID";
            }
        }
        //add
        //second operand is the node we are adding to the first operand (including any children from the second)
        else {
            //do add
            LinkedList<String> toAdd = new LinkedList<String>();

            //first we need to make a new list that starts with the drop we are adding and then has its children
            if (dropList1.contains(dropOperands[1])) {
                toAdd = listFromIndex(dropList1, dropList1.indexOf(dropOperands[1]));
                dropList1 = removeFromIndex(dropList1, toAdd);
            }
            else if (dropList2.contains(dropOperands[1])) {
                toAdd = listFromIndex(dropList2, dropList2.indexOf(dropOperands[1]));
                dropList2 = removeFromIndex(dropList2, toAdd);
            }
            else {
                return "INVALID";
            }

            if (dropList1.contains(dropOperands[0])) {
                dropList1.addAll(dropList1.indexOf(dropOperands[0]) +1, toAdd);
            }
            else if (dropList2.contains(dropOperands[0])) {
                dropList2.addAll(dropList2.indexOf(dropOperands[0]) +1, toAdd);
            }
            else {
                return "INVALID";
            }

        }

        //checks both drop lists for loops
        if (!loopCheck(dropList1) && !loopCheck(dropList2)) {

            //when droplist 1 and 2 are not empty
            if (!(dropList1.isEmpty()) && !(dropList2.isEmpty())) {
                //droplist2 comes first alphabetically
                if (dropList1.getFirst().compareTo(dropList2.getFirst()) > 0) {
                    answer = createOutput(dropList2, dropList1);
                }
                //droplist1 comes first
                else {
                    answer = createOutput(dropList1, dropList2);
                }

            }
            //droplist1 is empty so output droplist2
            else if (dropList1.isEmpty()) {
                answer = createDropString(dropList2);
            }
            //droplist2 is empty outptu list1
            else if (dropList2.isEmpty()) {
                answer = createDropString(dropList1);
            }
        }

        return answer;
    }

    public static LinkedList<String> listFromIndex(LinkedList<String> list, int index) {
        LinkedList<String> newList = new LinkedList<String>();

        for (int i = index; i < list.size(); i++) {
            newList.add(list.get(i));
        }

        return newList;
    }

    public static boolean loopCheck(LinkedList<String> list) {
        boolean looped = false;
        HashMap<String, Integer> nodeCount = new HashMap<String, Integer>();

        for (String s : list) {
            if (!(nodeCount.put(s, 1) == null)) {
                looped = true;
            }
        }

        return looped;
    }

    public static String createOutput(LinkedList<String> list1, LinkedList<String> list2) {
        String output = "";

        output = output + createDropString(list1) + "," + createDropString(list2);

        return output;
    }

    public static String createDropString(LinkedList<String> list) {
        String dropString = "";
        String arrow = "->";

        for (int i = 0; i < list.size(); i++) {
            //if on last elem of list output comma instead of arrow with the drop
            if (i == list.size() - 1) {
                dropString = dropString + list.get(i);
            }
            else {
                dropString = dropString + list.get(i) + arrow;
            }
        }

        return dropString;
    }

    public static LinkedList<String> removeFromIndex(LinkedList<String> list, LinkedList<String> removeList) {
        for (String s : removeList) {
            list.remove(s);
        }

        return list;
    }
}
