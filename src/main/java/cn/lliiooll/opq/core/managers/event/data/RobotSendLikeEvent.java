package cn.lliiooll.opq.core.managers.event.data;

import cn.lliiooll.opq.core.data.group.Group;
import cn.lliiooll.opq.core.managers.event.Event;
import cn.lliiooll.opq.core.managers.event.HandlerList;
import lombok.Getter;

/**
 * 好友请求事件
 */
public class RobotSendLikeEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    @Getter
    public final long id;

    public RobotSendLikeEvent(long id) {
        this.id = id;
    }


    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
