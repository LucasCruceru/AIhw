package algorithm.cnm;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

enum Position {RIGHT, LEFT}

@Getter
@Setter
public class State {

    private int cannibalLeft;
    private int missionaryLeft;
    private int cannibalRight;
    private int missionaryRight;
    private Position boat;

    private State parentState;

    public State(int cannibalLeft, int missionaryLeft, Position boat,
                 int cannibalRight, int missionaryRight) {
        this.cannibalLeft = cannibalLeft;
        this.missionaryLeft = missionaryLeft;
        this.boat = boat;
        this.cannibalRight = cannibalRight;
        this.missionaryRight = missionaryRight;
    }

    public boolean isGoal() {
        return cannibalLeft == 0 && missionaryLeft == 0;
    }

    public boolean isValid() {
        if (missionaryLeft >= 0 && missionaryRight >= 0 && cannibalLeft >= 0 && cannibalRight >= 0
                && (missionaryLeft == 0 || missionaryLeft >= cannibalLeft)
                && (missionaryRight == 0 || missionaryRight >= cannibalRight)) {
            return true;
        }
        return false;
    }

    public List<State> generateSuccessors() {
        List<State> successors = new ArrayList<State>();
        if (boat == Position.LEFT) {
            testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 2, Position.RIGHT,
                    cannibalRight, missionaryRight + 2));
            testAndAdd(successors, new State(cannibalLeft - 2, missionaryLeft, Position.RIGHT,
                    cannibalRight + 2, missionaryRight));
            testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft - 1, Position.RIGHT,
                    cannibalRight + 1, missionaryRight + 1));
            testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 1, Position.RIGHT,
                    cannibalRight, missionaryRight + 1));
            testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft, Position.RIGHT,
                    cannibalRight + 1, missionaryRight));
        } else {
            testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 2, Position.LEFT,
                    cannibalRight, missionaryRight - 2));
            testAndAdd(successors, new State(cannibalLeft + 2, missionaryLeft, Position.LEFT,
                    cannibalRight - 2, missionaryRight));
            testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft + 1, Position.LEFT,
                    cannibalRight - 1, missionaryRight - 1));
            testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 1, Position.LEFT,
                    cannibalRight, missionaryRight - 1));
            testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft, Position.LEFT,
                    cannibalRight - 1, missionaryRight));
        }
        return successors;
    }

    private void testAndAdd(List<State> successors, State newState) {
        if (newState.isValid()) {
            newState.setParentState(this);
            successors.add(newState);
        }
    }

    @Override
    public String toString() {
        if (boat == Position.LEFT) {
            return "(" + cannibalLeft + "," + missionaryLeft + ",L,"
                    + cannibalRight + "," + missionaryRight + ")";
        } else {
            return "(" + cannibalLeft + "," + missionaryLeft + ",R,"
                    + cannibalRight + "," + missionaryRight + ")";
        }
    }

}