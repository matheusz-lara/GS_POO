import java.util.Random;

public class SensorPressao extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteAtencao;
    private double limiteAlerta;
    private double limiteCritico;
    private static final Random random = new Random();

    public SensorPressao(String id) {
        super(id, "Sensor de Pressão");
        this.limiteAtencao = 1.5;
        this.limiteAlerta  = 2.0;
        this.limiteCritico = 2.5;
    }

    @Override
    public double lerValor() {
        valorAtual = 0.5 + random.nextDouble() * 3.0; // simula 0.5 a 3.5 bar
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return ligado;
    }

    @Override
    public String retornarTipo() {
        return "PRESSÃO";
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
        System.out.printf("  [%-22s] %7.3f bar | %s%n", nome, valorAtual, verificarNivelAlerta());
    }
}
