package cn.lliiooll.opq.event;

import cn.lliiooll.opq.core.OPQGlobal;
import cn.lliiooll.opq.core.managers.event.EventHandler;
import cn.lliiooll.opq.core.managers.event.data.*;

public class RefshListener {

    @EventHandler(order = -99)
    public void onGroupI(GroupMemberInviteEvent e) {
        OPQGlobal.refreshGroupList();
        OPQGlobal.refreshMemberList(e.getGroup());
    }

    @EventHandler(order = -99)
    public void onGroupJ(GroupMemberJoinEvent e) {
        OPQGlobal.refreshGroupList();
        OPQGlobal.refreshMemberList(e.getGroup());
    }

    @EventHandler(order = -99)
    public void onGroupE(GroupMemberExitEvent e) {
        OPQGlobal.refreshGroupList();
        OPQGlobal.refreshMemberList(e.getGroup());
    }

    @EventHandler(order = -99)
    public void onGroupK(GroupMemberKickEvent e) {
        OPQGlobal.refreshGroupList();
        OPQGlobal.refreshMemberList(e.getGroup());
    }


    @EventHandler(order = -99)
    public void onPrivate(FriendAddedEvent e) {
        OPQGlobal.refreshFriendList();
    }
}
