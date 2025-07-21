
package io.aobo;

import io.aobo.statemachine.leave.LeaveEvents;
import io.aobo.statemachine.leave.LeaveStates;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/start")
    public ResponseEntity<String> start() throws Exception {
        String leaveId = leaveService.start();
        return ResponseEntity.ok(leaveId);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<String> approve(@PathVariable String id) throws Exception {
        LeaveStates state = leaveService.sendEvent(id, LeaveEvents.APPROVE);
        return ResponseEntity.ok("State changed to: " + state);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<String> reject(@PathVariable String id) throws Exception {
        LeaveStates state = leaveService.sendEvent(id, LeaveEvents.REJECT);
        return ResponseEntity.ok("State changed to: " + state);
    }
}
