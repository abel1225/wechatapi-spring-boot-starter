package me.abel.qywechatapi.dto.wechat;


import me.abel.qywechatapi.dto.wechat.out.ContactWayResult;

import java.util.List;

public class ContactWayBaseResponse {

    private String errcode;
    private String errmsg;
    private List<ContactWayResult> contact_way;

    public String getErrcode() {
        return errcode;
    }

    public final void setErrcode(String errcode) {
        this.errcode=errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public final void setErrmsg(String errmsg) {
        this.errmsg=errmsg;
    }

    public List<ContactWayResult> getContact_way() {
        return contact_way;
    }

    public final void setContact_way(List<ContactWayResult> contact_way) {
        this.contact_way=contact_way;
    }
}
