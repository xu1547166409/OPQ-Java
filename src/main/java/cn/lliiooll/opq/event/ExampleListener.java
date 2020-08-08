package cn.lliiooll.opq.event;

import cn.lliiooll.opq.core.OPQGlobal;
import cn.lliiooll.opq.core.data.group.GroupAnnounceType;
import cn.lliiooll.opq.core.data.message.MessageChain;
import cn.lliiooll.opq.core.data.message.data.*;
import cn.lliiooll.opq.core.data.user.AddFriendFrom;
import cn.lliiooll.opq.core.data.user.Member;
import cn.lliiooll.opq.core.managers.event.EventHandler;
import cn.lliiooll.opq.core.managers.event.data.*;

import java.io.File;

public class ExampleListener {

    @EventHandler
    public void onGroup(GroupMessageEvent event) {
        // 回复消息
        OPQGlobal.reply(event.getMessage(), MessageChain.newCall("回复消息"));
        // 群消息
        event.getGroup().sendMessage(MessageChain.newCall("群消息"));
        // 群图文混合消息
        event.getGroup().sendMessage(MessageChain.newCall("群消息").put(new PicMessage("https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg")));
        // 群语音消息
        event.getGroup().sendMessage(MessageChain.newCall(new VoiceMessage("http://d.s2.lliiooll.cn:88/test.amr")));
        // 群at消息
        event.getGroup().sendMessage(MessageChain.newCall(new AtMessage(new Long[]{3475898232L})));
        // 禁言1分钟
        event.getSender().mute(1);
        // 解除禁言
        event.getSender().unmute();
        // 踢出群
        event.getSender().kick();
        // 赞
        OPQGlobal.like(event.getSender().getMemberUin());
        // 发送一个公告
        OPQGlobal.sendGroupAnnounce(event.getGroup(), "测试公告", "测试公告", false, GroupAnnounceType.POPUP);
        // 下载一个文件
        if (event.getMessage() instanceof GroupFileMessage) {
            OPQGlobal.downloadGroupFile(event.getGroup(), ((GroupFileMessage) event.getMessage()).getFileId(), new File("test.file"));
        }
        // 添加多个好友
        OPQGlobal.addFriend("消息", AddFriendFrom.GROUP, event.getGroup(), 1234567890L, 9876543210L);
        // 撤回一条消息
        OPQGlobal.recallGroup(event.getMessage(), event.getGroup());
        // 邀请多个人加入本群
        OPQGlobal.invite(event.getGroup(), 1234567890L, 9876543210L);
        // 主动加入一个群
        OPQGlobal.joinGroup("验证消息", 1234567890L, 9876543210L);
        // 退出多个群
        OPQGlobal.quitGroup(1234567890L, 9876543210L);
        // 开启全员禁言
        OPQGlobal.setAllMuteOfGroup(event.getGroup(), true);
        // 关闭全员禁言
        OPQGlobal.setAllMuteOfGroup(event.getGroup(), false);
        // 获取一个群员
        Member member = event.getGroup().getMember(1234567890L);
        // 修改群员名片
        member.setGroupCard("新的名片");
        // 修改群头衔
        member.setGroupTitle("新的群头衔");
    }

    @EventHandler
    public void onPrivate(FriendMessageEvent event) {
        // 回复消息
        OPQGlobal.reply(event.getMessage(), MessageChain.newCall("回复消息"));
        // 好友消息
        event.getSender().sendMessage(MessageChain.newCall("?"));
        // 好友图文混合消息
        event.getSender().sendMessage(MessageChain.newCall("?").put(new PicMessage("https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg")));
        // 好友语音消息
        event.getSender().sendMessage(MessageChain.newCall(new VoiceMessage("http://d.s2.lliiooll.cn:88/test.amr")));
        // 下载文件
        if (event.getMessage() instanceof FriendFileMessage) {
            OPQGlobal.downloadFriendFile(((FriendFileMessage) event.getMessage()).getFileId(), new File(((FriendFileMessage) event.getMessage()).getFileName()));
        }
        // 获取一个好友
        OPQGlobal.getFriend(1234567890L);
    }

    @EventHandler
    public void onFriendRequest(FriendRequestEvent event) {
        // 接受请求
        event.accept();
        // 忽略请求
        event.pass();
        // 拒绝请求
        event.refuse();
    }

    @EventHandler
    public void onGroupRequest(GroupJoinRequestEvent event) {
        // 接受请求
        event.accept();
        // 忽略请求
        event.pass();
        // 拒绝请求
        event.refuse();
    }
}
