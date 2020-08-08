package cn.lliiooll.opq.core.managers.event.data;

import cn.lliiooll.opq.core.data.group.Group;
import cn.lliiooll.opq.core.managers.event.Event;
import cn.lliiooll.opq.core.managers.event.HandlerList;
import lombok.Getter;

/**
 * 群组退出
 */
public class GroupMemberKickEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    @Getter
    private final long id;
    @Getter
    private final long adminId;
    @Getter
    private final Group group;

    public GroupMemberKickEvent(long adminId, long id, Group group) {
        this.id = id;
        this.adminId = adminId;
        this.group = group;
    }


    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
