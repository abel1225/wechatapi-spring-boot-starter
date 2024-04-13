package me.abel.qywechatapi.dto.wechat.out;

import java.util.List;

public class FollowUser {

    private String userid;
    private String remark;
    private String description;
    private String createtime;
    private String tags;
    private String remark_company;
    private String state;
    private List<String> remark_mobiles;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRemark_company() {
        return remark_company;
    }

    public void setRemark_company(String remark_company) {
        this.remark_company = remark_company;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getRemark_mobiles() {
        return remark_mobiles;
    }

    public void setRemark_mobiles(List<String> remark_mobiles) {
        this.remark_mobiles = remark_mobiles;
    }
}
