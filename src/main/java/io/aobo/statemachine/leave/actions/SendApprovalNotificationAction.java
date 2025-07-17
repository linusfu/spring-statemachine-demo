package io.aobo.statemachine.leave.actions;

import io.aobo.statemachine.leave.LeaveEvents;
import io.aobo.statemachine.leave.LeaveStates;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * 在请假申请提交后发送审批通知给审批人
 */
@Component
public class SendApprovalNotificationAction implements Action<LeaveStates, LeaveEvents> {

    @Override
    public void execute(StateContext<LeaveStates, LeaveEvents> context) {
        // 可以从上下文中获取业务数据（如请假单ID、申请人、审批人等）
        String userId = (String) context.getExtendedState().getVariables().get("userId");
        String leaveId = (String) context.getExtendedState().getVariables().get("leaveId");

        // 模拟发送通知逻辑
        System.out.println("📧 正在发送审批通知给审批人...");
        System.out.println("👤 申请人: " + userId);
        System.out.println("📝 请假单ID: " + leaveId);
        System.out.println("🔔 请审批人尽快处理该请假申请");

        // 实际项目中可调用邮件服务、消息队列或远程接口来发送通知
        sendEmailToApprover(userId, leaveId);
    }

    // 示例方法：模拟发送邮件
    private void sendEmailToApprover(String userId, String leaveId) {
        System.out.println("📨 邮件已发送给审批人，用户：" + userId + "，请假单：" + leaveId);
    }
}
