public class PropulsaoEletrica extends SistemaPropulsao {

    // Atributo específico: potência elétrica máxima disponível (kW)
    private final double potenciaMaximaKw;

    public PropulsaoEletrica(String id) {
        super(id, "Propulsao Eletrica");
        this.potenciaMaximaKw = 80.0; // propulsor iônico de alta eficiência
    }

    @Override
    public void acelerar(double potencia) {
        super.acelerar(potencia); // validação na classe mãe
        if (ligado && potencia >= 0 && potencia <= 100) {
            calcularEmpuxo();
        }
    }

    // Propulsão elétrica: baixo empuxo, altíssima eficiência
    @Override
    protected void calcularEmpuxo() {
        double kWAtual = (potencia / 100.0) * potenciaMaximaKw;
        empuxo = kWAtual * 0.2; // ~0.2 N/kW (propulsor iônico simplificado)
        System.out.printf("  [%s] Empuxo calculado: %.3f N  (%.2f kW consumidos)%n", nome, empuxo, kWAtual);
    }

    @Override
    public void monitorar() {
        double kWAtual = (potencia / 100.0) * potenciaMaximaKw;
        System.out.printf("  [%-20s] Status: %-7s | Potencia: %5.1f%% | Empuxo: %.3f N | Energia: %.2f kW%n",
            nome, ligado ? "LIGADO" : "DESLIGADO", potencia, empuxo, kWAtual);
    }
}
