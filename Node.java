class Node {
    private Coordinations coordinate;
    private char color;
    private int id;

    public Node(int id, int x, int y, char color) {
        this.color = color;
        this.coordinate = new Coordinations(x, y);
        this.id = id;
    }

    public Coordinations getCoordinate() {
        return coordinate;
    }

    public char getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (color != node.color) return false;
        return coordinate.equals(node.coordinate);
    }

    @Override
    public int hashCode() {
        int result = coordinate.hashCode();
        result = result + (int) color;
        return result;
    }

    @Override
    public String toString() {
        return " [" + coordinate.toString() + ", " + color + "] ";
    }
}
