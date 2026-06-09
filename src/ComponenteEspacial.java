public abstract class ComponenteEspacial {

    protected String id;
    protected String nome;
    protected boolean ligado;
    protected double temperatura;

    public ComponenteEspacial(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.ligado = false;
        this.temperatura = 20.0;
    }

    public void ligar() {
        ligado = true;
        System.out.println("  [" + nome + "] Ligado com sucesso.");
    }

    public void desligar() {
        ligado = false;
        System.out.println("  [" + nome + "] Desligado.");
    }

    public abstract void monitorar();

    public String getId()               { return id; }
    public String getNome()             { return nome; }
    public boolean isLigado()           { return ligado; }
    public double getTemperatura()      { return temperatura; }
    public void setTemperatura(double t){ this.temperatura = t; }
}
