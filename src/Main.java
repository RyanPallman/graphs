public class Main {
    public static void main(String[] args) {
        Graph prims = new Graph("input.txt");
        Graph warshall = new Graph("warshall.txt");
        warshall.warshall();
    }
}
