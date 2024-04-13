package me.abel.qywechatapi.handlers;

import me.abel.qywechatapi.autoconfigure.AppProperties;
import me.abel.qywechatapi.autoconfigure.QyWechatApiProperties;
import org.springframework.stereotype.Component;

@Component
public class ApiDefinitionHandler {

    private final QyWechatApiProperties properties;

    public ApiDefinitionHandler(QyWechatApiProperties properties) {
        this.properties = properties;
    }

    public String getCorpId() {
        return properties.getCorpid();
    }

    public String getServerHost() {
        return properties.getHost();
    }

    public String getRealUrl(String urlType) {
        return properties.getUrl().get(urlType);
    }

    public AppProperties getAppProperties(String appType) {
        return properties.getApp().get(appType);
    }
}

