package com.hanshow.wise.base.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TRoot<T> implements Serializable {
    private String rootId;
    private List<TChildLevel1> tChildLevel1List = new ArrayList<>();
    private T data;

    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public List<TChildLevel1> gettChildLevel1List() {
        return tChildLevel1List;
    }

    public void settChildLevel1List(List<TChildLevel1> tChildLevel1List) {
        this.tChildLevel1List = tChildLevel1List;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        TRoot<User> tRoot = new TRoot<>();
        User user = new User();
        user.setAge(26);
        user.setId(System.currentTimeMillis());
        user.setName("ctl");
        tRoot.setData(user);
        tRoot.setRootId("rootId" + System.currentTimeMillis());
        List<TChildLevel1> tChildLevel1List = new ArrayList<>();

        TChildLevel1 tChildLevel1_01 = new TChildLevel1();
        tChildLevel1_01.setData("tChildLevel1_01" + System.currentTimeMillis());
        tChildLevel1_01.settChildLevel1id(String.valueOf(System.currentTimeMillis()));
        List<TChildLevel2> tChildLevel2List_01 = new ArrayList<>();
        tChildLevel1_01.settChildLevel2List(tChildLevel2List_01);

        TChildLevel2 tChildLevel2_01 = new TChildLevel2();
        tChildLevel2_01.setData(user);
        tChildLevel2_01.settChildLevel2id("tChildLevel2_01" + System.currentTimeMillis());
        tChildLevel2List_01.add(tChildLevel2_01);

        TChildLevel1 tChildLevel1_02 = new TChildLevel1();
        tChildLevel1_02.setData("tChildLevel1_02" + System.currentTimeMillis());
        tChildLevel1_02.settChildLevel1id(String.valueOf(System.currentTimeMillis()));
        List<TChildLevel2> tChildLevel2List_02 = new ArrayList<>();
        tChildLevel1_02.settChildLevel2List(tChildLevel2List_02);
        TChildLevel2 tChildLevel2_02 = new TChildLevel2();
        tChildLevel2_02.setData(user);
        tChildLevel2_02.settChildLevel2id("tChildLevel2_02" + System.currentTimeMillis());
        tChildLevel2List_02.add(tChildLevel2_02);


        tChildLevel1List.add(tChildLevel1_01);
        tChildLevel1List.add(tChildLevel1_02);

        tRoot.settChildLevel1List(tChildLevel1List);
    }
}
