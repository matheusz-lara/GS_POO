import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaMonitoramento {

    private static final List<Sensor> sensores = new ArrayList<>();
    private static final List<SistemaPropulsao> motores = new ArrayList<>();
    private static DadosMissao dadosMissao;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarSistema();
        menuPrincipal();
        scanner.close();
    }

    // =========================================================
    // INICIALIZAÇÃO
    // =========================================================

    private static void inicializarSistema() {
        SensorTemperatura sTemp  = new SensorTemperatura("ST-01");
        SensorPressao     sPress = new SensorPressao("SP-01");
        SensorRadiacao    sRad   = new SensorRadiacao("SR-01");

        sTemp.ligar();
        sPress.ligar();
        sRad.ligar();

        sensores.add(sTemp);
        sensores.add(sPress);
        sensores.add(sRad);

        motores.add(new PropulsaoQuimica("MQ-01"));
        motores.add(new PropulsaoEletrica("ME-01"));

        // Senha de acesso às coordenadas: FIAP2026
        dadosMissao = new DadosMissao("FIAP2026", 7, "Terra -> Lua -> Marte");

        System.out.println("==================================================");
        System.out.println("     PLATAFORMA DE MONITORAMENTO ESPACIAL");
        System.out.println("==================================================");
        System.out.println("  Sistema inicializado. Sensores ativos.");
        System.out.println("  [AVISO] Senha de coordenadas: FIAP2026");
        System.out.println();
    }

    // =========================================================
    // MENU PRINCIPAL
    // =========================================================

    private static void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("  1. Verificar Sensores");
            System.out.println("  2. Controlar Propulsao");
            System.out.println("  3. Gerenciar Dados da Missao");
            System.out.println("  4. Simular Alertas");
            System.out.println("  5. Exibir Status Completo");
            System.out.println("  0. Sair");
            System.out.print("  Opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> menuSensores();
                case 2 -> menuPropulsao();
                case 3 -> menuDadosMissao();
                case 4 -> simularAlertas();
                case 5 -> exibirStatusCompleto();
                case 0 -> System.out.println("\n  Encerrando sistema. Boa viagem!");
                default -> System.out.println("  Opcao invalida.");
            }
        } while (opcao != 0);
    }

    // =========================================================
    // MENU SENSORES
    // =========================================================

    private static void menuSensores() {
        int opcao;
        do {
            System.out.println("\n-------- SENSORES --------");
            System.out.println("  1. Ler todos os sensores");
            System.out.println("  2. Verificar funcionamento");
            System.out.println("  3. Definir limites de alerta");
            System.out.println("  0. Voltar");
            System.out.print("  Opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> lerTodosSensores();
                case 2 -> verificarFuncionamento();
                case 3 -> definirLimites();
                case 0 -> {}
                default -> System.out.println("  Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static void lerTodosSensores() {
        System.out.println("\n-- Leitura dos Sensores --");
        for (Sensor s : sensores) {
            ((ComponenteEspacial) s).monitorar();
        }
    }

    private static void verificarFuncionamento() {
        System.out.println("\n-- Funcionamento dos Sensores --");
        for (Sensor s : sensores) {
            String status = s.verificarFuncionamento() ? "OK" : "INATIVO";
            System.out.printf("  %-15s -> %s%n", s.retornarTipo(), status);
        }
    }

    private static void definirLimites() {
        System.out.println("\n-- Definir Limites de Alerta --");
        listarSensores();
        System.out.print("  Escolha o sensor (1-" + sensores.size() + "): ");
        int idx = lerInteiro() - 1;

        if (idx < 0 || idx >= sensores.size()) {
            System.out.println("  Sensor invalido.");
            return;
        }

        System.out.print("  Limite ATENCAO : ");
        double atencao = lerDouble();
        System.out.print("  Limite ALERTA  : ");
        double alerta = lerDouble();
        System.out.print("  Limite CRITICO : ");
        double critico = lerDouble();

        if (atencao <= 0 || alerta <= atencao || critico <= alerta) {
            System.out.println("  ERRO: limites devem ser crescentes e positivos.");
            return;
        }
        sensores.get(idx).definirLimiteAlerta(atencao, alerta, critico);
        System.out.println("  Limites atualizados.");
    }

    private static void listarSensores() {
        for (int i = 0; i < sensores.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, ((ComponenteEspacial) sensores.get(i)).getNome());
        }
    }

    // =========================================================
    // MENU PROPULSÃO
    // =========================================================

    private static void menuPropulsao() {
        int opcao;
        do {
            System.out.println("\n-------- PROPULSAO --------");
            System.out.println("  1. Ligar motor");
            System.out.println("  2. Desligar motor");
            System.out.println("  3. Acelerar");
            System.out.println("  4. Status dos motores");
            System.out.println("  0. Voltar");
            System.out.print("  Opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> acaoMotor(true);
                case 2 -> acaoMotor(false);
                case 3 -> acelerarMotor();
                case 4 -> statusMotores();
                case 0 -> {}
                default -> System.out.println("  Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private static void listarMotores() {
        for (int i = 0; i < motores.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, motores.get(i).getNome());
        }
    }

    private static void acaoMotor(boolean ligar) {
        listarMotores();
        System.out.print("  Escolha o motor (1-" + motores.size() + "): ");
        int idx = lerInteiro() - 1;
        if (idx < 0 || idx >= motores.size()) {
            System.out.println("  Motor invalido.");
            return;
        }
        if (ligar) motores.get(idx).ligar();
        else       motores.get(idx).desligar();
    }

    private static void acelerarMotor() {
        listarMotores();
        System.out.print("  Escolha o motor (1-" + motores.size() + "): ");
        int idx = lerInteiro() - 1;
        if (idx < 0 || idx >= motores.size()) {
            System.out.println("  Motor invalido.");
            return;
        }
        System.out.print("  Potencia (0-100): ");
        double pot = lerDouble();
        motores.get(idx).acelerar(pot);
    }

    private static void statusMotores() {
        System.out.println("\n-- Status dos Motores --");
        for (SistemaPropulsao m : motores) {
            m.monitorar();
        }
    }

    // =========================================================
    // MENU DADOS DA MISSÃO
    // =========================================================

    private static void menuDadosMissao() {
        int opcao;
        do {
            System.out.println("\n-------- DADOS DA MISSAO --------");
            System.out.println("  1. Ver coordenadas (requer senha)");
            System.out.println("  2. Atualizar coordenadas (requer senha)");
            System.out.println("  3. Ver nivel de combustivel");
            System.out.println("  4. Atualizar combustivel");
            System.out.println("  5. Ver trajetoria");
            System.out.println("  6. Atualizar trajetoria");
            System.out.println("  7. Ver numero de tripulantes");
            System.out.println("  8. Atualizar tripulantes");
            System.out.println("  0. Voltar");
            System.out.print("  Opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> {
                    System.out.print("  Senha: ");
                    System.out.println(dadosMissao.getCoordenadas(scanner.nextLine().trim()));
                }
                case 2 -> {
                    System.out.print("  Senha: ");
                    String senha = scanner.nextLine().trim();
                    System.out.print("  X: "); double x = lerDouble();
                    System.out.print("  Y: "); double y = lerDouble();
                    System.out.print("  Z: "); double z = lerDouble();
                    dadosMissao.setCoordenadas(x, y, z, senha);
                }
                case 3 -> System.out.printf("  Combustivel: %.1f%%%n", dadosMissao.getNivelCombustivel());
                case 4 -> {
                    System.out.print("  Novo nivel (0-100): ");
                    dadosMissao.setNivelCombustivel(lerDouble());
                }
                case 5 -> System.out.println("  Trajetoria: " + dadosMissao.getTrajetoria());
                case 6 -> {
                    System.out.print("  Nova trajetoria: ");
                    dadosMissao.setTrajetoria(scanner.nextLine().trim());
                }
                case 7 -> System.out.println("  Tripulantes: " + dadosMissao.getNumeroDeTripulantes());
                case 8 -> {
                    System.out.print("  Numero de tripulantes: ");
                    dadosMissao.setNumeroDeTripulantes(lerInteiro());
                }
                case 0 -> {}
                default -> System.out.println("  Opcao invalida.");
            }
        } while (opcao != 0);
    }

    // =========================================================
    // SIMULAÇÃO DE ALERTAS
    // =========================================================

    private static void simularAlertas() {
        System.out.println("\n======== SIMULACAO DE ALERTAS ========");
        boolean temAlerta = false;

        for (Sensor s : sensores) {
            if (!s.verificarFuncionamento()) continue;

            double valor  = s.lerValor();
            String nivel  = s.verificarNivelAlerta();

            if (!nivel.equals("NORMAL")) {
                temAlerta = true;
                String tag = switch (nivel) {
                    case "ATENÇÃO"  -> "[!  ] ATENCAO ";
                    case "ALERTA"   -> "[!! ] ALERTA  ";
                    default         -> "[!!!] CRITICO ";
                };
                System.out.printf("  %s | %-16s | %.4f%n", tag, s.retornarTipo(), valor);
            }
        }

        double comb = dadosMissao.getNivelCombustivel();
        if (comb < 20.0) {
            temAlerta = true;
            String tag = comb <= 5.0 ? "[!!!] CRITICO " : comb <= 10.0 ? "[!! ] ALERTA  " : "[!  ] ATENCAO ";
            System.out.printf("  %s | %-16s | %.1f%%%n", tag, "COMBUSTIVEL", comb);
        }

        if (!temAlerta) {
            System.out.println("  Todos os sistemas operando normalmente.");
        }
        System.out.println("======================================");
    }

    // =========================================================
    // STATUS COMPLETO
    // =========================================================

    private static void exibirStatusCompleto() {
        System.out.println("\n============ STATUS COMPLETO ============");

        System.out.println("\n  -- SENSORES --");
        for (Sensor s : sensores) ((ComponenteEspacial) s).monitorar();

        System.out.println("\n  -- PROPULSAO --");
        for (SistemaPropulsao m : motores) m.monitorar();

        System.out.println("\n  -- DADOS DA MISSAO --");
        dadosMissao.exibirStatus();

        System.out.println("=========================================");
    }

    // =========================================================
    // UTILITÁRIOS DE LEITURA
    // =========================================================

    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("  Valor invalido. Digite um inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("  Valor invalido. Digite um numero: ");
            }
        }
    }
}
