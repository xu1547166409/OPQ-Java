package cn.lliiooll.iotqq.event;

import cn.lliiooll.iotqq.IOTQQMain;
import cn.lliiooll.iotqq.core.IOTGlobal;
import cn.lliiooll.iotqq.core.data.group.Group;
import cn.lliiooll.iotqq.core.data.message.MessageFrom;
import cn.lliiooll.iotqq.core.managers.cmd.CommandManager;
import cn.lliiooll.iotqq.core.managers.event.EventHandler;
import cn.lliiooll.iotqq.core.managers.event.data.FriendMessageEvent;
import cn.lliiooll.iotqq.core.managers.event.data.GroupMessageEvent;
import org.apache.logging.log4j.LogManager;

public class LoggerListener {

    @EventHandler
    public void onGroup(GroupMessageEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append(IOTGlobal.getQq())
                .append(" <- 群: ")
                .append(event.getGroup().getName())
                .append("（")
                .append(event.getGroup().getId())
                .append("）")
                .append(" 发送者: ")
                .append(event.getSender().getName())
                .append("（")
                .append(event.getSender().getId())
                .append("）")
                .append(" 消息：")
                .append(event.getMessage().messageToString());
        LogManager.getLogger().info(sb.toString());
    }

    @EventHandler
    public void onPrivate(FriendMessageEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append(IOTGlobal.getQq())
                .append(" <- 私聊: ")
                .append(event.getSender().getNick())
                .append("（")
                .append(event.getSender().getId())
                .append("）")
                .append(" 消息：")
                .append(event.getMessage().messageToString());
        LogManager.getLogger().info(sb.toString());
    }
}
