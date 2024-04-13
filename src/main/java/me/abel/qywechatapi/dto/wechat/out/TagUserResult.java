package me.abel.qywechatapi.dto.wechat.out;

import java.util.List;

public class TagUserResult {

	private String errcode;
    private String errmsg;
    private String tagname;
    private List<UserBaseResult> userlist;
    private List<Integer> partylist;

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

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public List<UserBaseResult> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<UserBaseResult> userlist) {
        this.userlist = userlist;
    }

    public List<Integer> getPartylist() {
        return partylist;
    }

    public void setPartylist(List<Integer> partylist) {
        this.partylist = partylist;
    }
}
