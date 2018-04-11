/*
 * Authors: Ethan Mitchell & Ryan Pallman
 * Date: 4/10/2018
 * Overview: Reads in the specified files and runs the algorithms.
 */

public class Main {
    public static void main(String[] args) {
        Graph warshall = new Graph("warshall.txt");
        warshall.warshall();
    }
}
