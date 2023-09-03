import java.time.LocalDate;
import java.time.Period;
public class Aluno {
    // Atributos

    private String nome;
    private LocalDate nascimento;
    private String matricula;

    // Métodos especiais

    public Aluno(String nome, LocalDate nascimento, String matricula) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.matricula = matricula;
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
        this.matricula = matricula;
    }

    // Métodos

    private String gerarMatricular() {
        return null;
    }
}
