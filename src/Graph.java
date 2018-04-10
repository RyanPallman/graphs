import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
    char[] names;
    int[][] matrix;

    public Graph(String inputPath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputPath))) {
            int count;
            String[] namesTemp = bufferedReader.readLine().split(",");
            names = new char[namesTemp.length];
            for (int i = 0; i < namesTemp.length; i++) {
                names[i] = namesTemp[i].charAt(0);
            }
            count = namesTemp.length;
            matrix = new int[count][count];
            for (int j = 0; j < count; j++) {
                String[] line = bufferedReader.readLine().split(",");
                for (int k = 0; k < count; k++) {
                    if (line[k].charAt(0) == '∞')
                        matrix[j][k] = Integer.MAX_VALUE;
                    else matrix[j][k] = Integer.parseInt(line[k]);
                }
            }
            // debugging - prints read in matrix
            print_matrix();
        } catch (IOException e) {
            System.out.println("File not found at: " + inputPath);
        }
    }

    public void warshall() {

    }

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

    private void print_header() {
        String out = "   ";
        for (char c : names)
            out += c + " ";
        System.out.println(out);
    }
}
