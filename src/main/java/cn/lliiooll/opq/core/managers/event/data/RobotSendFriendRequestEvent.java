package cn.lliiooll.opq.core.managers.event.data;

import cn.lliiooll.opq.core.data.group.Group;
import cn.lliiooll.opq.core.managers.event.Event;
import cn.lliiooll.opq.core.managers.event.HandlerList;
import lombok.Getter;

/**
 * 好友请求事件
 */
public class RobotSendFriendRequestEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    @Getter
    public final long id;
    @Getter
    public final String question;
    @Getter
    public final boolean isFromGroup;
    @Getter
    public final Group fromGroup;


    /**
     * @param id          接收者id
     * @param question    问题
     * @param isFromGroup 是否来自群组
     * @param fromGroup   来自的群组
     */
    public RobotSendFriendRequestEvent(long id, String question, boolean isFromGroup, Group fromGroup) {
        this.id = id;
        this.question = question;
        this.isFromGroup = isFromGroup;
        this.fromGroup = fromGroup;
    }


    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
