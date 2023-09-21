public class Visuals {

    public static void printHeader(String args){
        System.out.println("-=-".repeat(args.length()));
        System.out.printf(" ".repeat(args.length()) + "%s" + " ".repeat(args.length()) + "\n", args);
        System.out.println("-=-".repeat(args.length()));
    }

    public static void printLine(int tamanho){
        System.out.printf("~~~".repeat(tamanho) + "\n");
    }

}
