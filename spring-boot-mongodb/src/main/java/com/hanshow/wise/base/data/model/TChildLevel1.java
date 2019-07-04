package com.hanshow.wise.base.data.model;

import java.util.ArrayList;
import java.util.List;

public class TChildLevel1<T> {
    private String tChildLevel1id;
    private T data;
    private List<TChildLevel2>  tChildLevel2List =new ArrayList<>();
    public String gettChildLevel1id() {
        return tChildLevel1id;
    }

    public void settChildLevel1id(String tChildLevel1id) {
        this.tChildLevel1id = tChildLevel1id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TChildLevel2> gettChildLevel2List() {
        return tChildLevel2List;
    }

    public void settChildLevel2List(List<TChildLevel2> tChildLevel2List) {
        this.tChildLevel2List = tChildLevel2List;
    }
}
