package com.hanshow.wise.base.data.model;

public class TChildLevel2<T> {
    private String tChildLevel2id;
    private T data;

    public String gettChildLevel2id() {
        return tChildLevel2id;
    }

    public void settChildLevel2id(String tChildLevel2id) {
        this.tChildLevel2id = tChildLevel2id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
