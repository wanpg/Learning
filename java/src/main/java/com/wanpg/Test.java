package com.wanpg;
/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

public class Test {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) {
        ListNode l1 = stringToListNode("[2,4,3]");
        ListNode l2 = stringToListNode("[5,6,4]");

        ListNode ret = addTwoNumbers(l1, l2);

        String out = listNodeToString(ret);

        System.out.print(out);
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode lastNode = null;
        ListNode cur1 = l1, cur2 = l2;
        int lastLeftVal = 0;
        while (cur1 != null || cur2 != null) {
            int value = 0;
            if (cur1 != null) {
                value += cur1.val;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                value += cur2.val;
                cur2 = cur2.next;
            }
            value += lastLeftVal;
            lastLeftVal = value / 10;
            value = value % 10;
            ListNode valueNode = new ListNode(value);
            if (lastNode == null) {
                lastNode = result = valueNode;
            } else {
                lastNode.next = valueNode;
                lastNode = valueNode;
            }
        }
        return result;
    }
}