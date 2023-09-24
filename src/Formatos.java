public enum Formatos {
    DATA("dd/MM/yyyy"),
    MATRICULA("^[0-9]{3}-[0-9]{3}-[0-9]{4}$");

    private final String formato;

    Formatos(String formato) {
        this.formato = formato;
    }

    public String getFormato() {
        return this.formato;
    }

}
