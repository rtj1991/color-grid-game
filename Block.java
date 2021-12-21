import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Block implements Comparable<Block> {

    private final char color;


    private final Set<Node> nodes;

    public Block(char color) {
        this.color = color;
        nodes = new HashSet<>();
    }


    public Set<Coordinations> allCoords() {
        return nodes.stream().map(n -> n.getCoordinate()).collect(Collectors.toSet());
    }

    public boolean addNode(Node node) {
        if (node != null && !nodes.contains(node)
                && node.getColor() == this.color) {
            return nodes.add(node);
        }
        return false;
    }

    public boolean hasNode(Node node) {
        if (node == null)
            return false;
        return nodes.stream().anyMatch(n -> n.getId() == node.getId());
    }

    public int size() {
        return nodes.size();
    }


    @Override
    public int compareTo(Block o) {
        return o.size() - this.size();
    }
}
