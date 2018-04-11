import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    // input variables
    // vertex names
    char[] names;
    // adjacency matrix
    int[][] matrix;

    private Set<Vertex> vertices;
    private Set<Edge> edges;
    private Map<Vertex, Set<Edge>> adjacencyList;

    public Graph(String inputPath) {
        vertices = new HashSet<Vertex>();
        edges = new HashSet<Edge>();
        adjacencyList = new HashMap<Vertex, Set<Edge>>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputPath))) {
            int count;
            String[] namesTemp = bufferedReader.readLine().split(",");
            names = new char[namesTemp.length];
            for (int i = 0; i < namesTemp.length; i++) {
                names[i] = namesTemp[i].charAt(0);
                // creates a new vertex with the characters from the first row and adds it to the set of vertices
                vertices.add(new Vertex(names[i]));
            }
            count = namesTemp.length;
            matrix = new int[count][count];
            // if we need the diagonal to be zero just check for when k == j and set it to zero
            for (int j = 0; j < count; j++) {
                String[] line = bufferedReader.readLine().split(",");
                for (int k = 0; k < count; k++) {
                    if (line[k].charAt(0) == '∞')
                        matrix[j][k] = Integer.MAX_VALUE;
                    else {
                        matrix[j][k] = Integer.parseInt(line[k]);
                        addEdge(names[j], names[k], matrix[j][k]);
                    }
                }
            }
            // debugging - prints read in matrix
            //print_matrix();
            // debugging - lists all edges
            //for (Edge e : edges)
            //    System.out.println("Edge(" + e.getVertex1().getLabel() + ", " + e.getVertex2().getLabel() + ") Weight: " + e.getWeight());
        } catch (IOException e) {
            System.out.println("File not found at: " + inputPath);
        }
    }

    public void addEdge(char v1, char v2, int weight) {
        Edge e = new Edge(new Vertex(v1), new Vertex(v2), weight);
        // checking for duplicate edges
        if (!edges.add(e))
            return;
        // checking if the adjacency list has existing values for each vertex
        adjacencyList.putIfAbsent(e.getVertex1(), new HashSet<Edge>());
        adjacencyList.putIfAbsent(e.getVertex2(), new HashSet<Edge>());
        // adding the edge to the adjacency list
        adjacencyList.get(e.getVertex1()).add(e);
        adjacencyList.get(e.getVertex2()).add(e);
    }

    // algorithms

    public void warshall() {
        // using d to align with pseudocode
        int[][] d = new int[names.length][names.length];
        // setting each element in the two dimensional array to "infinity"
        for (int[] e : d) {
            for (int i = 0; i < e.length; i++) {
                e[i] = Integer.MAX_VALUE;
            }
        }
        // setting edges
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != Integer.MAX_VALUE) {
                    d[i][j] = matrix[i][j];
                }
            }
        }
        // prints the read in matrix
        System.out.println("Read in matrix for Floyd-Warshall algorithm:");
        print_matrix(d);
        System.out.println();
        // setting the diagonal to zero in d and weight of edges
        for (int j = 0; j < d.length; j++) {
            for (int k = 0; k < d[j].length; k++)
                if (j == k)
                    d[j][k] = 0;
        }
        // prints the matrix after the diagonal of zeroes is set
        // print_matrix(d);

        // actual algorithm
        for (int k = 0; k < names.length; k++) {
            for (int i = 0; i < names.length; i++) {
                for (int j = 0; j < names.length; j++) {
                    if (d[i][k] != Integer.MAX_VALUE && d[k][j] != Integer.MAX_VALUE) {
                        if (d[i][j] > d[i][k] + d[k][j])
                            d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
            // prints out each iteration
            print_matrix(d);
            System.out.println();
        }
    }

    // printing methods

    public void print_matrix() {
        print_header();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(names[i] + "  ");
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == Integer.MAX_VALUE)
                    System.out.print("\u221e ");
                else System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    // overloading
    public void print_matrix(int[][] input) {
        print_header();
        for (int i = 0; i < input.length; i++) {
            System.out.print(names[i] + "  ");
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == Integer.MAX_VALUE)
                    System.out.print("\u221e ");
                else System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void print_header() {
        String out = "   ";
        for (char c : names)
            out += c + " ";
        System.out.println(out);
    }

    // accessors
    // returns the amount of vertices
    public int getGraphSize() {
        return names.length;
    }
}
