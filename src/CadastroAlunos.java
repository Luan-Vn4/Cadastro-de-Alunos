import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Armazena os alunos cadastrados no sistema e permite realizar operações de adição, busca, remoção,
 * atualização e exclusão
 */

@SuppressWarnings("unused")
public class CadastroAlunos extends ArrayList<Aluno> {
    Scanner input = new Scanner(System.in);

    /**
     * Inicia formulário para preenchimento das informações do aluno e seu posterior cadastro
     */
    public void cadastrarAluno() {
        // Obtendo nome
        String nome = this.inputNome();

        // Obtendo data de nascimento
        LocalDate nascimento = this.inputDataNascimento();


        // Verificando modo de criar matrícula
        int respMatr = inputTipoMatricula();

        // Inserindo novo aluno
        switch (respMatr) {
            case 1 -> {
                String matricula;
                do {
                    System.out.println("Insira a matrícula (XXX-XXX-XXXX):");
                    matricula = input.nextLine();
                } while (!validarMatricula(matricula) || this.getAluno(matricula, false) != null); // Validando matrícula

                Aluno aluno = new Aluno(nome, nascimento, matricula);
                this.add(aluno);
            }
            case 2 -> {
                String matricula;
                do {
                    matricula = matriculaAleatoria();
                } while (this.getAluno(matricula, false) != null); // Verificando se a matrícula já não existe

                Aluno aluno = new Aluno(nome, nascimento, matricula);
                this.add(aluno);
            }
        }

    }

    /**
     * Remove o aluno com base na matrícula fornecida
     */
    public void removerAluno() {
        Aluno query;
        do {
            System.out.println("Informe a matrícula do aluno que deseja remover:\n[6] Cancelar");
            String matricula = input.nextLine();

            if (matricula.equals("6")) {
                break; // Cancela remoção
            }

            query = getAluno(matricula, true); // Buscando aluno

            // Validação e remoção
            if (query != null) {
                this.remove(query);
            }
        } while (query == null);
    }

    /**
     * Realiza a busca de um aluno com base na matrícula fornecida
     * e dispõe suas informações
     */
    public void buscarAluno() {
        Aluno query;
        do {
            System.out.println("Matrícula do aluno que deseja buscar:\n[6] Cancelar");
            String matricula = input.nextLine();

            if ((matricula.equals("6"))) {
                return; // Cancela operação
            }

            query = this.getAluno(matricula, true); // Buscando aluno

            // Validação e obtenção de informações
            if (query != null) {
                query.getInfo();
            }
        } while (query == null);
    }

    /**
     * Atualiza as informaçoes do aluno com base nas especificações fornecidas.
     * Pode-se alterar nome, data de nascimento e a matrícula (gerando ou inserindo)
     */
    public void atualizarAluno() {
        Aluno query;
        do {
            System.out.println("Matrícula do aluno que deseja atualizar:\n[6] Cancelar");
            String matricula = input.nextLine();

            if (matricula.equals("6")) {
                return; // Cancela operação
            }

            query = this.getAluno(matricula, true); // Buscando aluno

        } while (query == null);

        int optAlterar;
        do {
            query.getInfo();
            System.out.println("""
                    Informação que deseja alterar:
                    [1] Nome
                    [2] Data de nascimento
                    [3] Matrícula
                    [4] Sair
                    """);
            optAlterar = Integer.parseInt(input.nextLine());

            switch (optAlterar) {
                case 1 -> {
                    String nome = this.inputNome();
                    query.setNome(nome);
                }
                case 2 -> {
                    LocalDate data = this.inputDataNascimento();
                    query.setNascimento(data.getYear(), data.getMonthValue(), data.getDayOfMonth());
                }
                case 3 -> {
                    int respMatr = this.inputTipoMatricula();

                    if (respMatr == 1) {
                        this.mudarMatricula(query);
                        break;
                    }
                    this.gerarMatricula(query);
                }
                case 4 -> {
                }
                default -> System.out.println("Opção Inválida!!");
            }
        } while (optAlterar != 4);

    }

    /**
     * Lista todos os alunos cadastrados mostrando matrícula
     */
    public void listarAlunos() {
        Visuals.printHeader("LISTA DE ALUNOS CADASTRADOS");
        for (Aluno aluno : this) {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String nome = aluno.getNome();
            int idade = aluno.getIdade();
            String nascimento = formatador.format(aluno.getNascimento());
            String matricula = aluno.getMatricula();

            System.out.printf("%s | %-40s | %d | %s\n", matricula, nome, idade, nascimento);
        }
        System.out.println();
    }


    // TRATAMENTO DO NOME

    /**
     * Formulário para preenchimento do nome do aluno
     *
     * @return nome inserido
     */
    private String inputNome() {
        // Obtendo nome
        String nome;
        do {
            System.out.println("Nome:");
            nome = input.nextLine();
        } while (!nome.trim().matches("([A-Z]([a-z]|ç|Ç)+(\\s|$))+"));
        return nome;
    }


    // TRATAMENTO DA DATA DE NASCIMENTO

    /**
     * Valida a data de nascimento inserida com base no formato informado
     *
     * @param data       String contendo a data que deseja formatar
     * @param formatador Objeto da classe DateTimeFormatter que possui a formatação da data
     * @return true caso o formato seja válido ou false caso contrário
     */
    private boolean validarNascimento(String data, DateTimeFormatter formatador) {
        try {
            LocalDate validadeNascimento = LocalDate.parse(data, formatador);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Data de nascimento inválida!!");
            return false;
        }
    }

    /**
     * Formulário para preenchimento da data de nascimento do aluno
     *
     * @return Objeto {@link LocalDate} com a data de nascimento
     */
    private LocalDate inputDataNascimento() {
        // Validando e obtendo nascimento
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern(Formatos.DATA.getFormato());

        String data;
        do {
            System.out.println("Ano de nascimento (dd/mm/aaaa):");
            data = input.nextLine();
        } while (!validarNascimento(data, formatoData));

        return LocalDate.parse(data, formatoData);
    }

    // TRATAMENTO DA MATRÍCULA


    /**
     * Formulário para definir o tipo de preenchimento de matrícula que se deseja realizar
     *
     * @return 1 caso queira inserir a matrícula ou 2 caso queira gerar uma matrícula
     */
    private int inputTipoMatricula() {
        int respMatr;
        do {
            System.out.println("Deseja inserir uma mátricula[1] ou gerar sua própria[2]?");
            respMatr = Integer.parseInt(input.nextLine());
            if (!(respMatr == 1) && !(respMatr == 2)) {
                System.out.println("Resposta inválida!!");
            }
        } while (!(respMatr == 1) && !(respMatr == 2));

        return respMatr;
    }

    /**
     * Valida a matrícula inserida com base no formato (XXX-XXX-XXXX)
     *
     * @param matricula String com a matrícula que se deseja inserir
     * @return true caso o formato seja válido ou false caso contrário
     */
    private boolean validarMatricula(String matricula) {
        try {
            Aluno validadeAluno = new Aluno("", LocalDate.of(1, 1, 1), matricula);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de matrícula inválida");
            return false;
        }
    }

    /**
     * Gera uma matrícula aleatória
     *
     * @return {@link String} com a matrícula
     */
    private String matriculaAleatoria() {
        StringBuilder matricula = new StringBuilder();

        String[] dezNumeros = new String[10];
        // Obtendo 10 números
        for (int index = 0; index < 10; index++) {
            dezNumeros[index] = Integer.toString((int) (Math.floor(Math.random() * (10))));
        }

        // Formatando
        for (int index = 0; index < 10; index++) {
            if (index != 2 && index != 5) {
                matricula.append(dezNumeros[index]);
            } else {
                matricula.append(dezNumeros[index] + "-");
            }
        }

        return matricula.toString();
    }

    /**
     * Redefine de um aluno com base na matrícula especificada, caso já não exista
     * alguma igual àquela especificada
     *
     * @param aluno aluno cuja matrícula deseja alterar
     */
    private void mudarMatricula(Aluno aluno) {
        String novaMatricula;
        do {
            System.out.println("Insira a nova matrícula (XXX-XXX-XXXX):");
            novaMatricula = input.nextLine();
        } while (!novaMatricula.matches(Formatos.MATRICULA.getFormato())
                || this.getAluno(novaMatricula, false) != null); // Verifica se atende o formato e já não existe

        aluno.setMatricula(novaMatricula);
    }

    /**
     * Gera uma nova matrícula para o aluno fornecido. Além disso, verifica
     * se a matrícula gerada já não existe
     *
     * @param aluno Aluno cuja matrícula deseja alterar
     */
    private void gerarMatricula(Aluno aluno) {
        String novaMatricula;
        do {
            novaMatricula = this.matriculaAleatoria();
        } while (getAluno(novaMatricula, false) != null); // Verifica se já não existe

        aluno.setMatricula(novaMatricula);
    }


    // ADICIONAIS

    /**
     * Busca um aluno com base na matrícula fornecida
     *
     * @param matricula Matrícula para realizar a busca do aluno
     * @return Objeto {@link Aluno} caso exista. Caso contrário, retorna null
     */
    private Aluno getAluno(String matricula, boolean mensagem) {
        // Busca pelo primeiro resultado do array
        Optional<Aluno> query = this.stream().filter(aluno -> aluno.getMatricula().equals(matricula)).findFirst();

        // Validação
        if (query.isPresent()) {
            return query.get();
        }
        if (mensagem) {
            System.out.printf("Nenhum aluno com matrícula \"%s\" cadastrada!!\n", matricula);
        }
        return null;
    }
}
