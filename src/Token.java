import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Token {

    // Number 1-34
    private int tokenNumber;

    // This is either the value for an integer or the name for an identifier
    private char[] value;

    // Should be used for reserved words, special symbols, end of file, and error tokens.
    public Token(int tokenNumber) {
        this.tokenNumber = tokenNumber;
    }
    // Should be used for creating integers or identifiers
    public Token(int tokenNumber, String value) {
        this.tokenNumber = tokenNumber;
        this.value  = value.toCharArray();
    }
    public int getTokenNumber() {
        return tokenNumber;
    }
    // precondition: this is integer token and has a valid, parsable integer stored in the char array "value".
    public int getValueAsInt() {
        return Integer.parseInt(value.toString());
    }
    // precondition: this is an identifier token and has a valid, parsable string stored in the char array "value".
    public String getValueAsString() {
        return value.toString();
    }
}
