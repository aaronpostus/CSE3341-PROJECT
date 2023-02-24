import java.util.*;

public class TokenUtil {
    static List<Character> DIGITS = Arrays.asList('0','1','2','3','4','5','6','7','8','9');

    // RESERVED WORDS
    static int PROGRAM_NUM = 1;
    static int BEGIN_NUM = 2;
    static int END_NUM = 3;
    static int INT_NUM = 4;
    static int IF_NUM = 5;
    static int THEN_NUM = 6;
    static int ELSE_NUM = 7;
    static int WHILE_NUM = 8;
    static int LOOP_NUM = 9;
    static int READ_NUM = 10;
    static int WRITE_NUM = 11;

    static Map<String, Integer> RESERVED_WORDS = new HashMap<>();

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

    static int SEMICOLON_NUM = 12;
    static int COMMA_NUM = 13;
    static int EQUALS_NUM = 14;
    static int EXCLAMATION_NUM = 15;
    static int OPEN_BRACKET_NUM = 16;
    static int CLOSED_BRACKET_NUM = 17;
    static int AND_NUM = 18;
    static int OR_NUM = 19;
    static int OPEN_PARENTHESIS_NUM = 20;
    static int CLOSED_PARENTHESIS_NUM = 21;
    static int PLUS_NUM = 22;
    static int MINUS_NUM = 23;
    static int TIMES_NUM = 24;
    static int NOT_EQUALS_NUM = 25;
    static int DOUBLE_EQUALS_NUM = 26;
    // <
    static int LESS_THAN_NUM = 27;
    // >
    static int GREATER_THAN_NUM = 28;
    // <=
    static int LESS_THAN_OR_EQUAL_NUM = 29;
    // >=
    static int GREATER_THAN_OR_EQUAL_NUM = 30;

    // MISCELLANEOUS
    static int INTEGER_NUM = 31;
    static int IDENTIFIER_NUM = 32;
    static int END_OF_FILE_NUM = 33;
    static int ERROR_TOKEN_NUM = 34;

    // Single character, and no need to worry about greedy tokenizing.
    static Map<String, Integer> SIMPLE_SPECIAL_SYMBOLS = new HashMap<>();
    // One or two characters. Need to consider greedy tokenizing.
    static Map<String, Integer> COMPLEX_SPECIAL_SYMBOLS = new HashMap<>();

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



}
