package cn.lliiooll.opq.core.data.user;

import cn.lliiooll.opq.core.OPQGlobal;
import cn.lliiooll.opq.core.data.group.Group;
import cn.lliiooll.opq.core.queue.IQueue;
import cn.lliiooll.opq.core.queue.RequestBuilder;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.HashMap;

@Data
public class Member implements User {
    public long Age;
    public long CreditLevel;
    public long FaceId;
    public long Gender;
    /**
     * 入群时间
     */
    public long JoinTime;
    /**
     * 最后一次发言时间
     */
    public long LastSpeakTime;
    /**
     * 是否群管理
     */
    public long GroupAdmin;
    /**
     * 等级
     */
    public long MemberLevel;
    /**
     * qq号
     */
    public long MemberUin;
    public long Status;
    public String AutoRemark;
    public String SpecialTitle;
    public String Memo;
    public String Email;
    /**
     * 群名片
     */
    public String GroupCard;
    public String ShowName;
    public String NickName;
    public long fromGroup;

    /**
     * 是否是管理员
     *
     * @return
     */
    public boolean isGroupAdmin() {
        return this.GroupAdmin == 1;
    }

    /**
     * 设置成员群名片，为Null时清除群名片
     *
     * @param card
     */
    public void setGroupCard(String card) {
        String url = "http://" + OPQGlobal.url + "/v1/LuaApiCaller?qq=" + OPQGlobal.qq + "&funcname=ModifyGroupCard&timeout=10";
        IQueue.sendRequest(
                RequestBuilder.builder()
                        .setUrl(url)
                        .setRequest(new JSONObject(new HashMap<String, Object>() {{
                            put("GroupID", Member.this.getFromGroup().getId());
                            put("UserID", Member.this.getMemberUin());
                            put("NewNick", card == null ? "" : card);
                        }}).toJSONString())
                        .build()
        );
    }

    /**
     * 设置成员群头衔，为Null时清除群名片
     *
     * @param title
     */
    public void setGroupTitle(String title) {
        String url = "http://" + OPQGlobal.url + "/v1/LuaApiCaller?qq=" + OPQGlobal.qq + "&funcname=SetUniqueTitle&timeout=10";
        IQueue.sendRequest(
                RequestBuilder.builder()
                        .setUrl(url)
                        .setRequest(new JSONObject(new HashMap<String, Object>() {{
                            put("GroupID", Member.this.getFromGroup().getId());
                            put("UserID", Member.this.getMemberUin());
                            put("NewTitle", title == null ? "" : title);
                        }}).toJSONString())
                        .build()
        );
    }

    /**
     * 踢出群
     */
    public void kick() {
        OPQGlobal.kickMember(this);
    }


    /**
     * 禁言
     *
     * @param time
     */
    public void mute(long time) {
        OPQGlobal.setMuteOfGroupMember(this.getFromGroup(), this, time);
    }

    /**
     * 解除禁言
     */
    public void unmute() {
        OPQGlobal.cancelMuteOfGroupMember(this.getFromGroup(), this);
    }

    /**
     * 是否是管理员
     *
     * @return
     */
    public Group getFromGroup() {
        return OPQGlobal.getGroup(fromGroup);
    }
}
