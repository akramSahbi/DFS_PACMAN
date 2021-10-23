import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static void dfs(int r, int c, int pacman_r, int pacman_c, int food_r, int food_c, String [] grid){
        //Your logic here
        Point pacman = new Point(pacman_r, pacman_c, 'P');
        Point food = new Point(food_r, food_c, '.');
        Point[][] matrix = fillGridPoints(grid);
        //printGrid(matrix);

        Stack<Point> fringe = new Stack<>();

        List<Point> explored = new ArrayList<>();
        fringe.push(pacman);
        boolean solution = false;

        while (!fringe.isEmpty()) {
            Point currentPoint = fringe.pop();
            //System.out.println(currentPoint);

            explored.add(currentPoint);
            if (matrix[currentPoint.getX()][currentPoint.getY()].getCell() == food.getCell()) {
                solution = true;
                break;
            }
            //matrix[currentPoint.getX()][currentPoint.getY()].setCell('P');
            //printGrid(matrix);
            List<Point> possibleMoves = listPossiblePointMoves(currentPoint, matrix);
            for (int i = 0; i < possibleMoves.size(); i ++) {
                Point p = possibleMoves.get(i);
                    if (!fringe.contains(p) && ! explored.contains(p)) {
                        fringe.push(p);
                    }

            }

        }
        if (solution) {
            System.out.println(String.valueOf(explored.size()));
            explored.stream().forEach(point -> System.out.println(point));
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        int pacman_r = in.nextInt();
        int pacman_c = in.nextInt();

        int food_r = in.nextInt();
        int food_c = in.nextInt();

        int r = in.nextInt();
        int c = in.nextInt();

        String grid[] = new String[r];

        for(int i = 0; i < r; i++) {
            grid[i] = in.next();
        }


        dfs( r, c, pacman_r, pacman_c, food_r, food_c, grid);
    }

    private static List<Point> listPossiblePointMoves(Point pacmanStartPoint, Point[][] matrix) {
        List<Point> possibleMoves = new ArrayList<>();
        int maxRows = matrix.length;
        int maxColumns = matrix[0].length;

        if (pacmanStartPoint.getX() - 1 >= 0) {
            // can go up
            if (matrix[pacmanStartPoint.getX() - 1][pacmanStartPoint.getY()].getCell() != '%') {
                possibleMoves.add(new Point(pacmanStartPoint.getX() - 1, pacmanStartPoint.getY()));
            }
        }

        if (pacmanStartPoint.getY() - 1 >= 0) {
            // can go left
            if (matrix[pacmanStartPoint.getX()][pacmanStartPoint.getY() - 1].getCell() != '%') {
                possibleMoves.add(new Point(pacmanStartPoint.getX(), pacmanStartPoint.getY() - 1));
            }
        }

        if (pacmanStartPoint.getX() + 1 < maxRows) {
            // can go down
            if (matrix[pacmanStartPoint.getX() + 1][pacmanStartPoint.getY()].getCell() != '%') {
                possibleMoves.add(new Point(pacmanStartPoint.getX() + 1, pacmanStartPoint.getY()));
            }
        }

        if (pacmanStartPoint.getY() + 1 < maxColumns) {
            // can go right
            if (matrix[pacmanStartPoint.getX()][pacmanStartPoint.getY() + 1].getCell() != '%') {
                possibleMoves.add(new Point(pacmanStartPoint.getX(), pacmanStartPoint.getY() + 1));
            }
        }

        return possibleMoves;
    }

    static Point[][] fillGridPoints(String[] grid) {
        Point[][] matrix = new Point[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            String line = grid[i];
            for (int j = 0; j < line.length(); j++) {
                int x = i;
                int y = j;
                char cell = line.charAt(j);
                Point point = new Point(x, y, cell);
                matrix[i][j] = point;
            }
        }
        return matrix;
    }

    static void printGrid(Point[][] matrix) {
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j].getCell());
            }
            System.out.println();
        }
    }

    static class Point {
        private int x;
        private int y;
        private char cell;

        public Point() {

        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, char cell) {
            this.x = x;
            this.y = y;
            this.cell = cell;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public char getCell() {
            return cell;
        }

        public void setCell(char cell) {
            this.cell = cell;
        }

        @Override
        public String toString() {
            return "" + x + " " + y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;
            return cell == point.cell;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + (int) cell;
            return result;
        }
    }
}
