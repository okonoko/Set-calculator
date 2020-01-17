import java.util.Scanner;

public class Main {
	
    private void start() {
    	Parser chuj = new Parser();
    	Scanner in = new Scanner(System.in);
		chuj.program(in);
		in.close();
    }

    public static void main(String[] argv) {
        new Main().start();
    }
}
