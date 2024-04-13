package me.abel.qywechatapi.handlers;

import com.alibaba.fastjson2.JSON;
import me.abel.qywechatapi.autoconfigure.UrlDefinitionConstant;
import me.abel.qywechatapi.utils.HttpClientUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExternalContactHandler {

    private final AccessTokenHandler accessTokenHandler;
    private final ApiDefinitionHandler apiDefinitionHandler;
    private final HttpClientUtil httpClientUtil;

    public ExternalContactHandler(AccessTokenHandler accessTokenHandler, ApiDefinitionHandler apiDefinitionHandler, HttpClientUtil httpClientUtil) {
        this.accessTokenHandler = accessTokenHandler;
        this.apiDefinitionHandler = apiDefinitionHandler;
        this.httpClientUtil = httpClientUtil;
    }

    public String getCustomerContacts (String appType) throws IOException {
        String contactsUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.CUSTOMER_CONTACTS);
        contactsUrl = contactsUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        return httpClientUtil.getResponce(contactsUrl);
    }

    public String getExternalContactList(String appType, String userId) throws IOException {
        String contactsUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.CUSTOMER_CONTACT_LIST);
        contactsUrl = contactsUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        contactsUrl = contactsUrl.replaceAll("USERID", userId);
        return httpClientUtil.getResponce(contactsUrl);
    }

    public String getExternalContactDetail(String appType, String externalUserid) throws IOException {
        String contactsUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.CUSTOMER_CONTACT_DETAIL);
        contactsUrl = contactsUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        contactsUrl = contactsUrl.replaceAll("EXTERNAL_USERID", externalUserid);
        return httpClientUtil.getResponce(contactsUrl);
    }

    public String getUnassignedCustomers (String appType, int pageId, int pageSize) throws IOException {
        String contactsUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.UNASSIGNED_CUSTOMERS);
        contactsUrl = contactsUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        Map<String, String> params = new HashMap<>();
        params.put("page_id", String.valueOf(pageId));
        params.put("page_size", String.valueOf(pageSize));
        return httpClientUtil.postJson(contactsUrl, JSON.toJSONString(params));
    }

    /**
     *
     * @param externalUserid  外部联系人的userid，注意不是企业成员的帐号
     * @param handoverUserid  离职成员的userid
     * @param takeoverUserid  接替成员的userid
     * external_userid必须是handover_userid的客户（即配置了客户联系功能的成员所添加的联系人）
     * @return
     */
    public String transferExternalContact (String appType, String externalUserid, String handoverUserid, String takeoverUserid) throws IOException {
        String contactsUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.TRANSFER_EXTERNAL_CONTACT);
        contactsUrl = contactsUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        Map<String, String> params = new HashMap<>();
        params.put("external_userid", externalUserid);
        params.put("handover_userid", handoverUserid);
        params.put("takeover_userid", takeoverUserid);
        return httpClientUtil.postJson(contactsUrl, JSON.toJSONString(params));
    }
}
