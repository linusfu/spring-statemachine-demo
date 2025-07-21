
package io.aobo;

import io.aobo.statemachine.leave.LeaveEvents;
import io.aobo.statemachine.leave.LeaveStates;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final StateMachineFactory<LeaveStates, LeaveEvents> leaveFactory;
    private final StateMachinePersister<LeaveStates, LeaveEvents, String> persister;

    public LeaveStates sendEvent(String leaveId, LeaveEvents event) throws Exception {
        StateMachine<LeaveStates, LeaveEvents> sm = leaveFactory.getStateMachine(leaveId);
        persister.restore(sm, leaveId);
        Message<LeaveEvents> message = MessageBuilder.withPayload(event)
                .setHeader("userId", "ufl1szh")
                .setHeader("leaveId", leaveId)
                .build();
        Mono<Message<LeaveEvents>> messageMono = Mono.just(message);
        sm.sendEvent(messageMono).blockLast();
        persister.persist(sm, leaveId);
        return sm.getState().getId();
    }

    public String start() throws Exception {
        String leaveId = UUID.randomUUID().toString();
        StateMachine<LeaveStates, LeaveEvents> sm = leaveFactory.getStateMachine(leaveId);
        sm.startReactively().block();
        persister.persist(sm, leaveId);
        return leaveId;
    }
}
