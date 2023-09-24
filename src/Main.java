import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean operando = true;
        CadastroAlunos alunosCadastrados = new CadastroAlunos();

        while (operando) {
            Visuals.printHeader("SISTEMA DE CADASTRO DE ALUNOS");

            System.out.println("""
                    [1] Adicionar aluno
                    [2] Remover aluno
                    [3] Buscar aluno
                    [4] Atualizar aluno
                    [5] Listar todos os alunos cadastrados
                    [6] Sair""");

            Visuals.printLine(25);


            System.out.println("Requisição: ");
            int query;
            while (true) {
                try {
                    query = Integer.parseInt(input.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Argumento inválido!!");
                }
            }


            // Operações do banco de cadastro de alunos
            switch (query) {
                case 1 -> alunosCadastrados.cadastrarAluno();
                case 2 -> alunosCadastrados.removerAluno();
                case 3 -> alunosCadastrados.buscarAluno();
                case 4 -> alunosCadastrados.atualizarAluno();
                case 5 -> alunosCadastrados.listarAlunos();
                case 6 -> operando = false;
            }
        }


    }
}