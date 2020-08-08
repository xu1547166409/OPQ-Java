package cn.lliiooll.opq.core.data.user;

import cn.lliiooll.opq.core.OPQGlobal;
import cn.lliiooll.opq.core.data.group.Group;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

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
     * 是否是管理员
     *
     * @return
     */
    public Group getFromGroup() {
        return OPQGlobal.getGroup(fromGroup);
    }
}
