public abstract class SistemaPropulsao extends ComponenteEspacial {

    protected double potencia; // 0 a 100 %
    protected double empuxo;   // Newton (simulado)

    public SistemaPropulsao(String id, String nome) {
        super(id, nome);
        this.potencia = 0.0;
        this.empuxo   = 0.0;
    }

    // Método concreto: valida e aplica a potência
    public void acelerar(double potencia) {
        if (!ligado) {
            System.out.println("  ERRO: motor desligado. Ligue-o antes de acelerar.");
            return;
        }
        if (potencia < 0 || potencia > 100) {
            System.out.println("  ERRO: potência deve estar entre 0 e 100.");
            return;
        }
        this.potencia = potencia;
        System.out.printf("  [%s] Potencia ajustada para %.0f%%%n", nome, potencia);
    }

    protected abstract void calcularEmpuxo();

    public double getEmpuxo()   { return empuxo; }
    public double getPotencia() { return potencia; }
}
