package cn.lliiooll.opq.core.managers.event.data;

import cn.lliiooll.opq.core.OPQGlobal;
import cn.lliiooll.opq.core.data.group.Group;
import cn.lliiooll.opq.core.managers.event.Event;
import cn.lliiooll.opq.core.managers.event.HandlerList;
import cn.lliiooll.opq.core.queue.IQueue;
import cn.lliiooll.opq.core.queue.RequestBuilder;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

/**
 * 好友请求事件
 */
public class GroupJoinRequestEvent extends Event {
    private static HandlerList handlers = new HandlerList();

    @Getter
    public final long id;
    @Getter
    public final long groupId;
    @Getter
    public final JSONObject source;


    public GroupJoinRequestEvent(long id, long groupId, JSONObject source) {
        this.id = id;
        this.groupId = groupId;
        this.source = source;
    }

    /**
     * 接受请求
     */
    public void accept() {
        String url = "http://" + OPQGlobal.url + "/v1/LuaApiCaller?qq=" + OPQGlobal.qq + "&funcname=AnswerInviteGroup&timeout=10";
        JSONObject j = source;
        JSONObject cu = j.getJSONObject("CurrentPacket");
        JSONObject data = j.getJSONObject("Data");
        JSONObject eData = j.getJSONObject("EventData");
        eData.put("Action", 11);
        data.put("EventData", eData);
        cu.put("Data", data);
        j.put("CurrentPacket", cu);
        IQueue.sendRequest(
                RequestBuilder.builder()
                        .setUrl(url)
                        .setRequest(j.toJSONString())
                        .build()
        );
    }

    /**
     * 忽略请求
     */
    public void pass() {
        String url = "http://" + OPQGlobal.url + "/v1/LuaApiCaller?qq=" + OPQGlobal.qq + "&funcname=AnswerInviteGroup&timeout=10";
        JSONObject j = source;
        JSONObject cu = j.getJSONObject("CurrentPacket");
        JSONObject data = j.getJSONObject("Data");
        JSONObject eData = j.getJSONObject("EventData");
        eData.put("Action", 14);
        data.put("EventData", eData);
        cu.put("Data", data);
        j.put("CurrentPacket", cu);
        IQueue.sendRequest(
                RequestBuilder.builder()
                        .setUrl(url)
                        .setRequest(j.toJSONString())
                        .build()
        );
    }

    /**
     * 拒绝请求
     */
    public void refuse() {
        String url = "http://" + OPQGlobal.url + "/v1/LuaApiCaller?qq=" + OPQGlobal.qq + "&funcname=AnswerInviteGroup&timeout=10";
        JSONObject j = source;
        JSONObject cu = j.getJSONObject("CurrentPacket");
        JSONObject data = j.getJSONObject("Data");
        JSONObject eData = j.getJSONObject("EventData");
        eData.put("Action", 21);
        data.put("EventData", eData);
        cu.put("Data", data);
        j.put("CurrentPacket", cu);
        IQueue.sendRequest(
                RequestBuilder.builder()
                        .setUrl(url)
                        .setRequest(j.toJSONString())
                        .build()
        );
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
