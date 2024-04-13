package me.abel.qywechatapi.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(
    prefix = "qywechat"
)
public class QyWechatApiProperties {
    private String corpid;

    private String host;

    private int interval;

    private CustomerProperties customer;

    private MessageProperties message;

    private Map<String, AppProperties> app = new HashMap<>();

    private Map<String, String> url = new HashMap<>();

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public CustomerProperties getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerProperties customer) {
        this.customer = customer;
    }

    public MessageProperties getMessage() {
        return message;
    }

    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    public Map<String, AppProperties> getApp() {
        return app;
    }

    public void setApp(Map<String, AppProperties> app) {
        this.app = app;
    }

    public Map<String, String> getUrl() {
        return url;
    }

    public void setUrl(Map<String, String> url) {
        this.url = url;
    }
}