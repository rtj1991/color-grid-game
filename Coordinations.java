class Coordinations {
    private int x;
    private int y;

    public Coordinations(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinations right() {
        return new Coordinations(x, y-1);
    }

    public Coordinations down() {
        return new Coordinations(x, y+1);
    }

    public Coordinations left() {
        return new Coordinations(x-1, y);
    }

    public Coordinations up() {
        return new Coordinations(x+1, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinations coord = (Coordinations) o;

        if (x != coord.x) return false;
        return y == coord.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = result + y;
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
