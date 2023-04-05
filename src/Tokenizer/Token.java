package Tokenizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Token {

    // Number 1-34
    private int tokenNumber;

    // This is either the value for an integer or the name for an identifier
    private char[] value = null;

    // Should be used for reserved words, special symbols, end of file, and error tokens.
    public Token(int tokenNumber) {
        this.tokenNumber = tokenNumber;
    }
    // Should be used for creating integers or identifiers
    public Token(int tokenNumber, char[] value) {
        this.tokenNumber = tokenNumber;
        this.value  = value;
    }
    public boolean hasValue() { return value != null; }
    public void setValue(char[] value) { this.value = value; }
    public int getTokenNumber() {
        return tokenNumber;
    }
    // precondition: this is integer token and has a valid, parsable integer stored in the char array "value".
    public int getValueAsInt() {
        return Integer.parseInt(String.valueOf(value));
    }
    // precondition: this is an identifier token and has a valid, parsable string stored in the char array "value".
    public String getValueAsString() {
        return String.valueOf(value);
    }
}
