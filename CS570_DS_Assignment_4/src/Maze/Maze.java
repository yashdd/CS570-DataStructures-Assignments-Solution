
// NAME - Yash Deepak Deshpande
// CWID - 20025089
package Maze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

import static java.util.Collections.min;


/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        if(x > maze.getNCols()-1 || y > maze.getNRows()-1 || x < 0 || y < 0){
            return false;
        }
        if(!maze.getColor(x,y).equals(NON_BACKGROUND)){
            return false;
        }
        if(x == maze.getNCols()-1 && y==maze.getNRows()-1){
            maze.recolor(x,y,PATH);
            return true;
        }
        maze.recolor(x,y,PATH);
        if(findMazePath(x+1,y) || findMazePath(x,y+1) || findMazePath(x-1,y) ||findMazePath(x,y-1)){
            return true;
        }else{
            maze.recolor(x,y,TEMPORARY);
            return false;
        }


    }


    // ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
        if (x > maze.getNCols() - 1 || y > maze.getNRows() - 1 || x < 0 || y < 0) {
            return;
        } else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return;
        } else if (x == maze.getNCols() - 1 || y == maze.getNRows() - 1) {
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> i = new ArrayList<>(trace);
            result.add(i);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
        } else {
            trace.push(new PairInt(x, y));
            maze.recolor(x, y, PATH);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }
    }
    public ArrayList<ArrayList<PairInt>>findAllMazePaths(int x, int y){
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0,0,result,trace);
        return result;

    }
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y){
        int i=0;
        int j = 0;
        int[] temp;
        int ind = 0;
        int min = 0;
        ArrayList<ArrayList<PairInt>> result = findAllMazePaths(x,y);
        System.out.print(result);
        if (result.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("No path found");
        }
        temp = new int[result.size()];
        ArrayList<PairInt> k;
        for(i=0;i<result.size();i++){
            k = result.get(i);
            temp[i] = k.size();

       }
        min = temp[0];
        for(j=0; j<temp.length; j++){
            if(temp[j]<min){
                min = temp[j];
                ind = j;
            }

        }
        return result.get(min);


    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
