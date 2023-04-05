package Tokenizer;

import java.util.*;

public class TokenUtil {
    public static final List<Character> DIGITS = Arrays.asList('0','1','2','3','4','5','6','7','8','9');

    // RESERVED WORDS
    public static final int PROGRAM_NUM = 1;
    public static final int BEGIN_NUM = 2;
    public static final int END_NUM = 3;
    public static final int INT_NUM = 4;
    public static final int IF_NUM = 5;
    public static final int THEN_NUM = 6;
    public static final int ELSE_NUM = 7;
    public static final int WHILE_NUM = 8;
    public static final int LOOP_NUM = 9;
    public static final int READ_NUM = 10;
    public static final int WRITE_NUM = 11;

    public static final Map<String, Integer> RESERVED_WORDS = new HashMap<>();

    static {
        RESERVED_WORDS.put("program", TokenUtil.PROGRAM_NUM);
        RESERVED_WORDS.put("begin", TokenUtil.BEGIN_NUM);
        RESERVED_WORDS.put("end", TokenUtil.END_NUM);
        RESERVED_WORDS.put("int", TokenUtil.INT_NUM);
        RESERVED_WORDS.put("if", TokenUtil.IF_NUM);
        RESERVED_WORDS.put("then", TokenUtil.THEN_NUM);
        RESERVED_WORDS.put("else", TokenUtil.ELSE_NUM);
        RESERVED_WORDS.put("while", TokenUtil.WHILE_NUM);
        RESERVED_WORDS.put("loop", TokenUtil.LOOP_NUM);
        RESERVED_WORDS.put("read", TokenUtil.READ_NUM);
        RESERVED_WORDS.put("write", TokenUtil.WRITE_NUM);
    }

    // SPECIAL SYMBOLS

    public static final int SEMICOLON_NUM = 12;
    public static final int COMMA_NUM = 13;
    public static final int EQUALS_NUM = 14;
    public static final int EXCLAMATION_NUM = 15;
    public static final int OPEN_BRACKET_NUM = 16;
    public static final int CLOSED_BRACKET_NUM = 17;
    public static final int AND_NUM = 18;
    public static final int OR_NUM = 19;
    public static final int OPEN_PARENTHESIS_NUM = 20;
    public static final int CLOSED_PARENTHESIS_NUM = 21;
    public static final int PLUS_NUM = 22;
    public static final int MINUS_NUM = 23;
    public static final int TIMES_NUM = 24;
    public static final int NOT_EQUALS_NUM = 25;
    public static final int DOUBLE_EQUALS_NUM = 26;
    // <
    public static final int LESS_THAN_NUM = 27;
    // >
    public static final int GREATER_THAN_NUM = 28;
    // <=
    public static final int LESS_THAN_OR_EQUAL_NUM = 29;
    // >=
    public static final int GREATER_THAN_OR_EQUAL_NUM = 30;

    // MISCELLANEOUS
    public static final int INTEGER_NUM = 31;
    public static final int IDENTIFIER_NUM = 32;
    public static final int END_OF_FILE_NUM = 33;
    public static final int ERROR_TOKEN_NUM = 34;

    // Single character, and no need to worry about greedy tokenizing.
    public static final Map<String, Integer> SIMPLE_SPECIAL_SYMBOLS = new HashMap<>();
    // One or two characters. Need to consider greedy tokenizing.
    public static final Map<String, Integer> COMPLEX_SPECIAL_SYMBOLS = new HashMap<>();

    static {
        SIMPLE_SPECIAL_SYMBOLS.put(";", TokenUtil.SEMICOLON_NUM);
        SIMPLE_SPECIAL_SYMBOLS.put(",", TokenUtil.COMMA_NUM);


        SIMPLE_SPECIAL_SYMBOLS.put("[", TokenUtil.OPEN_BRACKET_NUM);
        SIMPLE_SPECIAL_SYMBOLS.put("]", TokenUtil.CLOSED_BRACKET_NUM);

        SIMPLE_SPECIAL_SYMBOLS.put("(", TokenUtil.OPEN_PARENTHESIS_NUM);
        SIMPLE_SPECIAL_SYMBOLS.put(")", TokenUtil.CLOSED_PARENTHESIS_NUM);
        SIMPLE_SPECIAL_SYMBOLS.put("+", TokenUtil.PLUS_NUM);
        SIMPLE_SPECIAL_SYMBOLS.put("-", TokenUtil.MINUS_NUM);
        SIMPLE_SPECIAL_SYMBOLS.put("*", TokenUtil.TIMES_NUM);


    }

    static {
        COMPLEX_SPECIAL_SYMBOLS.put("=", TokenUtil.EQUALS_NUM);
        COMPLEX_SPECIAL_SYMBOLS.put("!", TokenUtil.EXCLAMATION_NUM);
        COMPLEX_SPECIAL_SYMBOLS.put("&&", TokenUtil.AND_NUM);
        COMPLEX_SPECIAL_SYMBOLS.put("||", TokenUtil.OR_NUM);
        COMPLEX_SPECIAL_SYMBOLS.put("!=", TokenUtil.NOT_EQUALS_NUM);
        COMPLEX_SPECIAL_SYMBOLS.put("==", TokenUtil.DOUBLE_EQUALS_NUM);
        COMPLEX_SPECIAL_SYMBOLS.put("<", TokenUtil.LESS_THAN_NUM);
        COMPLEX_SPECIAL_SYMBOLS.put(">", TokenUtil.GREATER_THAN_NUM);
        COMPLEX_SPECIAL_SYMBOLS.put("<=", TokenUtil.LESS_THAN_OR_EQUAL_NUM);
        COMPLEX_SPECIAL_SYMBOLS.put(">=", TokenUtil.GREATER_THAN_OR_EQUAL_NUM);
    }

    public static final void unexpectedError(Tokenizer tok, String expectedTokenName) {
        System.out.println("Error: Expected a(n) " + expectedTokenName + ", but" +
                " encountered a token with the id " + tok.getToken());
        System.exit(1);
    }
}
