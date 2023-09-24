import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


@SuppressWarnings("unused")
public class Aluno {
    // Atributos
    private String nome;
    private LocalDate nascimento;
    private String matricula;

    // Métodos especiais
    public Aluno(String nome, LocalDate nascimento, String matricula) {
        this.setNome(nome);
        this.setNascimento(nascimento.getYear(), nascimento.getMonthValue(), nascimento.getDayOfMonth());
        this.setMatricula(matricula);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(int ano, int mes, int dia) {
        this.nascimento = LocalDate.of(ano, mes, dia);
    }

    public int getIdade() {
        return Period.between(this.getNascimento(), LocalDate.now()).getYears();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (matricula.matches(Formatos.MATRICULA.getFormato())) {
            this.matricula = matricula;
            return;
        }
        throw new IllegalArgumentException("A matrícula inserida é inválida");
    }

    // Métodos

    public void getInfo(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern(Formatos.DATA.getFormato());

        int idade = this.getIdade();
        String nascimento = formatador.format(this.getNascimento());
        String matricula = this.getMatricula();

        Visuals.printHeader("INFORMAÇÕES: " + this.getNome());
        System.out.printf("""
                Matrícula: %s
                Data de nascimento: %s
                Idade: %d
                """, matricula, nascimento, idade);
    }
}
