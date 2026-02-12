import java.util.Arrays;

public class Solver {
    int[][] curMaze;
    char[][] solution;
    int width;
    int height;
    boolean foundGoal = false;
    public Solver(){

    }
    public void Solve(int[][] maze){
        if(maze == null || maze.length == 0){
            System.out.println("Maze is empty or null");
            return;
        }

        this.width = maze[0].length;
        this.height = maze.length;
        for (int[] row : maze) {
            if(row.length != this.width){
                System.out.println("Inconsistent Width.");
                return;
            }
        }

        foundGoal = false;
        this.curMaze = maze;
        solution = new char[height][width];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                solution[y][x] = '·';
            }
        }

        Solve(0, 0);
        if(!foundGoal){
            System.out.println("No solution found");
            return;
        }

        for (char[] row : solution) {
            System.out.println(Arrays.toString(row).replace(',', ' ').replace('[', ' ').replace(']', ' ').replace('-', '·'));
        }
    }

    public void Solve(int curX, int curY){
        // if Out Of Bounds, or Goal has already been found
        if(curY >= height || curX >= width || curX < 0 || curY < 0 || foundGoal) {
            return;
        }
        // if in wall
        if(curMaze[curY][curX] == 1){
            this.solution[curY][curX] = '·';
            return;
        }

        if(curX == width - 1 && curY == height - 1){
            System.out.println(curX + " " + curY + ": " + "Found the Goal");
            foundGoal = true;
            this.solution[curY][curX] = 'o';
            return;
        }

        // If square has already been evaluated
        if(solution[curY][curX] != '·'){
            return;
        }

        // Mark tile as seen
        solution[curY][curX] = '-';

        // Solve Adjacent Squares, check for solution.
        Solve(curX + 1, curY);
        Solve(curX, curY + 1);
        Solve(curX - 1, curY);
        Solve(curX, curY - 1);
        
        if(foundGoal) {
            solution[curY][curX] = 'o';
        } else {
            solution[curY][curX] = '-';
        }
    }

    public static void main(String[] args) {
        Solver solver = new Solver();

        test(solver);
    }

    public static void test(Solver solver){
        // ===== Maze Test Data Pack =====

        int[][] maze_trivial = {
            {0}
        };

        int[][] maze_open_3x3 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        int[][] maze_corridor = {
            {0, 0, 0, 0, 0}
        };

        int[][] maze_zigzag = {
            {0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0}
        };

        int[][] maze_dead_ends = {
            {0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1},
            {0, 0, 0, 0, 1},
            {0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}
        };

        int[][] maze_no_solution = {
            {0, 1, 0},
            {1, 1, 0},
            {0, 1, 0}
        };

        int[][] maze_start_blocked = {
            {1, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        int[][] maze_goal_blocked = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 1}
        };

        int[][] maze_spiral = {
            {0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 0}
        };

        int[][] maze_large = {
            {0,0,1,0,0,0,1,0,0,0},
            {1,0,1,0,1,0,1,0,1,0},
            {0,0,0,0,1,0,0,0,1,0},
            {0,1,1,1,1,1,1,0,1,0},
            {0,0,0,0,0,0,0,0,1,0},
            {0,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,0}
        };
        System.out.println();
        System.out.println("Testing: Base Case");
        System.out.println();
        solver.Solve(maze_trivial);
        System.out.println();
        System.out.println("Testing: Open 3x3");
        System.out.println();
        solver.Solve(maze_open_3x3);
        System.out.println();
        System.out.println("Testing: Long Corridor");
        System.out.println();
        solver.Solve(maze_corridor);
        System.out.println();
        System.out.println("Testing: 5x5 ZigZag");
        System.out.println();
        solver.Solve(maze_zigzag);
        System.out.println();
        System.out.println("Testing: Dead Ends");
        System.out.println();
        solver.Solve(maze_dead_ends);
        System.out.println();
        System.out.println("Testing: No solution");
        System.out.println();
        solver.Solve(maze_no_solution);
        System.out.println();
        System.out.println("Testing: Start blocked");
        System.out.println();
        solver.Solve(maze_start_blocked);
        System.out.println();
        System.out.println("Testing: Goal blocked");
        System.out.println();
        solver.Solve(maze_goal_blocked);
        System.out.println();
        System.out.println("Testing: Spiral");
        System.out.println();
        solver.Solve(maze_spiral);
        System.out.println();
        System.out.println("Testing: Large");
        System.out.println();
        solver.Solve(maze_large);

    }
}
