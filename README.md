# OPQ-Java

[OPQ项目地址](https://github.com/OPQBOT/OPQ/)


这个项目在于让使用者更方便的使用OPQ进行插件开发

功能暂未完全实现


## 食用方法

### 初始化
---
```Java
OPQ opq = OPQBuilder.builder()
                .setURL("OPQ地址,例如127.0.0.1:8888,不带http")
                .setQQ(1234567890L)// 机器人QQ号
                .build();
        opq.init("com.example");// 开发包名, 用于自动注册指令和监听器用
```
---
### 注册指令
---
```Java
import cn.lliiooll.opq.core.managers.cmd.CommandExecutor;
import cn.lliiooll.opq.core.managers.cmd.CommandResult;
import cn.lliiooll.opq.core.managers.cmd.annotations.Command;


@Command(name = "example", description = "示例指令", alias = {"ex", "示例"}, usage = "示例")
public class ExampleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandResult result) {
		// 处理指令
        return true;// 返回false输出用法
    }
}
```
---
### 注册事件
---
```Java
import cn.lliiooll.opq.core.managers.event.EventHandler;

public class ExampleListener{
    @EventHandler
    public void onEvent(Event event) {
        // 处理事件
    }
}
```
---

### api调用梨子
---
```Java
import cn.lliiooll.opq.core.managers.event.EventHandler;

public class ExampleListener{
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
        
    }
}
```
---
### 群聊示例
![](https://ftp.bmp.ovh/imgs/2020/08/38902d27e4fb8905.png)
### 私聊示例
![](https://ftp.bmp.ovh/imgs/2020/08/f3f3068bea599ebb.jpg)

## 事件列表
| 事件                            | 名称                    |         |
| ---------                       | -----                   |-----:   |
| FriendRequestEvent              | 好友申请事件            |  已实现 |
| FriendDeleteEvent               | 好友删除事件            |  已实现 |
| FriendRecallEvent               | 好友撤回事件            |  已实现 |
| FriendAddedEvent                | 好友添加完毕事件        |  已实现 |
| FriendMessageEvent              | 好友消息事件            |  已实现 |
| FriendMessageSendEvent          | 发送好友消息事件        |  已实现 |
| GroupRecallEvent                | 群组撤回事件            |  已实现 |
| GroupMuteEvent                  | 群禁言事件              |  已实现 |
| GroupMessageEvent               | 群消息事件              |  已实现 |
| GroupMessageSendEvent           | 发送群消息事件          |  已实现 |
| GroupAdminEvent                 | 群管理变更事件          |  已实现 |
| GroupMemberKickEvent            | 被踢退群事件            |  已实现 |
| GroupMemberExitEvent            | 主动退群事件            |  已实现 |
| GroupMemberInviteEvent          | 成员被邀入群事件        |  已实现 |
| GroupMemberJoinEvent            | 成员主动入群事件        |  已实现 |
| LoginSuccessEvent               | 机器人登陆成功事件      |  已实现 |
| CheckLoginQrcodeEvent           | 二维码检查事件          |  已实现 |
| RobotSendLike                   | 机器人发送赞事件        |  已实现 |
| RobotSendFriendRequestEvent     | 机器人发送好友请求事件  |  已实现 |
| RobotQuitGroup                  | 机器人主动退群事件      |  已实现 |
