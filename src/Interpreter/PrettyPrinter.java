package Interpreter;

public final class PrettyPrinter {
    private static PrettyPrinter INSTANCE;
    private int numberOfTabs = 0;
    private String indent = "    ";
    private PrettyPrinter() {
    }
    public static PrettyPrinter instance() {
        if(INSTANCE == null) {
            INSTANCE = new PrettyPrinter();
        }
        return INSTANCE;
    }
    // starts a new line and prints the input
    public void println(String str) {
        String space = " ";
        if(str.equals("")) {
            space = "";
        }
        System.out.print("\n" + getIndent() + str + space);
    }
    // continues printing on the same line
    public void print(String str) {
        System.out.print(str + " ");
    }
    public void indent() {
        numberOfTabs++;
    }
    public void indentBack() {
        if(numberOfTabs > 0) {
            numberOfTabs--;
        }
    }
    public int getIndentNumber() {
        return numberOfTabs;
    }
    public void setIndentNumber(int numberOfTabs) {
        this.numberOfTabs = numberOfTabs;
    }
    private String getIndent() {
        String indent = "";
        if(numberOfTabs == 0) {
            return indent;
        }
        for(int i = 0; i < numberOfTabs; i++) {
            // string concatenation is not a great practice in a loop but it's fine for the purpose of this project.
            indent = indent + this.indent;
        }
        return indent;
    }

}
