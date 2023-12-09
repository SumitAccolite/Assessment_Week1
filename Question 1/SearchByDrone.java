package org.example;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SearchByDrone {
    static final int dRow[] = {-1, 0, 1, 0};
    static final int dCol[] = {0, 1, 0, -1};

    public static void main(String[] args) {
        List<Pair> drones = new ArrayList<>();
        List<List<Integer>> grid = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of Rows:");
        int row = sc.nextInt();
        System.out.print("Enter the number of Columns:");
        int col = sc.nextInt();
        for (int i = 0; i < row; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                list.add(0);
            }
            grid.add(list);
        }
        //System.out.println(grid);
        //drones are 4 and we can put in grid
        int noOfDrones = 4;
        for (int i = 0; i < noOfDrones; i++) {
            System.out.print((i + 1) + "th Drone X Cordinate:");
            int droneX = sc.nextInt();
            System.out.print((i + 1) + "th Drone Y Cordinate:");
            int droneY = sc.nextInt();
            grid.get(droneX).set(droneY, 1);
            drones.add(new Pair(droneX, droneY));
        }
        Pair target;
        int targetX;
        int targetY;
        //specifying the range as the index starts from 0 to row or col-1
        System.out.print("Enter Target's X Coordinate from 0 to " + (row - 1) + " :");
        targetX = sc.nextInt();
        System.out.print("Enter Target's Y Coordinate from 0 to " + (col - 1) + " :");
        targetY = sc.nextInt();

        grid.get(targetX).set(targetY, 2);
        //passed to pair
        target = new Pair(targetX, targetY);
        //creatoing boolean array so that we can keep track of visited nodes
        List<List<Boolean>> visitedNodes = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            List<Boolean> list = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                list.add(false);
            }
            visitedNodes.add(list);
        }
        for (Pair drone : drones) {
            breadthFirstSearch(grid, visitedNodes, drone.x, drone.y, row, col, target);
            //System.out.println(visitedNodes);
            resetVisited(visitedNodes, row, col);

        }
        //System.out.println(visitedNodes);
    }

    public static void breadthFirstSearch(List<List<Integer>> grid, List<List<Boolean>> visited, int startX, int startY, int rows, int cols, Pair target) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(startX, startY));
        visited.get(startY).set(startX, true);
        Boolean targetFound = false;

        while (!queue.isEmpty()) {
            Pair cell = queue.poll();
            int x = cell.x;
            int y = cell.y;

            System.out.println("X axis: " + x + ", Y axis: " + y);

            if (x == target.x && y == target.y) {
                System.out.println("Target found!!");
                return;
            }

            for (int i = 0; i < 4; i++) {
                int adjX = x + dRow[i];
                int adjY = y + dCol[i];

                if (isValid(visited, adjX, adjY, rows, cols)) {
                    queue.add(new Pair(adjX, adjY));
                    visited.get(adjY).set(adjX, true);
                }
            }
        }

    }


    private static Boolean isValid(List<List<Boolean>> list, int x, int y, int row, int col) {
        return x >= 0 && y >= 0 && x < col && y < row && !list.get(y).get(x);
    }






    private static void resetVisited(List<List<Boolean>> visited, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited.get(i).set(j, false);
            }
        }
    }
}