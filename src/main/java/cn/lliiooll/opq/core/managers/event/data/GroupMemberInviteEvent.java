package cn.lliiooll.opq.core.managers.event.data;

import cn.lliiooll.opq.core.data.group.Group;
import cn.lliiooll.opq.core.data.user.Member;
import cn.lliiooll.opq.core.managers.event.Event;
import cn.lliiooll.opq.core.managers.event.HandlerList;
import lombok.Getter;

/**
 * 群组加入
 */
public class GroupMemberInviteEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    @Getter
    private final long inviteId;
    @Getter
    private final Group group;
    @Getter
    private final Member member;

    public GroupMemberInviteEvent(long inviteId, Group group, Member member) {
        this.inviteId = inviteId;
        this.group = group;
        this.member = member;
    }


    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
