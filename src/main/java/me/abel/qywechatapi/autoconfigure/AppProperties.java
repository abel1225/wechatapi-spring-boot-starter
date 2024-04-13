package me.abel.qywechatapi.autoconfigure;

public class AppProperties {
    private Integer agentid;

    private String corpsecret;

    private String mainhost;

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public String getCorpsecret() {
        return corpsecret;
    }

    public void setCorpsecret(String corpsecret) {
        this.corpsecret = corpsecret;
    }

    public String getMainhost() {
        return mainhost;
    }

    public void setMainhost(String mainhost) {
        this.mainhost = mainhost;
    }
}