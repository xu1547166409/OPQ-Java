package cn.lliiooll.opq.utils;

import lombok.Getter;
import lombok.Setter;

public class IData<T> {

    @Getter
    @Setter
    private T data;

    public IData(T data) {
        this.data = data;
    }
}
