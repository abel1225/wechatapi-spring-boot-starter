package me.abel.qywechatapi.handlers;


import com.alibaba.fastjson2.JSON;
import me.abel.qywechatapi.autoconfigure.UrlDefinitionConstant;
import me.abel.qywechatapi.dto.wechat.out.DepartmetUserResult;
import me.abel.qywechatapi.dto.wechat.out.TagUserResult;
import me.abel.qywechatapi.utils.HttpClientUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserHandler {

    private final AccessTokenHandler accessTokenHandler;
    private final ApiDefinitionHandler apiDefinitionHandler;
    private final HttpClientUtil httpClientUtil;

    public UserHandler(AccessTokenHandler accessTokenHandler, ApiDefinitionHandler apiDefinitionHandler, HttpClientUtil httpClientUtil) {
        this.accessTokenHandler = accessTokenHandler;
        this.apiDefinitionHandler = apiDefinitionHandler;
        this.httpClientUtil = httpClientUtil;
    }

    public String userCreate (String param, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.USER_CREATE);
        userUrl = userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        return httpClientUtil.postJson(userUrl, param);
    }

    public String userGet (String userId, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.USER_GET);
        userUrl = userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        userUrl = userUrl.replaceAll("USERID", userId);
        return httpClientUtil.getResponce(userUrl);
    }

    public String userUpdate (String param, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.USER_UPDATE);
        userUrl = userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        return httpClientUtil.postJson(userUrl, param);
    }

    public String userDelete (String userId, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.USER_DELETE);
        userUrl = userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        userUrl = userUrl.replaceAll("USERID", userId);
        return httpClientUtil.getResponce(userUrl);
    }

    public String userBatchDelete (List<String> useridlist, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.USER_BATCH_DELETE);
        userUrl = userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        Map<String, List<String>> params = new HashMap<>();
        params.put("useridlist", useridlist);
        return httpClientUtil.postJson(userUrl, JSON.toJSONString(params));
    }

    public String userBaseList (String departmentId, String fetchChild, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.USER_BASE_LIST);
        return getUserList(departmentId, fetchChild, userUrl, appType);
    }

    public DepartmetUserResult userDetailList (String departmentId, String fetchChild, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.USER_DETAIL_LIST);
        String back = getUserList(departmentId, fetchChild, userUrl, appType);
        return JSON.parseObject(back, DepartmetUserResult.class);
    }

    public TagUserResult tagDetailList (String tagid, String appType) throws IOException {
        String tagUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.TAG_GET);
        String back = getTagList(tagid, tagUrl, appType);
        return JSON.parseObject(back, TagUserResult.class);
    }

    public String convertToOpenid (String userId, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.CONVERT_TO_OPENID);
        userUrl = userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        Map<String, String> params = new HashMap<>();
        params.put("userid", userId);
        return httpClientUtil.postJson(userUrl, JSON.toJSONString(params));
    }

    public String convertToUserid (String openId, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.CONVERT_TO_USERID);
        userUrl = userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        Map<String, String> params = new HashMap<>();
        params.put("openid", openId);
        return httpClientUtil.postJson(userUrl, JSON.toJSONString(params));
    }

    /**
     * 二次校验
     * @param userId
     * @return
     */
    public String authSucc (String userId, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.AUTH_SUCC);
        userUrl = userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        userUrl = userUrl.replaceAll("USERID", userId);
        return httpClientUtil.getResponce(userUrl);
    }

    /**
     *
     * @param userList    成员ID列表, 最多支持1000个。
     * @return
     */
    public String batchInvite (List<String> userList, String appType) throws IOException {
        String userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.BATCH_INVITE);
        userUrl = userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        Map<String, List<String>> params = new HashMap<>();
        params.put("user", userList);
        return httpClientUtil.postJson(userUrl, JSON.toJSONString(params));
    }

    private String getUserList(String departmentId, String fetchChild, String userUrl, String appType) throws IOException {
        String targetUrl=userUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        targetUrl=targetUrl.replaceAll("DEPARTMENT_ID", departmentId);
        if (!"".equals(fetchChild)) {
            targetUrl=targetUrl.replaceAll("FETCH_CHILD", fetchChild);
        }
        return httpClientUtil.getResponce(targetUrl);
    }

    private String getTagList(String tagid, String tagUrl, String appType) throws IOException {
        String targetUrl=tagUrl.replaceAll("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        targetUrl=targetUrl.replaceAll("TAGID", tagid);
        return httpClientUtil.getResponce(targetUrl);
    }
}
