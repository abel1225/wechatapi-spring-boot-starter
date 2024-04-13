package me.abel.qywechatapi.dto.wechat.in;

public class MessageDto {
    private String fromUser;
    private String title;
    private String toUser;
    private String content;
    private String url;
    private String appType;

    public MessageDto() {
    }

    public MessageDto(String toUser, String content, String appType) {
        this.toUser=toUser;
        this.content=content;
        this.appType=appType;
    }

    public MessageDto(String toUser, String content, String url, String appType) {
        this.toUser=toUser;
        this.content=content;
        this.url=url;
        this.appType=appType;
    }

    public MessageDto(String title, String toUser, String content, String url, String appType) {
        this.title=title;
        this.toUser=toUser;
        this.content=content;
        this.url=url;
        this.appType=appType;
    }

    public String getFromUser() {
        return fromUser;
    }

    public final void setFromUser(String fromUser) {
        this.fromUser=fromUser;
    }

    public String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title=title;
    }

    public String getToUser() {
        return toUser;
    }

    public final void setToUser(String toUser) {
        this.toUser=toUser;
    }

    public String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content=content;
    }

    public String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url=url;
    }

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}
}