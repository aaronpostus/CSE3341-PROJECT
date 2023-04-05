package Tokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tokenizer {

    public ArrayList<Token> tokens = new ArrayList<>();
    // Necessary to keep track of duplicates
    public Map<String, Integer> ids = new LinkedHashMap<>();
    private char[] line;
    private int tokenIndex = 0, currentCharIndex = 0;
    private BufferedReader scanner;
    public Tokenizer(String programFile) {
        try {
            scanner = new BufferedReader(new FileReader(programFile));
            tokenizeLine();
        }
        catch (FileNotFoundException e) {
            // // File doesn't exist
            throw new RuntimeException(e);
        }
    }
    // Tokenizes a line. It makes a recursive call if the line was only whitespace.
    private void tokenizeLine() {
        String currentLine;
        try {
            currentLine = scanner.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Arrived at end of file.
        if(currentLine == null) {
            tokens.add(new Token(TokenUtil.END_OF_FILE_NUM));
        }
        else {
            line = currentLine.toCharArray();
            int tokensBefore = tokens.size();

            currentCharIndex = 0;
            while(currentCharIndex < line.length) {
                char currentChar = line[currentCharIndex];
                if(currentChar == ' ')  {
                    currentCharIndex++;
                } else {
                    Token token = null;
                    switch (Character.getType(currentChar)) {
                        case Character.DECIMAL_DIGIT_NUMBER:
                            // Attempts to tokenize as integer
                            token = tokenizeInt();
                            //System.out.println("Resulting int: " + token.getTokenNumber());
                            break;
                        case Character.LOWERCASE_LETTER:
                            // Attempts to tokenize as program
                            token = tokenizeReservedWord();
                            //System.out.println("Resulting reserved word: " + token.getTokenNumber());
                            break;
                        case Character.UPPERCASE_LETTER:
                            // Attempts to tokenize as identifier
                            token = tokenizeIdentifier();
                            //System.out.println("Resulting identifier: " + token.getTokenNumber());
                            break;
                        default:
                            // Attempts to tokenize as a special character
                            token = tokenizeSpecialCharacter();
                            //System.out.println("Resulting character: " + token.getTokenNumber());
                            break;
                    }
                    if(token != null) {
                        if(token.getTokenNumber() == TokenUtil.ERROR_TOKEN_NUM) {
                            throwInvalidTokenError();
                        }
                        tokens.add(token);
                    }
                }
            }
            // handles special case where we have one or many empty lines
            if(tokensBefore == tokens.size()) {
                tokenizeLine();
            }
        }

    }
    // Look ahead to see if there is whitespace or a special symbol.
    private boolean validLookAhead() {
        // At end of line, so it's fine.
        if(currentCharIndex >= line.length) {
            return true;
        }
        if(line[currentCharIndex] == ' ') {
            // Next symbol is whitespace
            return true;
        }
        // Not whitespace. Check if it's a special symbol.
        // Also save current index because tokenizing will advance the pointer.

        int previousIndex = currentCharIndex;
        Token token = tokenizeSpecialCharacter();
        currentCharIndex = previousIndex;

        // Returns whether there's a valid special symbol ahead of this.
        return  token.getTokenNumber() > 11 && token.getTokenNumber() < 31;
    }


    // Returns number of digits in int so tokenizeLine can advance
    private Token tokenizeInt() {
        int intLength = 0;
        // keep going til we hit a character that isn't a digit or we've reached end of line
        int beforeIndex = currentCharIndex;
        while(currentCharIndex < line.length) {
            if(Character.getType(line[currentCharIndex]) != Character.DECIMAL_DIGIT_NUMBER) {
                if(validLookAhead()) {
                    // We are not doing value for this project, but I can easily add functionality here later.
                    return new Token(TokenUtil.INTEGER_NUM, Arrays.copyOfRange(line, beforeIndex, currentCharIndex));
                } else {
                    return new Token(TokenUtil.ERROR_TOKEN_NUM);
                }
            }
            currentCharIndex++;
        }
        // Reached end of the line, so this has to be a valid integer.
        return new Token(TokenUtil.INTEGER_NUM, Arrays.copyOfRange(line, beforeIndex, line.length-1));

    }
    private Token tokenizeReservedWord() {
        for(String reservedWord : TokenUtil.RESERVED_WORDS.keySet()) {
            if(tokenPresent(reservedWord)) {
                currentCharIndex+=reservedWord.length();
                int previousCharIndex=currentCharIndex;
                if(validLookAhead()) {
                    Token token = new Token(TokenUtil.RESERVED_WORDS.get(reservedWord));
                    currentCharIndex = previousCharIndex;
                    return token;
                }
                currentCharIndex -= reservedWord.length();
            }
        }
        // error
        return new Token(TokenUtil.ERROR_TOKEN_NUM);
    }
    // Returns length of identifier so tokenizeLine can advance
    private Token tokenizeIdentifier() {

        // Check first character (Can only be a letter).
        int charType = Character.getType(line[currentCharIndex++]);
        if(charType != Character.UPPERCASE_LETTER) {
            return new Token(TokenUtil.ERROR_TOKEN_NUM);
        }

        int beforeIndex = currentCharIndex - 1;
        // keep going til we hit a character that isn't an uppercase letter or a digit or we've reached end of line
        while(currentCharIndex < line.length) {
            charType = Character.getType(line[currentCharIndex]);
            if(!(charType == Character.UPPERCASE_LETTER || charType == Character.DECIMAL_DIGIT_NUMBER)) {
                if(validLookAhead()) {
                    // We are not doing value for this project, but I can easily add functionality here later.
                    return new Token(TokenUtil.IDENTIFIER_NUM, Arrays.copyOfRange(line, beforeIndex, currentCharIndex));
                } else {
                    return new Token(TokenUtil.ERROR_TOKEN_NUM);
                }
            }
            currentCharIndex++;
        }
        // Reached end of the line, so this has to be a valid identifier.
        return new Token(TokenUtil.IDENTIFIER_NUM, Arrays.copyOfRange(line, beforeIndex, line.length));
    }
    private boolean tokenPresent(String token) {
        int length = 0;
        for(int i = currentCharIndex; i < line.length && length < token.length(); i++) {
            // updates counters
            length++;
            if(token.charAt(length-1) != line[i]) {
                // invalid character found, not this token.
                return false;
            }
        }

        // Need to make a check for if we got through the whole token to handle
        // a special case when the token is at the end of the line.
        // i.e. if "in" was at the end of the line, this could mistakenly be tokenized as "int" without this check.
        return length == token.length();
    }

    private Token tokenizeSpecialCharacter() {

        // Assume it's an error til we find a valid token
        int tokenNum = TokenUtil.ERROR_TOKEN_NUM;

        // Handle simple characters first. These don't need to factor in one token lookahead.
        if(TokenUtil.SIMPLE_SPECIAL_SYMBOLS.containsKey("" + line[currentCharIndex])) {
            Token token = new Token(TokenUtil.SIMPLE_SPECIAL_SYMBOLS.get(("" + line[currentCharIndex++])));
            return token;
        }

        // Not a simple special symbol. Need to use greedy tokenizing to check for complex special symbols.

        // If this is the last character of the line, it has to be only one character long (if it is a valid symbol)
        if(currentCharIndex == line.length - 1) {
            if(TokenUtil.COMPLEX_SPECIAL_SYMBOLS.containsKey(""+line[currentCharIndex])) {
                // This is a valid one character token. Return it.
                return new Token(TokenUtil.COMPLEX_SPECIAL_SYMBOLS.get(""+line[currentCharIndex++]));
            } else {
                // This isn't a valid special symbol. Return an error token.
                return new Token(TokenUtil.ERROR_TOKEN_NUM);
            }
        }

        // We know there is one or more characters after this symbol, and it's not a simple symbol. Let's check
        // if it satisfies special conditions by looking ahead.
        String fullToken;

        switch (line[currentCharIndex]) {
            case '&':
                fullToken = determineToken("&", "&&");
                // if next character is NOT an ampersand, this an illegal token
                if(fullToken.equals("&&")) {
                    currentCharIndex+=2;
                    return new Token(TokenUtil.AND_NUM);
                }
                break;
            case '|':
                fullToken = determineToken("|", "||");
                // if next character is NOT a |, this an illegal token
                if(fullToken.equals("||")) {
                    currentCharIndex+=2;
                    return new Token(TokenUtil.OR_NUM);
                }
                break;
            case '!':
                fullToken = determineToken("!", "!=");
                if(fullToken.equals("!")) {
                    currentCharIndex++;
                    return new Token(TokenUtil.EXCLAMATION_NUM);
                }
                else if(fullToken.equals("!=")){
                    currentCharIndex+=2;
                    return new Token(TokenUtil.NOT_EQUALS_NUM);
                }
                break;
            case '=':
                fullToken = determineToken("=", "==");
                if(fullToken.equals("=")) {
                    currentCharIndex++;
                    return new Token(TokenUtil.EQUALS_NUM);
                }
                else if(fullToken.equals("==")){
                    currentCharIndex+=2;
                    return new Token(TokenUtil.DOUBLE_EQUALS_NUM);
                }
                break;
            case '<':
                fullToken = determineToken("<", "<=");
                if(fullToken.equals("<")) {
                    currentCharIndex++;
                    return new Token(TokenUtil.LESS_THAN_NUM);
                }
                else if(fullToken.equals("<=")){
                    currentCharIndex+=2;
                    return new Token(TokenUtil.LESS_THAN_OR_EQUAL_NUM);
                }

                break;
            case '>':
                fullToken = determineToken(">", ">=");
                if(fullToken.equals(">")) {
                    currentCharIndex++;
                    return new Token(TokenUtil.GREATER_THAN_NUM);
                }
                else if(fullToken.equals(">=")){
                    currentCharIndex+=2;
                    return new Token(TokenUtil.GREATER_THAN_OR_EQUAL_NUM);
                }
                break;
        }
        return new Token(TokenUtil.ERROR_TOKEN_NUM);
    }

    // Greedy method that determines which token is present if two tokens share the same character.
    private String determineToken(String tokenOne, String tokenTwo) {
        if(tokenOne.length() > tokenTwo.length()) {
            if(tokenPresent(tokenOne)) {
                return tokenOne;
            }
            else if(tokenPresent(tokenTwo)) {
                return tokenTwo;
            }
        }
        else {
            if(tokenPresent(tokenTwo)) {
                return tokenTwo;
            }
            else if(tokenPresent(tokenOne)) {
                return tokenOne;
            }
        }
        // neither token. this shouldn't happen.
        return null;
    }
    // Exits the program because we encountered an error token.
    private void throwInvalidTokenError() {
        System.out.println("Error. Invalid token found.");
        System.exit(1);
    }

    // Gets current token without advancing forward.
    public int getToken() {
        if(tokenIndex == -1) {
            if(tokens.size() > 0) {
                tokenIndex = 0;
            }
        }
        if(tokens.get(tokenIndex).getTokenNumber() == TokenUtil.ERROR_TOKEN_NUM) {
            throwInvalidTokenError();
        }
        return tokens.get(tokenIndex).getTokenNumber();
    }

    // Skip to next token if it's not an error token or we are not at the end of the file.
    public void skipToken() {
        System.out.print(getToken() +" ");
        int currentTokenNumber = tokens.get(tokenIndex).getTokenNumber();
        // Only skips if we are NOT at the end of file and the current token is NOT an illegal token.
        if(currentTokenNumber != TokenUtil.END_OF_FILE_NUM && currentTokenNumber != TokenUtil.ERROR_TOKEN_NUM) {
            // Reached last token. Need to tokenize further.
            if(tokenIndex + 1 == tokens.size()) {
                tokenizeLine();
            }
            // Increases pointer by one.
            tokenIndex++;
        }
    }

    // Gets int value: Note, this may not work yet.
    public int intVal() {
        if(getToken() == TokenUtil.INTEGER_NUM) {
            Token token = tokens.get(tokenIndex);
            if(!token.hasValue()) {
                System.out.println("An uninitialized variable was accessed.");
                System.exit(1);
            }
            return token.getValueAsInt();
        } else {
            throw new RuntimeException("Attempted to parse a non-integer token as an integer.");
        }
    }

    // Gets ID: Note, this may not work yet work.
    public String idName() {
        if(getToken() == TokenUtil.IDENTIFIER_NUM) {
            return tokens.get(tokenIndex).getValueAsString();
        } else {
            throw new RuntimeException("Attempted to parse a non-identifier token as an identifier.");
        }
    }
    public void closeInputStream() {
        try {
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
