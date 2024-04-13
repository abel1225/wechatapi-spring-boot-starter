package me.abel.qywechatapi.handlers;

import com.alibaba.fastjson2.JSON;
import me.abel.qywechatapi.autoconfigure.UrlDefinitionConstant;
import me.abel.qywechatapi.dto.wechat.in.ContactWayDto;
import me.abel.qywechatapi.utils.HttpClientUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ContactWayHandler {

    private final AccessTokenHandler accessTokenHandler;
    private final ApiDefinitionHandler apiDefinitionHandler;
    private final HttpClientUtil httpClientUtil;

    public ContactWayHandler(AccessTokenHandler accessTokenHandler, ApiDefinitionHandler apiDefinitionHandler, HttpClientUtil httpClientUtil) {
        this.accessTokenHandler = accessTokenHandler;
        this.apiDefinitionHandler = apiDefinitionHandler;
        this.httpClientUtil = httpClientUtil;
    }

    public String addContactway (ContactWayDto dto, String appType) throws IOException {
        String contactwayUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.ADD_CONTACT_WAY);
        contactwayUrl = contactwayUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));

        return httpClientUtil.postJson(contactwayUrl, JSON.toJSONString(dto));
    }

    public String getContactway (String configId, String appType) throws IOException {
        String contactwayUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.GET_CONTACT_WAY);
        contactwayUrl = contactwayUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        return postJsonParams(configId, contactwayUrl);
    }

    public String updateContactway (ContactWayDto dto, String appType) throws IOException {
        String contactwayUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.UPDATE_CONTACT_WAY);
        contactwayUrl = contactwayUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        return httpClientUtil.postJson(contactwayUrl, JSON.toJSONString(dto));
    }

    public String delContactway (String configId, String appType) throws IOException {
        String contactwayUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.DEL_CONTACT_WAY);
        contactwayUrl = contactwayUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        return postJsonParams(configId, contactwayUrl);
    }

    private String postJsonParams(String configId, String contactwayUrl) throws IOException {
        Map<String, String> params=new HashMap<>();
        params.put("config_id", configId);
        return httpClientUtil.postJson(contactwayUrl, JSON.toJSONString(params));
    }

}
