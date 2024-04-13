package me.abel.qywechatapi.dto.wechat.out;

import java.util.List;

public class VehicleResponse {

    private String type;

    private List<VehicleResult> options;

    public String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type=type;
    }

    public List<VehicleResult> getOptions() {
        return options;
    }

    public final void setOptions(List<VehicleResult> options) {
        this.options=options;
    }
}
