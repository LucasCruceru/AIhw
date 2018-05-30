package algorithm.cnm;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        State initialState = new State (3, 3, Position.LEFT, 0, 0);
        SearchSolution search = new SearchSolution();
        State solution = search.exec(initialState);
        printSolution(solution);
    }

    private static void printSolution(State solution) {
        if (null == solution) {
            System.out.print("\nNo solution found.");
        } else {
            System.out.println("\nSolution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): ");
            List<State> path = new ArrayList<State>();
            State state = solution;
            while(null!=state) {
                path.add(state);
                state = state.getParentState();
            }

            int depth = path.size() - 1;
            for (int i = depth; i >= 0; i--) {
                state = path.get(i);
                if (state.isGoal()) {
                    System.out.print(state.toString());
                } else {
                    System.out.print(state.toString() + " -> ");
                }
            }
        }
    }
}
