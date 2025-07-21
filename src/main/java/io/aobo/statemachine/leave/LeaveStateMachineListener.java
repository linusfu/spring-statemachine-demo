package io.aobo.statemachine.leave;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
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
    public void stateEntered(State<LeaveStates, LeaveEvents> state) {
        System.out.println("[监听器] 进入状态: " + state.getId());
    }

    @Override
    public void stateExited(State<LeaveStates, LeaveEvents> state) {
        System.out.println("[监听器] 状态结束: " + state.getId());
    }

    @Override
    public void eventNotAccepted(Message<LeaveEvents> event) {
        System.err.println("[监听器] 事件未被接受: " + event.getPayload());
    }

    @Override
    public void stateMachineError(StateMachine<LeaveStates, LeaveEvents> stateMachine, Exception exception) {
        System.out.println("❌ 状态机发生异常");
        System.out.println("❌ 异常状态机: " + stateMachine);
        System.out.println("❌ 异常信息: " + exception.getMessage());
        throw new RuntimeException(exception);
    }
}
