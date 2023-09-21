import java.time.LocalDate;
import java.time.Period;


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
        this.matricula = matricula;
    }

    public Aluno(String nome, LocalDate nascimento) {
        this.setNome(nome);
        this.setNascimento(nascimento.getYear(), nascimento.getMonthValue(), nascimento.getDayOfMonth());
        this.setMatricula();
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
        if (matricula.matches(this.getMaskMatricula())) {
            this.matricula = matricula;
            return;
        }
        throw new IllegalArgumentException("A matrícula inserida é inválida");
    }

    public void setMatricula() {
        StringBuilder matricula = new StringBuilder();

        String[] dezNumeros = new String[10];
        // Obtendo 10 números
        for (int index = 0; index < 10; index++) {
            dezNumeros[index] = Integer.toString((int) (Math.floor(Math.random() * (10))));
        }

        // Formatando
        for (int index = 0; index < 10; index++){
            if (index != 2 && index != 5){
                matricula.append(dezNumeros[index]);
            } else {
                matricula.append(dezNumeros[index] + "-");
            }
        }

        this.matricula = matricula.toString();
    }

    // Métodos
    private String getMaskMatricula(){
        // Formato: XXX-XXX-XXXX (Apenas digitos)
        return "^[0-9]{3}-[0-9]{3}-[0-9]{4}$";
    }

}
