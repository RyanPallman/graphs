/*
 * Authors: Ethan Mitchell, Partner: Ryan Pallman
 * Date: 4/10/2018
 * Overview: Create a minimum spanning tree using both Prims and
 * Kruskal's algorithms
 */
//package graphAlgorithms;

import java.util.PriorityQueue;

class MST {
    public static final int INF = Integer.MAX_VALUE;
    private int primsTree[][];                          //Holds the edges for prims algorithm
    private int krusTree[][];
    PriorityQueue<Vertice> mstQ = new PriorityQueue<>();
    private int verCheck[];                       //Holds vertices already in the tree
    int matrix[][];                                //Adjacency matrix
    int count = 0;

    public MST(int matrix[][]) {
        this.matrix = matrix;
    }

    public void primsAlgo(int start) {
        //add the starting vertex to the tree
        primsTree = new int[2][matrix.length - 1];

        //initialize the verCheck array
        verCheck = new int[matrix.length];
        for (int i = 0; i < verCheck.length; i++) {
            verCheck[i] = INF;
        }

        //Put adjacent vertices to start in the queue
        findAdjacent(start);

        //Put the start vertex in the check array
        verCheck[0] = start;

        while (mstQ.size() > 0) {
            Vertice check = mstQ.poll();
            //is the key in the tree
            boolean flag = true;
            int i = 0;
            while (flag) {
                //All vertices on the tree have been checked so add to the tree
                if (verCheck.length == i) {
                    if (count == primsTree[0].length) {
                        primsTree[0][count] = check.connect;
                        primsTree[1][count] = check.key;
                        flag = false;
                        i = 0;
                        mstQ.clear();
                    } else {
                        primsTree[0][count] = check.connect;
                        primsTree[1][count] = check.key;
                        flag = false;
                        i = 0;
                        verCheck[count + 1] = check.key;
                        findAdjacent(check.key);
                        count++;
                    }

                }
                //Yes, then don't add to the tree
                if (verCheck[i] == check.key) {
                    flag = false;
                }
                //No, keep checking
                else {
                    i++;
                }
            }
        }
        //Print the results
        System.out.println("Prim's MST edges:");
        printResults(primsTree);
    }

    public void kruskalsAlgo(int start) {
        //put all edges in the queue
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                if (matrix[i][j] < INF) {
                    mstQ.add(new Vertice(matrix[i][j], i, j));
                }
            }
        }

        //Check variables
        int edges = 0;
        int exCheck = 0;

        //Kind of a disjoin set
        Set dis = new Set();

        //initialize krusTree
        krusTree = new int[2][matrix.length - 1];


        while (edges < matrix.length - 1 && exCheck < matrix.length) {
            Vertice check = mstQ.poll();
            //If the sets are the same then join
            if (check != null && dis.find(check.key) != dis.find(check.connect)) {
                //Join
                dis.join(check.key, check.connect);
                //Add to the tree
                krusTree[0][edges] = check.key;
                krusTree[1][edges] = check.connect;
                edges++;
            }
            exCheck++;
        }

        //Print the results
        System.out.println("Kruskal’s Algoritm Edges:");
        printResults(krusTree);
    }


    //Find adjacent vertices
    private void findAdjacent(int location) {
        for (int i = 0; i < matrix[location].length; i++) {
            if (matrix[location][i] < INF) {
                //Check if any vertices are already in the tree
                boolean flag = true;
                int j = 0;
                while (flag) {
                    //All vertices on the tree have been checked so add to the queue
                    if (verCheck.length == j) {
                        mstQ.add(new Vertice(matrix[location][i], i, location));
                        flag = false;
                        j = 0;
                    }
                    //Yes, then don't add to the queue
                    if (verCheck[j] == i) {
                        flag = false;
                    }
                    //No, keep checking
                    else {
                        j++;
                    }
                }
            }
        }
    }

    //Print results for the MST algorithms
    public void printResults(int[][] toPrint) {
        for (int i = 0; i < toPrint[0].length; i++) {
            System.out.printf("%c <--> %c\n", inttoChar(toPrint[0][i]), inttoChar(toPrint[1][i]));
        }
        System.out.println();
    }

    //Returns char for the actual vertex
    private char inttoChar(int x) {
        switch (x) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            case 7:
                return 'H';
            case 8:
                return 'I';
            case 9:
                return 'J';
            case 10:
                return 'K';
            case 11:
                return 'L';
            case 12:
                return 'M';
            case 13:
                return 'N';
            case 14:
                return 'O';
            case 15:
                return 'P';
            case 16:
                return 'Q';
            case 17:
                return 'R';
            case 18:
                return 'S';
            case 19:
                return 'T';
            case 20:
                return 'U';
            case 21:
                return 'V';
            case 22:
                return 'W';
            case 23:
                return 'X';
            case 24:
                return 'Y';
            case 25:
                return 'Z';
            default:
                return ' ';
        }
    }
}
                       