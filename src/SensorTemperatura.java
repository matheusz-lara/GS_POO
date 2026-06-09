import java.util.Random;

public class SensorTemperatura extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAtencao;
    private double limiteAlerta;
    private double limiteCritico;
    private static final Random random = new Random();

    public SensorTemperatura(String id) {
        super(id, "Sensor de Temperatura");
        this.limiteAtencao = 60.0;
        this.limiteAlerta  = 80.0;
        this.limiteCritico = 100.0;
    }

    @Override
    public double lerValor() {
        valorAtual = 15.0 + random.nextDouble() * 120.0; // simula 15°C a 135°C
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return ligado;
    }

    @Override
    public String retornarTipo() {
        return "TEMPERATURA";
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
        System.out.printf("  [%-22s] %7.2f °C  | %s%n", nome, valorAtual, verificarNivelAlerta());
    }
}
