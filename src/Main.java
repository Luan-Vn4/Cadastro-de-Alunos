import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean operando = true;

        while (operando) {
            Visuals.printHeader("SISTEMA DE CADASTRO DE ALUNOS");

            System.out.println("[1] Adicionar aluno\n" +
                    "[2] Remover aluno\n" +
                    "[3] Buscar aluno\n" +
                    "[4] Atualizar aluno\n" +
                    "[5] Listar todos os alunos cadastrados");

            Visuals.printLine(25);

            System.out.println("Requisição: ");

            int query;
            while (true) {
                try {
                    query = Integer.parseInt(input.next());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Argumento inválido!!");
                }
            }

            switch (query) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }

        }
    }
}