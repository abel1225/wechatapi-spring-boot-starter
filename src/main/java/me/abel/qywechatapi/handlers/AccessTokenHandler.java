package me.abel.qywechatapi.handlers;

import com.alibaba.fastjson2.JSONObject;
import me.abel.qywechatapi.autoconfigure.AppProperties;
import me.abel.qywechatapi.autoconfigure.QyWechatApiProperties;
import me.abel.qywechatapi.autoconfigure.UrlDefinitionConstant;
import me.abel.qywechatapi.dto.wechat.out.AccessToken;
import me.abel.qywechatapi.utils.HttpClientUtil;

import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AccessTokenHandler {

    private final QyWechatApiProperties properties;
    private final HttpClientUtil httpClientUtil;

    private static ConcurrentMap<String, AtomicStampedReference<AccessToken>> ACCESSTOKEN_MAP = new ConcurrentHashMap<>();

    public AccessTokenHandler(QyWechatApiProperties properties, HttpClientUtil httpClientUtil) {
        this.properties = properties;
        this.httpClientUtil = httpClientUtil;
    }

    /**
     * 初始化
     * 启用Timertask 2小时执行一次
     */
    public void initial () {
        Map<String, AppProperties> appMap = properties.getApp();
        if (null != appMap && !appMap.isEmpty()) {
            for (Map.Entry<String, AppProperties> entry : appMap.entrySet()) {
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        AtomicStampedReference<AccessToken> accessToken = new AtomicStampedReference<>(null, 0);
                        accessToken.compareAndSet(accessToken.getReference(), getRemote(entry.getKey(), entry.getValue()), accessToken.getStamp(),accessToken.getStamp() + 1);
                        ACCESSTOKEN_MAP.put(entry.getKey(), accessToken);
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 0,2 * 60 * 60 * 1000);
            }
        }
    }

    /**
     * 方法注释： 获取AccessToken
     * 修改内容： 新增
     * 修改时间： 2018年3月7日 下午5:25:42
     * @return
     */
    public String getAccessToken(String appType) {
        Map<String, AppProperties> appMap = properties.getApp();
        if (null != appMap && !appMap.isEmpty()) {
            AppProperties appProperties = appMap.get(appType);
            AtomicStampedReference<AccessToken> accessTokenAtomic = ACCESSTOKEN_MAP.get(appType);
            if (null != appProperties) {
                if (null == accessTokenAtomic.getReference()) {
                    accessTokenAtomic = new AtomicStampedReference<>(null, 0);
                    accessTokenAtomic.compareAndSet(accessTokenAtomic.getReference(), getRemote(appType, appProperties), accessTokenAtomic.getStamp(), accessTokenAtomic.getStamp() + 1);
                    ACCESSTOKEN_MAP.put(appType, accessTokenAtomic);
                    return accessTokenAtomic.getReference().getAccess_token();
                }
                if (accessTokenAtomic.getReference().isExpired()) {
                    accessTokenAtomic.compareAndSet(accessTokenAtomic.getReference(), getRemote(appType, appProperties), accessTokenAtomic.getStamp(), accessTokenAtomic.getStamp() + 1);
                    ACCESSTOKEN_MAP.put(appType, accessTokenAtomic);
                    return accessTokenAtomic.getReference().getAccess_token();
                }
            }
        }
        throw new RuntimeException("exception when get accesstoken");
    }

    /**
     * 方法注释： 获取微信API基础接口access_token
     * 修改内容： 新增
     * 修改时间： 2018年3月7日 下午4:54:59
     *
     * @param appType
     * @return
     */
    private AccessToken getRemote(String appType, AppProperties appProperties) {
        String accessTokenUri = properties.getUrl().get(UrlDefinitionConstant.ACCESS_TOKEN);
        accessTokenUri = accessTokenUri.replace("APPID", properties.getCorpid());
        accessTokenUri = accessTokenUri.replace("SECRET", appProperties.getCorpsecret());
        try {
            String tokenJson = httpClientUtil.getResponce(accessTokenUri);
            if (!"".equals(tokenJson)) {
                AccessToken token = JSONObject.parseObject(tokenJson, AccessToken.class);
                if (!"".equals(token.getAccess_token())) {
                    token.init();
                    return token;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("exception when get accesstoken");
    }
}

