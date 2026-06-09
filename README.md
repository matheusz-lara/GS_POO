# 🚀 Plataforma de Monitoramento Espacial — POO

Sistema de monitoramento de uma estação espacial desenvolvido em Java aplicando os quatro pilares da Programação Orientada a Objetos: **Classe Abstrata**, **Interface**, **Encapsulamento** e **Herança**.

---

## 📐 Diagrama de Classes

<img width="2222" height="1396" alt="Class Diagram0" src="https://github.com/user-attachments/assets/61c87e63-037c-444b-937f-b74bd45af82c" />

---

## 📁 Estrutura do Projeto

```
projeto-espacial/
├── ComponenteEspacial.java     # Classe abstrata base
├── Sensor.java                 # Interface dos sensores
├── SensorTemperatura.java      # Sensor de temperatura (°C)
├── SensorPressao.java          # Sensor de pressão (bar)
├── SensorRadiacao.java         # Sensor de radiação (mSv)
├── DadosMissao.java            # Dados encapsulados da missão
├── SistemaPropulsao.java       # Classe abstrata de propulsão
├── PropulsaoQuimica.java       # Motor químico (LOX/LH2)
├── PropulsaoEletrica.java      # Motor iônico elétrico
└── SistemaMonitoramento.java   # Aplicação interativa (menu)
```

---

## 🧱 Conceitos de POO Aplicados

### 1. Classe Abstrata — `ComponenteEspacial`

Serve de molde para todos os componentes da estação. Não pode ser instanciada diretamente.

| Membro | Tipo | Descrição |
|--------|------|-----------|
| `id` | `String` | Identificador único do componente |
| `nome` | `String` | Nome legível |
| `ligado` | `boolean` | Estado de operação |
| `temperatura` | `double` | Temperatura do componente |
| `ligar()` | método concreto | Liga o componente |
| `desligar()` | método concreto | Desliga o componente |
| `monitorar()` | **método abstrato** | Cada subclasse exibe seu próprio status |

Todas as subclasses concretas — sensores e motores — **obrigatoriamente** implementam `monitorar()`.

---

### 2. Interface — `Sensor`

Contrato que garante que qualquer tipo de sensor terá os mesmos métodos básicos.

| Método | Retorno | Descrição |
|--------|---------|-----------|
| `lerValor()` | `double` | Leitura simulada com `Random` |
| `verificarFuncionamento()` | `boolean` | Retorna `true` se o sensor estiver ligado |
| `retornarTipo()` | `String` | Nome do tipo de medição |
| `definirLimiteAlerta(atencao, alerta, critico)` | `void` | Define os três limiares de alerta |
| `verificarNivelAlerta()` | `String` | Retorna `"NORMAL"`, `"ATENÇÃO"`, `"ALERTA"` ou `"CRÍTICO"` |

**Implementada por:** `SensorTemperatura`, `SensorPressao`, `SensorRadiacao`.

#### Limites padrão de cada sensor

| Sensor | Unidade | ATENÇÃO | ALERTA | CRÍTICO |
|--------|---------|---------|--------|---------|
| `SensorTemperatura` | °C | ≥ 60 | ≥ 80 | ≥ 100 |
| `SensorPressao` | bar | ≥ 1.5 | ≥ 2.0 | ≥ 2.5 |
| `SensorRadiacao` | mSv | ≥ 50 | ≥ 100 | ≥ 200 |

---

### 3. Encapsulamento — `DadosMissao`

Todos os atributos são `private`. Dados sensíveis requerem senha para leitura e escrita.

| Atributo | Tipo | Proteção |
|----------|------|----------|
| `coordenadaX/Y/Z` | `double` | Leitura e escrita exigem senha |
| `codigoAcesso` | `String` | `final` — nunca exposto |
| `nivelCombustivel` | `double` | Setter valida intervalo 0–100 |
| `trajetoria` | `String` | Setter rejeita string vazia/nula |
| `numeroDeTripulantes` | `int` | Setter rejeita valores negativos |

**Alertas automáticos de combustível:**

| Nível | Combustível |
|-------|-------------|
| ATENÇÃO | < 20% |
| ALERTA | ≤ 10% |
| CRÍTICO | ≤ 5% |

> **Senha padrão definida em `SistemaMonitoramento`:** `FIAP2026`

---

### 4. Herança — `SistemaPropulsao`

Hierarquia de classes de propulsão com comportamento polimórfico.

```
ComponenteEspacial  (abstract)
└── SistemaPropulsao  (abstract)
    ├── PropulsaoQuimica
    └── PropulsaoEletrica
```

| Classe | Atributo específico | Cálculo de empuxo |
|--------|--------------------|--------------------|
| `PropulsaoQuimica` | `impulsoEspecifico` = 450 s | `(pot/100) × Isp × 9.8 × 10` |
| `PropulsaoEletrica` | `potenciaMaximaKw` = 80 kW | `(pot/100) × kW × 0.2` |

Ambas sobrescrevem `acelerar(double potencia)` chamando `super.acelerar()` — que centraliza a validação (0–100%) e a verificação de motor ligado — antes de invocar o `calcularEmpuxo()` específico de cada tipo.

---

## ⚙️ Pré-requisitos

### Pré-requisito

- Java 17+ (compatível com switch expressions e text blocks)


---

## 🖥️ Navegação do Menu Interativo

```
========= MENU PRINCIPAL =========
  1. Verificar Sensores
  2. Controlar Propulsao
  3. Gerenciar Dados da Missao
  4. Simular Alertas
  5. Exibir Status Completo
  0. Sair
```

| Opção | Sub-opções |
|-------|-----------|
| **1 — Sensores** | Ler todos · Verificar funcionamento · Definir limites |
| **2 — Propulsão** | Ligar motor · Desligar motor · Acelerar · Ver status |
| **3 — Missão** | Ver/atualizar coordenadas · Combustível · Trajetória · Tripulantes |
| **4 — Alertas** | Varredura automática de todos os sensores + combustível |
| **5 — Status** | Visão consolidada de sensores, motores e missão |

---

## 🏷️ Identificadores Padrão

| Componente | ID |
|------------|----|
| Sensor de Temperatura | `ST-01` |
| Sensor de Pressão | `SP-01` |
| Sensor de Radiação | `SR-01` |
| Motor Químico | `MQ-01` |
| Motor Elétrico | `ME-01` |

---
