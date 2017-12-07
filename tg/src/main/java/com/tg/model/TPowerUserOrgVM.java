package com.tg.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@JsonPropertyOrder(alphabetic = true)
public class TPowerUserOrgVM implements Serializable {
    private static final long        serialVersionUID = 6248036603724633560L;
    private List<TPoweruserOrg> list;

    public List<TPoweruserOrg> getList() {
        return list;
    }

    public void setList(List<TPoweruserOrg> list) {
        this.list = list;
    }
}
