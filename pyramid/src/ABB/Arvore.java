package ABB;

public class Arvore{
    private Elemento raiz;
    public Arvore(){
        this.raiz = null;
    }


    // Método de adição
    public boolean adicionar(int valor){
        Elemento novoElemento = new Elemento(valor);
        if (raiz == null){
            this.raiz = novoElemento;
            return true;
        }else {
            Elemento atual = this.raiz;
            while (true) {
                if (valor < atual.getValor()) {
                    if (atual.getEsquerda() == null) {
                        atual.setEsquerda(novoElemento);
                        return true;
                    }
                    atual = atual.getEsquerda();
                } else if (valor > atual.getValor()) {
                    if (atual.getDireita() == null) {
                        atual.setDireita(novoElemento);
                        return true;
                    }
                    atual = atual.getDireita();
                } else {
                    // Valor já existe
                    return false;
                }
            }
        }
    }


    // Método de busca
    public boolean buscar(int valor) {
        Elemento atual = this.raiz;
        while (atual != null) {
            if (valor == atual.getValor()) {
                return true;
            }
            if (valor < atual.getValor()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }
        return false;
    }


    // Método de remoção
    public Elemento remover(int valor) {
        this.raiz = removerNo(this.raiz, valor);
        return this.raiz;
    }
    private Elemento removerNo(Elemento elementoAtual, int valor) {
        if (elementoAtual == null) {
            return null;
        }
        // Descobre onde o valor está
        if (valor < elementoAtual.getValor()) {
            elementoAtual.setEsquerda(removerNo(elementoAtual.getEsquerda(), valor));
        } else if (valor > elementoAtual.getValor()) {
            elementoAtual.setDireita(removerNo(elementoAtual.getDireita(), valor));
        } else {
            // No com apenas um filho ou sem filho
            if (elementoAtual.getEsquerda() == null) {
                return elementoAtual.getDireita();
            } else if (elementoAtual.getDireita() == null) {
                return elementoAtual.getEsquerda();
            }
            // No com dois filhos
            elementoAtual.setValor(menorValor(elementoAtual.getDireita()));
            elementoAtual.setDireita(removerNo(elementoAtual.getDireita(), elementoAtual.getValor()));
        }
        return elementoAtual;
    }
    private int menorValor(Elemento elementoAtual) {
        int valorMinimo = elementoAtual.getValor();
        while (elementoAtual.getEsquerda() != null) {
            valorMinimo = elementoAtual.getEsquerda().getValor();
            elementoAtual = elementoAtual.getEsquerda();
        }
        return valorMinimo;
    }

    // Método de impressão em pré-ordem
    public void imprimir() {
        imprimirRecursivamente(this.raiz, "", true);
    }

    private void imprimirRecursivamente(Elemento elementoAtual, String prefixo, boolean ehEsquerdo) {
        if (elementoAtual != null) {
            System.out.println(prefixo + (ehEsquerdo ? "├── " : "└── ") + elementoAtual.getValor());

            // Chamadas recursivas para esquerda e direita
            imprimirRecursivamente(elementoAtual.getEsquerda(), prefixo + (ehEsquerdo ? "│   " : "    "), true);
            imprimirRecursivamente(elementoAtual.getDireita(), prefixo + (ehEsquerdo ? "│   " : "    "), false);
        }
    }
}

