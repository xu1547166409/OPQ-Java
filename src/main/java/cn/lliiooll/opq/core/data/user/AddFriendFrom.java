package cn.lliiooll.opq.core.data.user;

import lombok.Getter;

public enum AddFriendFrom {

    /**
     * 空间
     */
    QZONE(2011),
    /**
     * 搜索
     */
    SEARCH(2020),
    /**
     * 群组
     */
    GROUP(2004),
    /**
     * 群组
     */
    DISCUSS(2005);

    @Getter
    public final int type;

    AddFriendFrom(int i) {
        this.type = i;
    }
}
