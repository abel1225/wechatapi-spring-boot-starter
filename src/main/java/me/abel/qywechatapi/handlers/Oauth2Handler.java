
package me.abel.qywechatapi.handlers;

import com.alibaba.fastjson2.JSON;
import me.abel.qywechatapi.autoconfigure.UrlDefinitionConstant;
import me.abel.qywechatapi.dto.wechat.out.Oauth2Token;
import me.abel.qywechatapi.utils.HttpClientUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Oauth2Handler {

    private final AccessTokenHandler accessTokenHandler;
    private final ApiDefinitionHandler apiDefinitionHandler;
    private final HttpClientUtil httpClientUtil;

    public Oauth2Handler(AccessTokenHandler accessTokenHandler, ApiDefinitionHandler apiDefinitionHandler, HttpClientUtil httpClientUtil) {
        this.accessTokenHandler = accessTokenHandler;
        this.apiDefinitionHandler = apiDefinitionHandler;
        this.httpClientUtil = httpClientUtil;
    }

    public static final String SCOPE = "snsapi_base";
    private static final String OAUTH2_CODE_DENY = "authdeny";

    public final String getRedirectUri (){
        return apiDefinitionHandler.getServerHost() + "oauth2/";
    }

    public final String getCodeRequest(String module, String state) {
        String getCodeRequest = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.REDIRECT);
        getCodeRequest = getCodeRequest.replace("APPID", urlEnodeUTF8(apiDefinitionHandler.getCorpId()));
        getCodeRequest = getCodeRequest.replace("REDIRECT_URI", urlEnodeUTF8(getRedirectUri() + module));
        if (!"".equals(state)) {
            getCodeRequest = getCodeRequest.replace("STATE", state);
        }
        return getCodeRequest.replace("SCOPE", SCOPE);
    }

    public final String getCodeRequest(String module) {
        return getCodeRequest(module, "");
    }

    public final String getServerHost (){
        return apiDefinitionHandler.getServerHost();
    }

    public final String getCode2UserUrl (String code, String appType) {
        String code2userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.CODE2USER);
        code2userUrl = code2userUrl.replace("CODE", code);
        code2userUrl = code2userUrl.replace("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        Integer agentId = apiDefinitionHandler.getAppProperties(appType).getAgentid();
        code2userUrl = code2userUrl.replace("AGENTID", String.valueOf(agentId));
        return code2userUrl;
    }

    public Oauth2Token getOpenIdByCode(String code, String appType) throws IOException {
		String back = httpClientUtil.getResponce(getCode2UserUrl(code, appType));
        return JSON.parseObject(back, Oauth2Token.class);
    }

    public final String getUserInfoUrl (String userId, String appType) {
        String code2userUrl = apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.USER_GET);
        code2userUrl = code2userUrl.replace("ACCESS_TOKEN", accessTokenHandler.getAccessToken(appType));
        code2userUrl = code2userUrl.replace("USERID", String.valueOf(userId));
        return code2userUrl;
    }

    public Oauth2Token getMobileByUserId(String userId, String appType) throws IOException {
        String back = httpClientUtil.getResponce(getUserInfoUrl(userId, appType));
        return JSON.parseObject(back, Oauth2Token.class);
    }

    public final boolean isValidOauth2Code(String code) {
        return !OAUTH2_CODE_DENY.equals(code);
    }

    public String getModuleURI(String module) {
        if (!"".equals(module)) {
            return module.replace("-", "/");
        }
        return "";
    }

    public final String urlEnodeUTF8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}