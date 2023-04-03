package Interpreter;

public class Id implements Node{
    private String name;
    private int val;
    private boolean declared, initialized;
    private static Id eIds[]; // pointers to existing Interpreter.Id’s; *static* var
    private static int idCount = 0; // also static

    Id(String n) { //		name = n; declared = false; initialized = false; }
    }

    //static Interpreter.Id*

    public void parse() {  // get token; check if name matches 		// eIds[k] ->name for any k = 0, ..., idCount-1; 		// if yes, return eIds[k]; if not:
        //Interpreter.Id * nId = new Interpreter.Id(n1); ...add to eIds[]...return nId;
    }
    public void print() {

    }
    public void execute() {
        
    }

    //int getIdVal() {
    //  return val; // check initialized first?
    //  setIdVal() ? getIdName() ? ...
    //}
}