package me.abel.qywechatapi.dto.wechat.out;

public class AccessToken {

    private String access_token;	// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
    private int expires_in;	    // access_token接口调用凭证超时时间，单位（秒）
    // 超时时间
    private Long expires;
    // 初始化
    public void init(){
        expires = System.currentTimeMillis() + getExpires_in() * 1000L;
    }

    public boolean isExpired(){
        return expires < System.currentTimeMillis();
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}
