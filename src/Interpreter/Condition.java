package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class Condition implements INode {
    public int altNo;
    Tokenizer tok;
    private Comparison comp;
    private Condition cond1;
    private Condition cond2;
    public Condition(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        switch (altNo) {
            case 0 -> comp.print();
            case 1 -> { printer.print("!"); cond1.print(); }
            case 2 -> { printer.print("["); cond1.print();
                printer.print("&&"); cond2.print(); printer.print("]");
            }
            case 3 -> { printer.print("["); cond1.print();
                printer.print("||"); cond2.print(); printer.print("]");}
        }
    }

    public void parse() {
        switch (tok.getToken()) {
            // !<cond>
            case TokenUtil.EXCLAMATION_NUM:
                tok.skipToken();
                // skip to condition
                cond1 = new Condition(tok);
                cond1.parse();
                altNo = 1;
                break;
            // [<cond> && <cond>] and [<cond> || <cond>]
            case TokenUtil.OPEN_BRACKET_NUM:
                // skip to condition
                tok.skipToken();
                cond1 = new Condition(tok);
                cond1.parse();
                switch (tok.getToken()) {
                    case TokenUtil.AND_NUM -> altNo = 2;
                    case TokenUtil.OR_NUM -> altNo = 3;
                    default -> TokenUtil.unexpectedError(tok, "'and' or 'or'");
                }
                tok.skipToken();
                cond2 = new Condition(tok);
                cond2.parse();
                break;
            // <comp> : note, if this is an invalid comp then comp will deal with that
            default:
                comp = new Comparison(tok);
                comp.parse();
                altNo = 0;
        }
    }

    public void execute() {
        // empty
    }
    public boolean evaluate() {
        switch (altNo) {
            case 0:
                return comp.evaluate();
            case 1:
                return !cond1.evaluate();
            case 2:
                return cond1.evaluate() && cond2.evaluate();
            case 3:
                return cond1.evaluate() || cond2.evaluate();
        }
        return false;
    }
}
