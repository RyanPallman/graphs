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
            for (int[] arr : matrix) {
                for (int i : arr) {
                    if (i == Integer.MAX_VALUE)
                        System.out.print("∞,");
                    else System.out.print(i + ",");
                }
                System.out.println("");
            }
        } catch (IOException e) {
            System.out.println("File not found at: " + inputPath);
        }
    }
}
