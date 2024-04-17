package solution;

import java.util.ArrayList;

public class BinTree {
    private TNode root;
    Status lstat;
    Status rstat;
    Status GOOD;
    Status LEFT_EXISTS;
    Status RIGHT_EXISTS;

    // binary string check
    public static boolean isBinaryString(String s) {
        // Regular expression for a binary string (contains only '0' and '1')
        String binaryPattern = "[01]+";

        // Check if the string matches the binary pattern
        return s.matches(binaryPattern);
    }

    void addNode(TNode t, String codeString, int pos, int c_index) {
        // Time Complexity: O(n)
        // AddNode runs for the length of
        // binary string n

        // when you reach the end of the binary string, place the data into node
        if (pos == codeString.length()) {
            t.data = "c" + c_index;
            return;
        }
        // if bit is 0, create/or return the recursive call on the left node
        if (codeString.charAt(pos) == '0') {
            if (t.left == null) {
                t.left = new TNode(null, null, null);
            }
            addNode(t.left, codeString, pos + 1, c_index);
        }

        // if bit is 1, create/or return the recursive call on the right node
        if (codeString.charAt(pos) == '1') {
            if (t.right == null) {
                t.right = new TNode(null, null, null);
            }
            addNode(t.right, codeString, pos + 1, c_index);
        }
    }

    // constructor
    public BinTree(String[] a) {
        // Time Complexity: O(m*n)
        // Constructor calls the recursive function addNode which runs for the length of
        // binary string n
        // Constructor runs for the length of array m, therefore, O(m*n)

        // Space Complexity: O(n)
        // Conctructor doesnt extra memory, runs for the length of the codestring

        // go through each binary string, if each element is 0 then left, 1 then right,
        // create a node if not existent
        // then put c+index as data into that node
        root = new TNode(null, null, null);
        TNode t = root;
        int size = a.length;

        for (int i = 0; i < size; i++) {

            if (!isBinaryString(a[i])) {
                throw new IllegalArgumentException("Invalid Argument!");
            }

            for (int j = 0; j < i; j++) {
                if (a[i].startsWith(a[j])) {
                    throw new IllegalArgumentException("Prefix condition violated!");
                }
            }

            addNode(t, a[i], 0, i);

        }

    }

    // inorder traversal print tree
    public void printTree() {
        printTree(root);
    }

    private void printTree(TNode t) {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(h)
        // Maximum number of calls on the call stack is equal to the height of the tree
        if (t != null) {

            printTree(t.left);

            if (t.data == null) {
                System.out.print("I ");
            } else
                System.out.print(t.data + " ");
            printTree(t.right);
        }
    }

    public void optimize() {
        // Time Complexity: O(n)
        // Runs for the number of nodes due to recursive function

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        TNode t = root;
        Status task = optimizeRecur(t);

        // start t is root, call traversal on left and then right
        // base case if t==null return 0
        // lstat = status of the subtree from recursive call left
        // rstat = status of the subtree from recursive call right

        // return GOOD if subtree is optimized (0)
        if (task == Status.GOOD) {
            return;
        }

        // if lstat is LEFT_EXISTS, then the current node's left child modifies to the
        // left child's left child
        if (lstat == Status.LEFT_EXISTS) {
            t.left = t.left.left;
        }

        // if lstat is RIGHT_EXISTS, then the current node's left child modifies to the
        // left child's right child
        if (lstat == Status.RIGHT_EXISTS) {
            t.left = t.left.right;
        }
        // similar logic with rstat
        if (rstat == Status.LEFT_EXISTS) {
            t.left = t.left.left;
        }
        if (rstat == Status.LEFT_EXISTS) {
            t.left = t.left.right;
        }

    }

    private Status optimizeRecur(TNode t) {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        // start t is root, call traversal on left and then right
        // base case if t==null return 0

        if (t == null) {
            return Status.GOOD;
        }
        // lstat = status of the subtree from recursive call left
        // rstat = status of the subtree from recursive call right
        Status lstat = optimizeRecur(t.left);
        Status rstat = optimizeRecur(t.right);

        // return LEFT_EXISTS if only left exists (1)
        if (t.left != null && t.right == null) {
            return Status.LEFT_EXISTS;
        }
        // return RIGHT_EXISTS if only right exists (2)
        if (t.right != null && t.left == null) {
            return Status.RIGHT_EXISTS;
        }

        // return GOOD if (left == null && right == null) || (left != null && right !=
        // null)
        if ((t.left == null && t.right == null) || (t.left != null && t.right != null)) {
            return Status.GOOD;
        }

        // return LEFT_EXISTS if left != null else return
        return lstat == Status.GOOD ? rstat : lstat;
        // SPECIAL CASE ROOT NODE STATUS

    }

    public ArrayList<String> getCodewords() {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        // in order traversal to store the path to get each node an array of strings
        TNode t = root;
        ArrayList<String> codeWords = new ArrayList<String>();
        getCodewords(t, "", codeWords);

        return codeWords;
    }

    private void getCodewords(TNode t, String x, ArrayList<String> y) {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        if (t == null) {
            return;
        }

        if (t.left != null) {
            getCodewords(t.left, x.concat("0"), y);
        }

        if (t.right != null) {
            getCodewords(t.right, x.concat("1"), y);
        }
        if (t.left == null && t.right == null) {
            y.add(x);
            return;
        }
    }

    public String[] toArray() {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        TNode y = root;
        int str_size = (int) Math.pow(2, find_depth(y));
        String[] s = new String[str_size];
        // if left exists, populate index 2i, if right exists , populate 2i +1
        // starting index 1
        toArrayRec(y, 1, s);
        return s;
    }

    private void toArrayRec(TNode t, int pos, String[] r) {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        r[pos] = t.data;
        if (t.data == null || t.data.isEmpty()) {
            r[pos] = "I";
        }
        if (t.left != null) {
            toArrayRec(t.left, 2 * pos, r);
        }

        if (t.right != null) {
            toArrayRec(t.right, 2 * pos + 1, r);
        }

    }

    private int find_depth(TNode t) {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        if (t == null) {
            return 0;
        }
        int ldepth = find_depth(t.left);
        int rdepth = find_depth(t.right);

        if (ldepth > rdepth) {
            return (ldepth + 1);
        } else {
            return (rdepth + 1);
        }
    }

    public String encode(ArrayList<String> a) {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        // use getCodewords, to get the string array of the binary codes
        // each element in words is a binary string
        // each index in the string array words is c0, c1, c2, etc
        // match the given string with the output of getcodewords to generate a new
        // string array
        ArrayList<String> words = new ArrayList<String>();
        words = getCodewords();
        String encoded = "";
        char w;
        for (int i = 0; i < a.size(); i++) {
            w = a.get(i).charAt(1);
            int val = charToNum(w);
            encoded = encoded.concat(words.get(val));
        }
        return encoded;
    }

    private int charToNum(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            default:
                throw new IllegalArgumentException("Invalid binary digit: " + c);
        }

    }

    public ArrayList<String> decode(String s) {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        // We have a bitstring
        // We can start at the root and go left for each 1, right for each 0
        // with each step, check if there is a leaf node, if there is, return the
        // data in the node, set it t back to root
        TNode t = root;
        ArrayList<String> str = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char bit = s.charAt(i);
            if (bit == '0') {
                t = t.left;
            } else if (bit == '1') {
                t = t.right;
            } else {
                throw new IllegalArgumentException("Input string contains non-binary characters.");
            }

            if (t.data != null && t != null) {
                str.add(t.data);
                t = root;
            }
            i++;
        }
        if (t != root) {
            throw new IllegalArgumentException("Input string cannot be parsed in a sequence of codewords.");
        }
        return str;
    }

    public String toString() {
        // Time Complexity: O(n)
        // Runs for the number of nodes

        // Space Complexity: O(n)
        // Occupies same amount of memory as number of nodes in the tree

        ArrayList<String> str = getCodewords();
        String output_string = "";
        for (int i = 0; i < str.size(); i++) {
            output_string = output_string.concat(str.get(i)).concat(" ");
        }
        return output_string.trim(); // removes space at end
    }

}
