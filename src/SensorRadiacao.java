import java.util.Random;

public class SensorRadiacao extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAtencao;
    private double limiteAlerta;
    private double limiteCritico;
    private static final Random random = new Random();

    public SensorRadiacao(String id) {
        super(id, "Sensor de Radiação");
        this.limiteAtencao = 50.0;
        this.limiteAlerta  = 100.0;
        this.limiteCritico = 200.0;
    }

    @Override
    public double lerValor() {
        valorAtual = random.nextDouble() * 260.0; // simula 0 a 260 mSv
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return ligado;
    }

    @Override
    public String retornarTipo() {
        return "RADIAÇÃO";
    }

    @Override
    public void definirLimiteAlerta(double atencao, double alerta, double critico) {
        this.limiteAtencao = atencao;
        this.limiteAlerta  = alerta;
        this.limiteCritico = critico;
    }

    @Override
    public String verificarNivelAlerta() {
        if (valorAtual >= limiteCritico) return "CRÍTICO";
        if (valorAtual >= limiteAlerta)  return "ALERTA";
        if (valorAtual >= limiteAtencao) return "ATENÇÃO";
        return "NORMAL";
    }

    @Override
    public void monitorar() {
        if (!ligado) {
            System.out.println("  [" + nome + "] DESLIGADO");
            return;
        }
        lerValor();
        System.out.printf("  [%-22s] %7.2f mSv | %s%n", nome, valorAtual, verificarNivelAlerta());
    }
}
