package me.abel.qywechatapi.dto.wechat.out;

import java.util.List;

public class CustomerBaseResult {

    private int errcode;
    private String errmsg;
    private List<String> customer_contacts;
    private List<String> external_userid;
    private ExternalContact external_contact;
    private List<FollowUser> follow_user;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<String> getCustomer_contacts() {
        return customer_contacts;
    }

    public void setCustomer_contacts(List<String> customer_contacts) {
        this.customer_contacts = customer_contacts;
    }

    public List<String> getExternal_userid() {
        return external_userid;
    }

    public void setExternal_userid(List<String> external_userid) {
        this.external_userid = external_userid;
    }

    public ExternalContact getExternal_contact() {
        return external_contact;
    }

    public void setExternal_contact(ExternalContact external_contact) {
        this.external_contact = external_contact;
    }

    public List<FollowUser> getFollow_user() {
        return follow_user;
    }

    public void setFollow_user(List<FollowUser> follow_user) {
        this.follow_user = follow_user;
    }
}
