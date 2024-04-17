public class Node {
    public int value;
    public int posicaoY;
    public int posicaoX;

    public int spacing;
    public Node parent = null;
    public char childSide;
    public Node left = null;
    public Node right = null;

    public Node(int value) {
        this.value = value;
    }


}
