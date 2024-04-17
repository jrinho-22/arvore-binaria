package ABB;

public class Elemento {
    private int valor;
    private Elemento esquerda;
    private Elemento direita;

    public Elemento (int novoValor){
        this.valor = novoValor;
        this.direita = null;
        this.esquerda = null;
    }

    public Elemento getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Elemento esquerda) {
        this.esquerda = esquerda;
    }

    public Elemento getDireita() {
        return direita;
    }

    public void setDireita(Elemento direita) {
        this.direita = direita;
    }

    public int getValor(){
        return valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }
}
