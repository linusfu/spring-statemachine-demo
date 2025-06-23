
package io.aobo.statemachine.leave;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachineFactory(name = "leaveStateMachineFactory")
@RequiredArgsConstructor
public class LeaveStateMachineConfig extends EnumStateMachineConfigurerAdapter<LeaveStates, LeaveEvents> {

    private final LeaveStateMachineListener listener;

    @Override
    public void configure(StateMachineConfigurationConfigurer<LeaveStates, LeaveEvents> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener);
    }

    @Override
    public void configure(StateMachineStateConfigurer<LeaveStates, LeaveEvents> states) throws Exception {
        states.withStates()
                .initial(LeaveStates.SUBMITTED)
                .state(LeaveStates.APPROVED)
                .state(LeaveStates.REJECTED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<LeaveStates, LeaveEvents> transitions) throws Exception {
        transitions
                .withExternal().source(LeaveStates.SUBMITTED).target(LeaveStates.APPROVED).event(LeaveEvents.APPROVE)
                .and()
                .withExternal().source(LeaveStates.SUBMITTED).target(LeaveStates.REJECTED).event(LeaveEvents.REJECT);
    }
}
