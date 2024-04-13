package me.abel.qywechatapi.dto.wechat.out;

public class ContactWayResult {

    private String config_id;
    private Integer type;
    private Integer scene;
    private Integer style;
    private String remark;
    private Boolean skip_verify;
    private String state;
    private String qr_code;

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

    public String getQr_code() {
        return qr_code;
    }

    public final void setQr_code(String qr_code) {
        this.qr_code=qr_code;
    }
}
