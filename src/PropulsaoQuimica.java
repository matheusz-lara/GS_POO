public class PropulsaoQuimica extends SistemaPropulsao {

    // Atributo específico: impulso específico do combustível (segundos)
    private final double impulsoEspecifico;

    public PropulsaoQuimica(String id) {
        super(id, "Propulsao Quimica");
        this.impulsoEspecifico = 450.0; // tipico de motores químicos (LOX/LH2)
    }

    @Override
    public void acelerar(double potencia) {
        super.acelerar(potencia); // validação na classe mãe
        if (ligado && potencia >= 0 && potencia <= 100) {
            calcularEmpuxo();
        }
    }

    // F ≈ (potencia/100) * Isp * g0 * fluxo_massa_referencia
    @Override
    protected void calcularEmpuxo() {
        empuxo = (potencia / 100.0) * impulsoEspecifico * 9.8 * 10.0;
        System.out.printf("  [%s] Empuxo calculado: %.2f N  (Isp: %.0f s)%n", nome, empuxo, impulsoEspecifico);
    }

    @Override
    public void monitorar() {
        System.out.printf("  [%-20s] Status: %-7s | Potencia: %5.1f%% | Empuxo: %.2f N%n",
            nome, ligado ? "LIGADO" : "DESLIGADO", potencia, empuxo);
    }
}
