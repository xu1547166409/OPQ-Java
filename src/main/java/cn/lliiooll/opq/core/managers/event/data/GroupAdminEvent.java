package cn.lliiooll.opq.core.managers.event.data;

import cn.lliiooll.opq.core.data.group.Group;
import cn.lliiooll.opq.core.managers.event.Event;
import cn.lliiooll.opq.core.managers.event.HandlerList;
import lombok.Getter;

/**
 * 群组管理变更
 */
public class GroupAdminEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    /**
     * 被操作id
     */
    @Getter
    private final long id;
    /***
     *  群
     */
    @Getter
    private final Group group;
    /**
     * true为升管理
     * flase为取消
     */
    @Getter
    private final boolean up;

    public GroupAdminEvent(long id, Group group, boolean up) {
        this.id = id;
        this.group = group;
        this.up = up;
    }


    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
