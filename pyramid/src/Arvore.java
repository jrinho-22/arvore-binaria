public class Arvore {
    public Node raiz;
    public String[][] grid = new String[10][80];
    Node closestNode = new Node(999999999);
    Node toDelete;

    public Arvore() {
        raiz = null;
        for (int y = 1; y < 10; y++) {
            for (int x = 1; x < 80; x++) {
                grid[y][x] = " ";
            }
        }
    }

    public void search(Node node){
        if (raiz != null){
            Node current = raiz;
        } else {
            return;
        }
        goDownSearch(raiz, node);

    }

    public Node goDownSearch(Node currentNode, Node node) {
        if (node.value < currentNode.value) {
            if (currentNode.left != null) {
                Node returnedNode = goDownSearch(currentNode.left, node);
                return returnedNode;
            }
        } else if (node.value > currentNode.value) {
            if (currentNode.right != null) {
                Node returnedNode = goDownSearch(currentNode.right, node);
                return returnedNode;
            }
        } else {
            return currentNode;
        }
        System.out.println("false");
        return null;
    }

    public void delete(Node node) {
        if (raiz != null){
            Node current = raiz;
        } else {
            return;
        }
        Node searchNode = goDownSearch(raiz, node);
        toDelete = searchNode;
        // sem filhos
        if (searchNode.right == null && searchNode.left == null) {
            Node parent = searchNode.parent;
            if (searchNode.childSide == 'l') {
                parent.left = null;
            } else {
                parent.right = null;
            }
            grid[searchNode.posicaoY][searchNode.posicaoX] = " ";
            searchNode = null;
        } else if (searchNode.right == null) {
            Node parent = searchNode.parent;
            if (searchNode.childSide == 'l') {
                //linkando parent ao proximo nó
                parent.left = searchNode.left;
            } else {
                parent.right = searchNode.left;
            }
            resize(searchNode.left, 'r');
        } else if (searchNode.left == null) {
            Node parent = searchNode.parent;
            if (searchNode.childSide == 'r') {
                //linkando parent ao proximo nó
                parent.right = searchNode.left;
            } else {
                parent.left = searchNode.right;
            }
            resize(searchNode.right, 'l');

            // se tiver os dois nó, va para a direita
        } else if (searchNode.right != null) {
            goDownDelete(searchNode.right); //seta closest node
            if (closestNode.childSide == 'l') {
                //checar apenas nó a direita pois nunca ira ter
                // a esquerta, se tivesse ele proprio seria o no mais proximo
                if (closestNode.right != null) {
                    //linka pai ao proximo filho
                    closestNode.parent.left = closestNode.right;
                    resize(closestNode.right, 'l');
                } else {
                    //nao tenho filhos, pai tb nao tera filhos
                    closestNode.parent.left = null;
                }
            } else {
                // se for filho a direita, tb nao tera filhos, consequentemente o pai tb nao(a direita)
                // unico cenario sendo filho direto a direita semm filhos proprios
                closestNode.parent.right = null;
            }
            if (searchNode == raiz) {
                raiz = closestNode;
            } else {
                //linkando pais aos filhos, caso nao seja o no raiz
                if (searchNode.childSide == 'r') {
                    searchNode.parent.right = closestNode;
                } else {
                    searchNode.parent.left = closestNode;
                }
            }
            //subtituindo closest no pelo deletado(filhos e posicao)
            closestNode.left = searchNode.left;
            closestNode.right = searchNode.right;
            closestNode.posicaoX = searchNode.posicaoX;
            closestNode.posicaoY = searchNode.posicaoY;
        }
    }
    public void resize(Node searchNode, char direction) {
        searchNode.posicaoY = searchNode.posicaoY - 1;
        if (direction == 'r') {
            searchNode.posicaoX = searchNode.posicaoX + searchNode.spacing;
        } else {
            searchNode.posicaoX = searchNode.posicaoX - searchNode.spacing;
        }
        if (searchNode.left != null) {
            resize(searchNode.left, direction);
        }
        if (searchNode.right != null) {
            resize(searchNode.right, direction);
        }
    }
    public void goDownDelete(Node searchNode) {
        //calcula valor do node mais proximo
        if (searchNode.value < closestNode.value && searchNode.value > toDelete.value) {
            closestNode = searchNode;
        }
        if (searchNode.left != null) {
            goDownDelete(searchNode.left);
        }
        if (searchNode.right != null) {
            goDownDelete(searchNode.right);
        }
    }
    public void inserir(Node node){
        int posicaoX = 40;
        int posicaoY = 1;
        int spacing = 8;

        if (raiz == null) {
            node.posicaoX = posicaoX;
            node.posicaoY = posicaoY;
            node.spacing = spacing;
            raiz = node;
            grid[posicaoY][posicaoX] = String.valueOf(node.value);
            return;
        }

        Node currentNode = raiz;
        goDown(currentNode, node, posicaoX, posicaoY, spacing);
    }


    public void goDown(Node currentNode, Node node, int posicaoX, int posicaoY, int spacing) {
        spacing--;
        if (node.value < currentNode.value) {
            posicaoX = posicaoX - spacing;
            posicaoY++;
            //se no a esquerta plota, senao continua
            if (currentNode.left != null) {
                goDown(currentNode.left, node, posicaoX, posicaoY, spacing);
            } else {
                node.posicaoX = posicaoX;
                node.posicaoY = posicaoY;
                node.spacing = spacing;
                currentNode.left = node;
                node.childSide = 'l';
                node.parent = currentNode;
            }
        } else if (node.value > currentNode.value) {
            posicaoX = posicaoX + spacing;
            posicaoY++;
            //se no a direita plota, senao continua
            if (currentNode.right != null) {
                goDown(currentNode.right, node, posicaoX, posicaoY, spacing);
            } else {
                node.posicaoX = posicaoX;
                node.posicaoY = posicaoY;
                node.spacing = spacing;
                currentNode.right = node;
                node.childSide = 'r';
                node.parent = currentNode;
            }
        } else {
            return;
        }
    }

    public void plotar(){
        if (raiz != null){
            Node current = raiz;
        } else {
            return;
        }
        goDownPlotar(raiz);
    }

    public void goDownPlotar(Node searchNode) {
        grid[searchNode.posicaoY][searchNode.posicaoX] = String.valueOf(searchNode.value);
        if (searchNode.left != null) {
            goDownPlotar(searchNode.left);
        }
        if (searchNode.right != null) {
            goDownPlotar(searchNode.right);
        }
    }

    public void print() {
        for (int y = 1; y < 10; y++) {
            for (int x = 1; x < 80; x++) {
                grid[y][x] = " ";
            }
        }
        plotar();
        for (int y = 1; y < 10; y++) {
            for (int x = 1; x < 80; x++) {
                System.out.print(grid[y][x] + " ");
            }
            System.out.println();
        }

    }
}

