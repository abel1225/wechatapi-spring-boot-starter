package me.abel.qywechatapi.dto.wechat.out;

import java.util.List;

public class DepartmetUserResult {

	private String errcode;
    private String errmsg;
    private List<UserBaseResult> userlist;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<UserBaseResult> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserBaseResult> userlist) {
        this.userlist = userlist;
    }
}
