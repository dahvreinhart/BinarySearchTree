import java.util.LinkedList;

/**
 * @author Mike Zastre, UVic
 *
 * Part of CSC 115, Spring 2015, Assignment #5
 */

public class WordRefs {
    private String word;
    private LinkedList<Integer> lineNumbers;


    public WordRefs(String word) {
        this.word = word;
        lineNumbers = new LinkedList<>();
    }


    public void addLine(int lineNumber) {
        lineNumbers.add(lineNumber);
    }


    public String getWord() {
        return word;
    }


    public LinkedList<Integer> getLineNumbers() {
        return lineNumbers;
    }

   
    public String toString() {
        String result = word + ":";
        
        for (Integer ii : lineNumbers) { //nothing in this loop for some reason....
            result += " " + ii;
        }

        return result;
    }
}
