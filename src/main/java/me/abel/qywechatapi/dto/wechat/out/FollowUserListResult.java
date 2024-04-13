package me.abel.qywechatapi.dto.wechat.out;

import java.util.List;

public class FollowUserListResult {

    private int errcode;
    private String errmsg;
    private List<String> follow_user;

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

    public List<String> getFollow_user() {
        return follow_user;
    }

    public void setFollow_user(List<String> follow_user) {
        this.follow_user = follow_user;
    }
}
