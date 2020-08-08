package cn.lliiooll.opq.event;

import cn.lliiooll.opq.core.OPQGlobal;
import cn.lliiooll.opq.core.managers.event.EventHandler;
import cn.lliiooll.opq.core.managers.event.data.FriendMessageEvent;
import cn.lliiooll.opq.core.managers.event.data.FriendMessageSendEvent;
import cn.lliiooll.opq.core.managers.event.data.GroupMessageEvent;
import cn.lliiooll.opq.core.managers.event.data.GroupMessageSendEvent;
import cn.lliiooll.opq.utils.TaskUtils;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.ExecutorService;

public class LoggerListener {
    private static ExecutorService main = TaskUtils.create("LoggerTask-%d");

    @EventHandler
    public void onGroup(GroupMessageEvent event) {
        main.execute(() -> {
            StringBuilder sb = new StringBuilder();
            sb.append(OPQGlobal.getQq())
                    .append(" <- 群: ")
                    .append(event.getGroup().getName())
                    .append("（")
                    .append(event.getGroup().getId())
                    .append("）")
                    .append(" 发送者: ")
                    .append(event.getSender().getGroupCard())
                    .append("（")
                    .append(event.getSender().getMemberUin())
                    .append("）")
                    .append(" 消息：")
                    .append(event.getMessage().messageToString()
                            .replace("\n", "")
                            .replace("\r", "")
                            .replace("\t", ""));
            LogManager.getLogger().info(sb.toString());
        });
    }

    @EventHandler
    public void onPrivate(FriendMessageEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append(OPQGlobal.getQq())
                .append(" <- 私聊: ")
                .append(event.getSender().getNick())
                .append("（")
                .append(event.getSender().getId())
                .append("）")
                .append(" 消息：")
                .append(event.getMessage().messageToString()
                        .replace("\n", "")
                        .replace("\r", "")
                        .replace("\t", ""));
        LogManager.getLogger().info(sb.toString());
    }

    @EventHandler
    public void onGroupSend(GroupMessageSendEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append(OPQGlobal.getQq())
                .append(" -> 群: ")
                .append(event.getGroup().getName())
                .append("（")
                .append(event.getGroup().getId())
                .append("）")
                .append(" 消息：")
                .append(event.getMessage().messageToString()
                        .replace("\n", "")
                        .replace("\r", "")
                        .replace("\t", ""));
        LogManager.getLogger().info(sb.toString());
    }

    @EventHandler
    public void onPrivateSend(FriendMessageSendEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append(OPQGlobal.getQq())
                .append(" -> 私聊: ")
                .append(event.getMessage().messageToString());
        LogManager.getLogger().info(sb.toString());
    }
}
