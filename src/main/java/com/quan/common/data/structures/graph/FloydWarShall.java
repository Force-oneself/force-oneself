package com.quan.common.data.structures.graph;

import java.util.Arrays;
import java.util.Scanner;

public class FloydWarShall {

    private int DistanceMatrix[][];
    private int numberOfVertices;//number of vertices in the graph
    public static final int INFINITY = 999;

    public FloydWarShall(int numberOfVertices) {
        //存储从源顶点到目标顶点的所有可能路径的距离值
        DistanceMatrix = new int[numberOfVertices + 1][numberOfVertices + 1];
        Arrays.fill(DistanceMatrix, 0);
        this.numberOfVertices = numberOfVertices;
    }

    // 计算从源顶点到目标顶点的所有距离

    public void floydWarShall(int AdjacencyMatrix[][]) {
        for (int source = 1; source <= numberOfVertices; source++) {
            for (int destination = 1; destination <= numberOfVertices; destination++) {
                DistanceMatrix[source][destination] = AdjacencyMatrix[source][destination];
            }
        }
        for (int intermediate = 1; intermediate <= numberOfVertices; intermediate++) {
            for (int source = 1; source <= numberOfVertices; source++) {
                for (int destination = 1; destination <= numberOfVertices; destination++) {
                    if (DistanceMatrix[source][intermediate] + DistanceMatrix[intermediate][destination]
                            < DistanceMatrix[source][destination])
                    // if the new distance calculated is less then the earlier shortest
                    // calculated distance it get replaced as new shortest distance
                    {
                        DistanceMatrix[source][destination] = DistanceMatrix[source][intermediate]
                                + DistanceMatrix[intermediate][destination];
                    }
                }
            }
        }
        for (int source = 1; source <= numberOfVertices; source++)
            System.out.print("\t" + source);
        System.out.println();
        for (int source = 1; source <= numberOfVertices; source++) {
            System.out.print(source + "\t");
            for (int destination = 1; destination <= numberOfVertices; destination++) {
                System.out.print(DistanceMatrix[source][destination] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String... arg) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        int numberOfVertices = scan.nextInt();
        int[][] adjacencyMatrix = new int[numberOfVertices + 1][numberOfVertices + 1];
        System.out.println("Enter the Weighted Matrix for the graph");
        for (int source = 1; source <= numberOfVertices; source++) {
            for (int destination = 1; destination <= numberOfVertices; destination++) {
                adjacencyMatrix[source][destination] = scan.nextInt();
                if (source == destination) {
                    adjacencyMatrix[source][destination] = 0;
                    continue;
                }
                if (adjacencyMatrix[source][destination] == 0) {
                    adjacencyMatrix[source][destination] = INFINITY;
                }
            }
        }
        System.out.println("The Transitive Closure of the Graph");
        FloydWarShall floydwarshall = new FloydWarShall(numberOfVertices);
        floydwarshall.floydWarShall(adjacencyMatrix);
        scan.close();
    }
}
