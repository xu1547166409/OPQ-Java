package cn.lliiooll.opq.core.data.group;

import cn.lliiooll.opq.core.OPQGlobal;
import cn.lliiooll.opq.core.data.message.MessageChain;
import cn.lliiooll.opq.core.data.user.Member;
import cn.lliiooll.opq.core.queue.IQueue;
import cn.lliiooll.opq.core.queue.RequestBuilder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.Data;
import org.apache.logging.log4j.LogManager;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

@Data
public class Group {
    public long id;
    public String name;

    public void sendMessage(MessageChain messageChain) {
        OPQGlobal.sendGroupMessage(messageChain, this);
    }

    public Member getMember(long userID) {
        for (Member m : OPQGlobal.getMemberList(this)) {
            if (m.getMemberUin() == userID) {
                //LogManager.getLogger().info("找到群员");
                return m;
            }
        }
        return new Member();
    }


}
