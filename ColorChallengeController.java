import java.util.*;


public class ColorChallengeController {

//    number of colors
    public static final char[] COLORS = {'R', 'B', 'W', 'G'};

    private HashMap<Coordinations, Node> hashMap;

    private int column;
    private int row;

    public void nodeSide(int cols, int rows) {
        this.column = cols;
        this.row = rows;

//        random generation
        Random random = new Random();
        this.hashMap = new HashMap<>();
        for (int i = 0; i < cols*rows ; i++ ) {
            int x = i % cols;
            int y = (int) Math.floor(i/cols);
            this.hashMap.put(new Coordinations(x, y), new Node(i, x, y, COLORS[random.nextInt(4)]));
        }
    }

    public Node getNode(int x, int y) {
        return this.hashMap.get(new Coordinations(x, y));
    }

    public void randomNode() {
        for (int y = 0; y < row; y++) {
            for(int x = 0; x < column; x++) {
                if(x == this.column - 1 ) {
                    System.out.println(getNode(x, y).getColor());
                } else {
                    System.out.print(getNode(x, y).getColor() + ", ");
                }
            }
        }
    }

//    Neighbour/Adjacent nodes
    private List<Node> findNearNodes(Node n, Block block) {
        List<Node> nodes = new ArrayList<>();
        Coordinations coordinate = n.getCoordinate();
        Node north = this.hashMap.get(coordinate.right());
        if (north != null && north.getColor() == n.getColor() && !block.hasNode(north)) {
            nodes.add(north);
        }
        Node south = this.hashMap.get(coordinate.down());
        if (south != null && south.getColor() == n.getColor() && !block.hasNode(south)) {
            nodes.add(south);
        }
        Node east = this.hashMap.get(coordinate.up());
        if (east != null && east.getColor() == n.getColor() && !block.hasNode(east)) {
            nodes.add(east);
        }
        Node west = this.hashMap.get(coordinate.left());
        if (west != null && west.getColor() == n.getColor() && !block.hasNode(west)) {
            nodes.add(west);
        }
        return nodes;
    }

    public Block getNextBlock(int x, int y) {
        Coordinations start = new Coordinations(x, y);
        Node startNode = this.hashMap.get(start);
        Block block = new Block(startNode.getColor());
        block.addNode(startNode);

        LinkedList<Node> nodesToVisit = new LinkedList<>();
        nodesToVisit.addAll(findNearNodes(startNode, block));

        while(!nodesToVisit.isEmpty()) {
            Node nextNode = nodesToVisit.remove();
            block.addNode(nextNode);
            nodesToVisit.addAll(findNearNodes(nextNode, block));
        }

        return block;
    }

    public Block getLargestCollection() {
        Set<Coordinations> allCoords = new HashSet<>(this.hashMap.keySet());
        List<Block> allBlocks = new ArrayList<>();
        while(!allCoords.isEmpty()) {
            Coordinations coord = allCoords.iterator().next();
            Block newBlock = getNextBlock(coord.getX(), coord.getY());
            allBlocks.add(newBlock);
            allCoords.removeAll(newBlock.allCoords());
        }
        Collections.sort(allBlocks);
        return allBlocks.size() > 0 ? allBlocks.get(0) : null;
    }

    public void printMostUsedBlock(Block block) {
        for (int y = 0; y < row; y++) {
            for(int x = 0; x < column; x++) {
                Node n = getNode(x, y);
                char color = block.hasNode(n) ? n.getColor() : ' ';
                if(x == this.column - 1 ) {
                    System.out.println("");
                } else {
                    System.out.print(color + ", ");
                }
            }
        }
    }

    public static void main(String[] args) {

        int WIDTH = 10;
        int HEIGHT = 12;

        ColorChallengeController challenge = new ColorChallengeController();
        challenge.nodeSide(WIDTH, HEIGHT);
        challenge.randomNode();

        Block block = challenge.getLargestCollection();

        if (block != null) {
            System.out.println();
            System.out.println();
            System.out.println("<===== largest connecting block  =====>");
            System.out.println();
            challenge.printMostUsedBlock(block);
        }
    }


}