package me.abel.qywechatapi.dto.wechat.out;


import com.alibaba.fastjson2.annotation.JSONField;

public class Oauth2Token {
	@JSONField(alternateNames = {"accessToken", "access_token"})
	private String accessToken;	// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	@JSONField(alternateNames = {"expiresIn", "expires_in"})
	private int expiresIn;	    // access_token接口调用凭证超时时间，单位（秒）
	@JSONField(alternateNames = {"refreshToken", "refresh_token"})
	private String refreshToken;	// 用户刷新access_token
	@JSONField(alternateNames = {"UserId", "OpenId"})
	private String openid;	    // 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	private String scope;	    // 用户授权的作用域，使用逗号（,）分隔

	private String mobile; // 手机号码，第三方仅通讯录应用可获取

	@JSONField(alternateNames = {"DeviceId"})
	private String deviceId;	    // 用户授权的作用域，使用逗号（,）分隔
	private String errcode;	    //
	private String errmsg;	    //
	private String avatar;
	private String qr_code;

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDeviceId() {
		return deviceId;
	}

	public final void setDeviceId(String deviceId) {
		this.deviceId=deviceId;
	}

	public String getErrcode() {
		return errcode;
	}

	public final void setErrcode(String errcode) {
		this.errcode=errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public final void setErrmsg(String errmsg) {
		this.errmsg=errmsg;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getQr_code() {
		return qr_code;
	}
	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}
}
