package me.abel.qywechatapi.handlers;

import com.alibaba.fastjson2.JSON;
import me.abel.qywechatapi.autoconfigure.UrlDefinitionConstant;
import me.abel.qywechatapi.dto.wechat.in.MessageContentDto;
import me.abel.qywechatapi.dto.wechat.in.MessageDto;
import me.abel.qywechatapi.dto.wechat.in.MessageSendDto;
import me.abel.qywechatapi.utils.HttpClientUtil;

import java.io.IOException;

public class MessageHandler {

    private final AccessTokenHandler accessTokenHandler;
    private final ApiDefinitionHandler apiDefinitionHandler;
    private final HttpClientUtil httpClientUtil;

    public MessageHandler(AccessTokenHandler accessTokenHandler, ApiDefinitionHandler apiDefinitionHandler, HttpClientUtil httpClientUtil) {
        this.accessTokenHandler = accessTokenHandler;
        this.apiDefinitionHandler = apiDefinitionHandler;
        this.httpClientUtil = httpClientUtil;
    }

    public String send(String appType, MessageDto messageDto) throws IOException {
    	Integer agentId = apiDefinitionHandler.getAppProperties(appType).getAgentid();
        String msgContent = JSON.toJSONString(new MessageSendDto(messageDto.getToUser(), agentId, new MessageContentDto(messageDto.getContent())));
        String sendUrl=getSendUrl(msgContent);
        return httpClientUtil.postJson(sendUrl, msgContent);
    }

    public String sendCard(String appType, MessageDto messageDto) throws IOException {
        Integer agentId = apiDefinitionHandler.getAppProperties(appType).getAgentid();
        String accessToken = accessTokenHandler.getAccessToken(messageDto.getAppType());

        return sendCard(messageDto, agentId, accessToken);
    }

    public String sendCard(MessageDto messageDto, int agentId, String accessToken) throws IOException {
        String msgContent=getMsgContent(messageDto, agentId);
        String sendUrl=getSendUrl(accessToken);
        return httpClientUtil.postJson(sendUrl, msgContent);
    }

    private String getSendUrl(String accessToken) {
        String sendUrl=apiDefinitionHandler.getRealUrl(UrlDefinitionConstant.MSG_SEND);
        sendUrl=sendUrl.replaceAll("ACCESS_TOKEN", accessToken);
        return sendUrl;
    }

    private String getMsgContent(MessageDto messageDto, Integer agentId) {
        MessageContentDto contentDto = new MessageContentDto();
        contentDto.setTitle(messageDto.getTitle());
        contentDto.setDescription(messageDto.getContent());
        contentDto.setUrl(messageDto.getUrl());
        contentDto.setBtntxt("点击查看");

        MessageSendDto sendDto = new MessageSendDto();
        sendDto.setMsgtype("textcard");
        sendDto.setAgentid(agentId);
        sendDto.setTouser(messageDto.getToUser());
        sendDto.setTextcard(contentDto);

        return JSON.toJSONString(sendDto);
    }
}
