package cn.lliiooll.opq.core.managers.event;

import cn.lliiooll.opq.core.OPQ;
import cn.lliiooll.opq.core.OPQGlobal;
import cn.lliiooll.opq.core.data.group.Group;
import cn.lliiooll.opq.core.data.user.Friend;
import cn.lliiooll.opq.core.managers.event.data.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;

public class EventFactory {
    public static void execute(JSONObject data) {
        JSONObject d = data.getJSONObject("CurrentPacket").getJSONObject("Data");
        EventType type = EventType.valueOf(d.getString("EventName"));
        JSONObject eData = d.getJSONObject("EventData");
        JSONObject eMsg = d.getJSONObject("EventMsg");

        switch (type) {
            case ON_EVENT_GROUP_EXIT:
                EventManager.invoke(new GroupMemberExitEvent(eData.getLongValue("UserID"), OPQGlobal.getGroup(eMsg.getLongValue("FromUin"))));
            case ON_EVENT_GROUP_JOIN:
                long invite = eData.getLongValue("InviteUin");
                Group g1 = OPQGlobal.getGroup(eMsg.getLongValue("FromUin"));
                if (invite == 0) {
                    EventManager.invoke(new GroupMemberJoinEvent(g1, g1.getMember(eData.getLongValue("UserID"))));
                } else {
                    EventManager.invoke(new GroupMemberInviteEvent(invite, g1, g1.getMember(eData.getLongValue("UserID"))));
                }
            case ON_EVENT_GROUP_SHUT:
                EventManager.invoke(new GroupMuteEvent(eData.getLongValue("UserID"), eData.getLongValue("ShutTime"), OPQGlobal.getGroup(eData.getLongValue("GroupID"))));
            case ON_EVENT_GROUP_ADMIN:
                EventManager.invoke(new GroupAdminEvent(eData.getLongValue("UserID"), OPQGlobal.getGroup(eData.getLongValue("GroupID")), eData.getIntValue("Flag") == 1));
            case ON_EVENT_FRIEND_ADD:
                Group group = new Group();
                group.setName(eData.getString("FromGroupName"));
                group.setId(eData.getLongValue("FromGroupId"));
                EventManager.invoke(new FriendRequestEvent(eData.getLongValue("UserID"), eData.getString("Content"), eData.getLongValue("FromGroupId") == 0, group, data));
            case ON_EVENT_GROUP_REVOKE:
                Group g = new Group();
                g.setName("");
                g.setId(eData.getLongValue("GroupID"));
                EventManager.invoke(new GroupRecallEvent(eData.getLongValue("UserID"), eData.getLongValue("MsgSeq"), g, eData.getLongValue("AdminUserID")));
            case ON_EVENT_FRIEND_DELETE:
                EventManager.invoke(new FriendDeleteEvent(eData.getLongValue("UserID")));
            case ON_EVENT_FRIEND_REVOKE:
                EventManager.invoke(new FriendRecallEvent(eData.getLongValue("UserID"), eData.getLongValue("MsgSeq")));
            case ON_EVENT_NOTIFY_PUSHADDFRD:
                Friend friend = new Friend();
                friend.setId(eData.getLongValue("UserID"));
                friend.setNick(eData.getString("NickName"));
                EventManager.invoke(new FriendAddedEvent(friend));
            case ON_EVENT_GROUP_ADMINSYSNOTIFY:
                if (eMsg.getString("Content").equalsIgnoreCase("邀请加群")) {
                    EventManager.invoke(new GroupJoinRequestEvent(eData.getLongValue("InviteUin"), eData.getLongValue("GroupId"), data));
                }
            case ON_EVENT_GROUP_JOIN_SUCC:// 主动加群成功
            case ON_EVENT_GROUP_EXIT_SUCC:// 主动退群成功
                EventManager.invoke(new RobotQuitGroupEvent(eData.getLongValue("GroupID")));
        }


    }
}
