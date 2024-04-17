public class Main {
    public static void main(String[] args) {
        Node node = new Node(28);
        Node node2 = new Node(20);
        Node node3 = new Node(25);
        Node node4 = new Node(34);
        //Node node5 = new Node(36);
        Node node6 = new Node(12);
        Node node7 = new Node(4);
        Node node8 = new Node(8);
        //Node node12 = new Node(37);
        Node node13 = new Node(31);
        Node node14 = new Node(33);
        Node node15 = new Node(16);
        Arvore arvore = new Arvore();
        arvore.inserir(node);
        arvore.inserir(node2);
//        arvore.inserir(node3);
        arvore.inserir(node4);
        arvore.inserir(node6);
        arvore.inserir(node7);
        arvore.inserir(node8);
        arvore.inserir(node13);
        arvore.inserir(node14);
        arvore.inserir(node15);
//        arvore.delete(node);
        //arvore.delete(node11);
        //arvore.delete(node2);
        arvore.print();
        arvore.delete(node);
        arvore.print();
    }
}