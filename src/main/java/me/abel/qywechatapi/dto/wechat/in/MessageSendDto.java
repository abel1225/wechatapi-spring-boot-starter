package me.abel.qywechatapi.dto.wechat.in;

public class MessageSendDto {

    private String touser;              //成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向该企业应用的全部成员发送
    private String toparty;             //部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
    private String totag;               //标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
    private String msgtype = "text";
    private Integer agentid;
    private MessageContentDto text;             //消息内容，最长不超过2048个字节，超过将截断
    private MessageContentDto image;             //消息内容，最长不超过2048个字节，超过将截断
    private MessageContentDto voice;             //消息内容，最长不超过2048个字节，超过将截断
    private MessageContentDto video;             //消息内容，最长不超过2048个字节，超过将截断
    private MessageContentDto file;             //消息内容，最长不超过2048个字节，超过将截断
    private MessageContentDto textcard;             //消息内容，最长不超过2048个字节，超过将截断
    private Integer safe = 0;

    public MessageSendDto() {
    }

    public MessageSendDto(String touser, Integer agentid, MessageContentDto text) {
        this.touser=touser;
        this.agentid=agentid;
        this.text=text;
    }

    public MessageSendDto(String touser, String msgtype, Integer agentid, MessageContentDto text) {
        this.touser=touser;
        this.msgtype=msgtype;
        this.agentid=agentid;
        this.text=text;
    }

    public String getTouser() {
        return touser;
    }

    public final void setTouser(String touser) {
        this.touser=touser;
    }

    public String getToparty() {
        return toparty;
    }

    public final void setToparty(String toparty) {
        this.toparty=toparty;
    }

    public String getTotag() {
        return totag;
    }

    public final void setTotag(String totag) {
        this.totag=totag;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public final void setMsgtype(String msgtype) {
        this.msgtype=msgtype;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public final void setAgentid(Integer agentid) {
        this.agentid=agentid;
    }

    public MessageContentDto getText() {
        return text;
    }

    public final void setText(MessageContentDto text) {
        this.text=text;
    }

    public MessageContentDto getImage() {
        return image;
    }

    public final void setImage(MessageContentDto image) {
        this.image=image;
    }

    public MessageContentDto getVoice() {
        return voice;
    }

    public final void setVoice(MessageContentDto voice) {
        this.voice=voice;
    }

    public MessageContentDto getVideo() {
        return video;
    }

    public final void setVideo(MessageContentDto video) {
        this.video=video;
    }

    public MessageContentDto getFile() {
        return file;
    }

    public final void setFile(MessageContentDto file) {
        this.file=file;
    }

    public MessageContentDto getTextcard() {
        return textcard;
    }

    public final void setTextcard(MessageContentDto textcard) {
        this.textcard=textcard;
    }

    public Integer getSafe() {
        return safe;
    }

    public final void setSafe(Integer safe) {
        this.safe=safe;
    }
}
