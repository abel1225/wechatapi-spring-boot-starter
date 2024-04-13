package me.abel.qywechatapi.dto.wechat.out;

import java.util.List;

public class VehicleBrandResult {

    private String type;
    private String statusCode;

    private List<VehicleResponse> responseData;

    public String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type=type;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public final void setStatusCode(String statusCode) {
        this.statusCode=statusCode;
    }

    public List<VehicleResponse> getResponseData() {
        return responseData;
    }

    public final void setResponseData(List<VehicleResponse> responseData) {
        this.responseData=responseData;
    }
}
