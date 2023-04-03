public class Tokenizer {

    public static void main(String[] args) {
        // No input file name
        if(args.length == 0) {
            System.out.println("Please input the name of a file to scan.");

        }
        else {
            TokenScanner scanner = new TokenScanner(args[0]);
            int token = 0;
            while(token != TokenUtil.END_OF_FILE_NUM) {
                token = scanner.getToken();
                System.out.print(token + " ");
                scanner.skipToken();
            }
            for(Token potentialID : scanner.tokens) {
                if(potentialID.getTokenNumber() == TokenUtil.INTEGER_NUM) {
                    System.out.print("\n" + "Identifier: " + potentialID.getValueAsString());
                }
            }
            System.exit(1);
        }
    }

}
