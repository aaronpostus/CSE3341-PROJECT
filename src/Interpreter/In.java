package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class In implements INode {
    Tokenizer tok;
    private IdList idList;
    public In(Tokenizer tok) {
        this.tok = tok;
    }
    public void print() {
        PrettyPrinter printer = PrettyPrinter.instance();
        printer.print("read");
        idList.print();
        printer.print(";");
    }

    public void parse() {
        if(tok.getToken() != TokenUtil.READ_NUM) {
            TokenUtil.unexpectedError(tok,"read");
        }
        tok.skipToken();
        idList = new IdList(tok, false);
        idList.parse();
        if(tok.getToken() != TokenUtil.SEMICOLON_NUM) {
            TokenUtil.unexpectedError(tok,"semicolon");
        }
        tok.skipToken();
    }

    public void execute() {
        idList.read();
    }
}
