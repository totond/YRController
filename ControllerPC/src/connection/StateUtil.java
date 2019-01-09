package connection;

import static controller.MainController.*;

public class StateUtil {
    public static int getNextState(int currentState){
        switch (currentState){
            case STATE_IDLE:
                return STATE_LISTENING;
            case STATE_LISTENING:
                return STATE_CONNECTED;
            case STATE_CONNECTED:
                return STATE_IDLE;
                default:return STATE_IDLE;
        }
    }
}
