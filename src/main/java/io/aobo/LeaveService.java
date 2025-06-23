
package io.aobo;

import io.aobo.statemachine.leave.LeaveEvents;
import io.aobo.statemachine.leave.LeaveStates;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final StateMachineFactory<LeaveStates, LeaveEvents> leaveFactory;
    private final StateMachinePersister<LeaveStates, LeaveEvents, String> persister;

    public LeaveStates sendEvent(String leaveId, LeaveEvents event) throws Exception {
        StateMachine<LeaveStates, LeaveEvents> sm = leaveFactory.getStateMachine(leaveId);
        sm.start();
        persister.restore(sm, leaveId);
        sm.sendEvent(MessageBuilder.withPayload(event).build());
        persister.persist(sm, leaveId);
        return sm.getState().getId();
    }
}
