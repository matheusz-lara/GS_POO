public interface Sensor {

    double lerValor();
    boolean verificarFuncionamento();
    String retornarTipo();
    void definirLimiteAlerta(double atencao, double alerta, double critico);
    String verificarNivelAlerta();
}
