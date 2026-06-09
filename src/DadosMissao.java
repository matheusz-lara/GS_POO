public class DadosMissao {

    private double coordenadaX;
    private double coordenadaY;
    private double coordenadaZ;
    private final String codigoAcesso;
    private double nivelCombustivel;
    private String trajetoria;
    private int numeroDeTripulantes;

    public DadosMissao(String codigoAcesso, int numeroDeTripulantes, String trajetoria) {
        this.codigoAcesso        = codigoAcesso;
        this.nivelCombustivel    = 100.0;
        this.numeroDeTripulantes = numeroDeTripulantes;
        this.trajetoria          = trajetoria;
    }

    // --- Coordenadas (acesso restrito por senha) ---

    public String getCoordenadas(String senha) {
        if (!senha.equals(codigoAcesso)) {
            return "  ACESSO NEGADO: senha incorreta.";
        }
        return String.format("  X: %.4f | Y: %.4f | Z: %.4f", coordenadaX, coordenadaY, coordenadaZ);
    }

    public boolean setCoordenadas(double x, double y, double z, String senha) {
        if (!senha.equals(codigoAcesso)) {
            System.out.println("  ACESSO NEGADO: senha incorreta.");
            return false;
        }
        this.coordenadaX = x;
        this.coordenadaY = y;
        this.coordenadaZ = z;
        System.out.println("  Coordenadas atualizadas.");
        return true;
    }

    // --- Combustível ---

    public double getNivelCombustivel() {
        return nivelCombustivel;
    }

    public boolean setNivelCombustivel(double nivel) {
        if (nivel < 0 || nivel > 100) {
            System.out.println("  ERRO: nível de combustível deve estar entre 0% e 100%.");
            return false;
        }
        this.nivelCombustivel = nivel;
        verificarCombustivel();
        return true;
    }

    private void verificarCombustivel() {
        if (nivelCombustivel <= 5.0) {
            System.out.printf("  [!!!] CRITICO  | Combustivel em %.1f%% - MISSAO EM RISCO!%n", nivelCombustivel);
        } else if (nivelCombustivel <= 10.0) {
            System.out.printf("  [!! ] ALERTA   | Combustivel em %.1f%%!%n", nivelCombustivel);
        } else if (nivelCombustivel < 20.0) {
            System.out.printf("  [!  ] ATENCAO  | Combustivel abaixo de 20%% - nivel atual: %.1f%%%n", nivelCombustivel);
        }
    }

    // --- Trajetória ---

    public String getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(String trajetoria) {
        if (trajetoria == null || trajetoria.trim().isEmpty()) {
            System.out.println("  ERRO: trajetória inválida.");
            return;
        }
        this.trajetoria = trajetoria.trim();
    }

    // --- Tripulantes ---

    public int getNumeroDeTripulantes() {
        return numeroDeTripulantes;
    }

    public boolean setNumeroDeTripulantes(int numero) {
        if (numero < 0) {
            System.out.println("  ERRO: número de tripulantes não pode ser negativo.");
            return false;
        }
        this.numeroDeTripulantes = numero;
        return true;
    }

    // --- Status geral (sem dados restritos) ---

    public void exibirStatus() {
        System.out.printf("  Combustivel    : %.1f%%%n", nivelCombustivel);
        System.out.printf("  Tripulantes    : %d%n",    numeroDeTripulantes);
        System.out.printf("  Trajetoria     : %s%n",    trajetoria);
        System.out.println("  Coordenadas    : [PROTEGIDAS - requer senha]");
    }
}
