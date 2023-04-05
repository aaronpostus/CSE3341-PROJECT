package Interpreter;

import Tokenizer.TokenUtil;
import Tokenizer.Tokenizer;

public class IdList implements INode {
    private final Tokenizer tok;
    private Id id;
    private IdList idList;
    // altNo 1: <id>
    // altNo 2: <id>, <idList>
    public int altNo;
    private boolean parseOne;
    public IdList(Tokenizer tok, boolean parseOne) {
        this.tok = tok;
        this.parseOne = parseOne;
    }
    public void print() {
        id.print();
        if(altNo == 1) {
            PrettyPrinter printer = PrettyPrinter.instance();
            printer.print(",");
            idList.print();
        }
    }

    public void parse() {
        // first token is an identifier
        id = new Id(tok.idName(), tok);

        if(parseOne) {
            id.parse();
        }
        else {
            id.parseTwo();
        }

        if (tok.getToken() == TokenUtil.COMMA_NUM) {
            altNo = 1;
            tok.skipToken();
            // parse id list
            idList = new IdList(tok, parseOne);
            idList.parse();
        }
        else {
            altNo = 0;
        }

    }
    public void execute() {
        //empty
    }
    public void read() {
        // get next int deals with error corresponding to reaching end of file
        tok.ids.put(id.name, Reader.instance().getNextInt());
        if(altNo == 1) {
            idList.read();
        }
    }
    public void write() {
        System.out.println(id.name + " = " + tok.ids.get(id.name));
        if(altNo == 1) {
            idList.write();
        }
    }
}
