package cn.lliiooll.opq.event;

import cn.lliiooll.opq.core.data.message.MessageChain;
import cn.lliiooll.opq.core.managers.event.EventHandler;
import cn.lliiooll.opq.core.managers.event.data.FriendMessageEvent;

import java.util.Random;

public class ReplyListener {

    @EventHandler
    public void onPrivate(FriendMessageEvent event) {
        if (event.getSender().getId() != 3483706632L) {
            String msg = event.getMessage().messageToString();
            String[] zd;
            if (msg.contains("在")) {
                zd = new String[]{"在的呐", "有什么事呐", "怎么了呐", "嗯呐"};
            } else if (msg.contains("睡")) {
                zd = new String[]{"睡了呢", "正在睡觉呐"};
            } else if (msg.contains("帮")) {
                zd = new String[]{"睡了呢", "正在睡觉呐", "睡觉呢"};
            } else if (msg.contains("谁") || msg.contains("你")) {
                zd = new String[]{"ai呢", "我是ai呐", "我是他的ai呐"};
            } else if (msg.contains("是") || msg.contains("机器人")) {
                zd = new String[]{"是呢", "是ai呐"};
            } else {
                zd = new String[]{"就这样叭", "好的呐", "嗯呐"};
            }
            event.getSender().sendMessage(MessageChain.newCall(zd[new Random().nextInt(zd.length)]));
        }
    }
}
