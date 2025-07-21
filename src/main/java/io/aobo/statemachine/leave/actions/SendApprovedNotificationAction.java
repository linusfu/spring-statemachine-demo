package io.aobo.statemachine.leave.actions;

import io.aobo.statemachine.leave.LeaveEvents;
import io.aobo.statemachine.leave.LeaveStates;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SendApprovedNotificationAction implements Action<LeaveStates, LeaveEvents> {

    @Override
    public void execute(StateContext<LeaveStates, LeaveEvents> context) {
        // 可以从上下文中获取业务数据（如请假单ID、申请人、审批人等）
        String userId = Optional.ofNullable(context.getMessageHeader("userId")).map(Object::toString).orElse("defaultUserId");
        String leaveId = Optional.ofNullable(context.getMessageHeader("leaveId")).map(Object::toString).orElse("defaultLeaveId");

        // 模拟发送通知逻辑
        System.out.println("📧 正在发送审批通过结果给申请人...");
        System.out.println("👤 申请人: " + userId);
        System.out.println("📝 请假单ID: " + leaveId);
        System.out.println("🔔 您的请假申请已通过");

        // 实际项目中可调用邮件服务、消息队列或远程接口来发送通知
        sendEmailToApprover(userId, leaveId);
    }

    // 示例方法：模拟发送邮件
    private void sendEmailToApprover(String userId, String leaveId) {
        System.out.println("📨 邮件已发送给申请人，用户：" + userId + "，请假单：" + leaveId);
    }
}
