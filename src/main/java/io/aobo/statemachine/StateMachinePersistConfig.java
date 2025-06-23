
package io.aobo.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.jpa.JpaStateMachineRepository;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

@Configuration
@EnableJpaRepositories
public class StateMachinePersistConfig {

    @Bean
    public StateMachineRuntimePersister<?, ?, String> runtimePersister(JpaStateMachineRepository repository) {
        return new JpaPersistingStateMachineInterceptor<>(repository);
    }

    @Bean
    public StateMachinePersister<?, ?, String> persister(StateMachineRuntimePersister<?, ?, String> runtimePersister) {
        return new DefaultStateMachinePersister<>(runtimePersister);
    }
}
