import java.util.*;
import java.io.*;

/**
 * @author Mike Zastre, UVic
 *
 * Part of CSC 115, Spring 2015, Assignment #5
 */

public class WordReference {
    public static BSTRefBased rawRefs(String filename) {
        BSTRefBased result = new BSTRefBased();
        try {
            Scanner scanner = new Scanner(new File(filename));
            int lineNum = 1;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                line = line.trim();
                line = line.replaceAll("\\p{Punct}+", "");
                if (line.equals("")) {
                    continue;
                }
                String words[] = line.split("\\s+");
                for (String w : words) {
                    String ww = w.toLowerCase();
                    WordRefs wf = result.retrieve(ww);
                    if (wf == null) {
                        result.insert(ww);
                        wf = result.retrieve(ww);
                    }
                    wf.addLine(lineNum);
                }
                lineNum++;
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            System.exit(1);
        } catch (NullPointerException npe) {
            System.err.println("One of the tree routines created a " +
                "null pointer exception");
            System.exit(2);
        }

        return result;
    }


    public static void filterRefs(BSTRefBased tree, String words[]) {
        for (String w : words) {
            tree.delete(w);
        }
    }


    public static void reportRefs(BSTRefBased tree) {
        for (WordRefs wf : tree) {
            System.out.println(wf.toString());
        }
    }


    public static void main(String args[]) {
        BSTRefBased t;
        String exclude[] = {"a", "the", "i", "it", "is"};

        if (args.length < 1) {
            System.err.println("Usage: provide an input filename");
            System.exit(1);
        }

        t = rawRefs(args[0]);
        filterRefs(t, exclude);
        reportRefs(t);
    }
}
