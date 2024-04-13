package me.abel.qywechatapi.dto.wechat.in;

import java.util.List;

public class ContactWayDto {

    private String config_id;           //
    private Integer type;               //联系方式类型,1-单人, 2-多人
    private Integer scene;              //场景，1-在小程序中联系，2-通过二维码联系
    private Integer style;              //
    private String remark;              //联系方式的备注信息，用于助记，不超过30个字符
    private Boolean skip_verify;        //外部客户添加时是否无需验证，默认为true
    private String state;               //企业自定义的state参数，用于区分不同的添加渠道，在调用“获取外部联系人详情”时会返回该参数值
    private List<String> user;          //使用该联系方式的用户userID列表，在type为1时为必填，且只能有一个
    private List<String> party;         //使用该联系方式的部门id列表，只在type为2时有效

    public String getConfig_id() {
        return config_id;
    }

    public final void setConfig_id(String config_id) {
        this.config_id=config_id;
    }

    public Integer getType() {
        return type;
    }

    public final void setType(Integer type) {
        this.type=type;
    }

    public Integer getScene() {
        return scene;
    }

    public final void setScene(Integer scene) {
        this.scene=scene;
    }

    public Integer getStyle() {
        return style;
    }

    public final void setStyle(Integer style) {
        this.style=style;
    }

    public String getRemark() {
        return remark;
    }

    public final void setRemark(String remark) {
        this.remark=remark;
    }

    public Boolean getSkip_verify() {
        return skip_verify;
    }

    public final void setSkip_verify(Boolean skip_verify) {
        this.skip_verify=skip_verify;
    }

    public String getState() {
        return state;
    }

    public final void setState(String state) {
        this.state=state;
    }

    public List<String> getUser() {
        return user;
    }

    public final void setUser(List<String> user) {
        this.user=user;
    }

    public List<String> getParty() {
        return party;
    }

    public final void setParty(List<String> party) {
        this.party=party;
    }
}
