/*
 * Authors: Ethan Mitchell & Ryan Pallman
 * Date: 4/10/2018
 * Overview: Reads an input file that contains an adjacency matrix and stores it in a 2D array.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
    // vertex names
    private char[] names;
    // adjacency matrix
    private int[][] matrix;

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
                    else {
                        matrix[j][k] = Integer.parseInt(line[k]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found at: " + inputPath);
        }
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
