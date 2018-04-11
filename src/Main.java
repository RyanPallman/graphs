/*
 * Authors: Ethan Mitchell & Ryan Pallman
 * Date: 4/10/2018
 * Overview: Reads in the specified files and runs the algorithms.
 */

public class Main {
    public static void main(String[] args) {
        Graph warshall = new Graph("warshall.txt");
        warshall.warshall();
        Graph primGraph = new Graph("input.txt");
        MST mst = new MST(primGraph.getMatrix());
        mst.primsAlgo(0);
        Graph kruuskalGraph = new Graph("input.txt");
        mst = new MST(kruuskalGraph.getMatrix());
        mst.kruskalsAlgo(0);
    }
}
