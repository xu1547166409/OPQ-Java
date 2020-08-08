package cn.lliiooll.opq.core.managers.event.data;

import cn.lliiooll.opq.core.data.group.Group;
import cn.lliiooll.opq.core.data.user.Member;
import cn.lliiooll.opq.core.managers.event.Event;
import cn.lliiooll.opq.core.managers.event.HandlerList;
import lombok.Getter;

/**
 * 群组主动加入
 */
public class GroupMemberJoinEvent extends Event {
    private static HandlerList handlers = new HandlerList();
    @Getter
    private final Group group;
    @Getter
    private final Member member;

    public GroupMemberJoinEvent(Group group, Member member) {
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
