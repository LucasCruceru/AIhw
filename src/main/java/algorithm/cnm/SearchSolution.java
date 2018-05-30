package algorithm.cnm;

import java.util.List;

public class SearchSolution {

    public State exec(State initialState) {
        int limit = 20;
        return solve(initialState, limit);
    }

    private State solve(State state, int limit) {
        if (state.isGoal()) {
            return state;
        } else if (limit == 0) {
            return null;
        } else {
            List<State> successors = state.generateSuccessors();
            for (State child : successors) {
                State result = solve(child, limit - 1);
                if (null != result) {
                    return result;
                }
            }
            return null;
        }
    }
}
