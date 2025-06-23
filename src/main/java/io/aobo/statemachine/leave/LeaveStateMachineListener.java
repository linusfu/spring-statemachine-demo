package io.aobo.statemachine.leave;

import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

/**
 * @author Linus FU
 * @since 6/23/2025 4:31 PM
 */
@Component
public class LeaveStateMachineListener extends StateMachineListenerAdapter<LeaveStates, LeaveEvents> {
    @Override
    public void stateChanged(State<LeaveStates, LeaveEvents> from, State<LeaveStates, LeaveEvents> to) {
        System.out.println("[监听器] 状态变更: " + from + " -> " + to);
    }

    @Override
    public void eventNotAccepted(Message<LeaveEvents> event) {
        System.err.println("[监听器] 事件未被接受: " + event.getPayload());
    }
}
