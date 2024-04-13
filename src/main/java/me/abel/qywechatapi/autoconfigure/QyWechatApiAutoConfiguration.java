package me.abel.qywechatapi.autoconfigure;

import me.abel.qywechatapi.handlers.*;
import me.abel.qywechatapi.utils.HttpClientUtil;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;

@Configuration
@EnableConfigurationProperties({QyWechatApiProperties.class})
public class QyWechatApiAutoConfiguration implements InitializingBean {

    private final QyWechatApiProperties properties;

    public QyWechatApiAutoConfiguration(QyWechatApiProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(HttpClient.class)
    public HttpClient httpClient(){
        return getPoolingHttpClient();
    }

    @Bean
    @ConditionalOnMissingBean(HttpClientUtil.class)
    public HttpClientUtil httpClientUtil(){
        return new HttpClientUtil(httpClient());
    }

    private PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
        try {
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustAllStrategy()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, (s, sslSession) -> true);

            ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", plainsf)
                    .register("https", sslsf)
                    .build();
            PoolingHttpClientConnectionManager poolConnManager =
                    new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // Increase max total connection to 200
            poolConnManager.setMaxTotal(20000);
            // Increase default max connection per route to 20
            poolConnManager.setDefaultMaxPerRoute(20000);
            SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(Timeout.ofMilliseconds(100000)).build();
            poolConnManager.setDefaultSocketConfig(socketConfig);

            ConnectionConfig connectionConfig = ConnectionConfig.custom().setConnectTimeout(Timeout.ofMilliseconds(30000)).build();
            poolConnManager.setDefaultConnectionConfig(connectionConfig);
            return poolConnManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CloseableHttpClient getPoolingHttpClient() {
        PoolingHttpClientConnectionManager poolConnManager = getHttpClientConnectionManager();

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(Timeout.ofMilliseconds(30000))
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolConnManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
        /**
         * PoolStats
         *
         * leased ：the number of persistent connections tracked by the connection manager currently being used to execute requests.
         * available ：the number idle persistent connections.
         * pending : the number of connection requests being blocked awaiting a free connection.
         * max: the maximum number of allowed persistent connections.
         */
        if(poolConnManager!=null&&poolConnManager.getTotalStats()!=null) {
            // ("now client pool {}", poolConnManager.getTotalStats().toString());
        }
        return httpClient;
    }

    @Bean
    @ConditionalOnMissingBean(ApiDefinitionHandler.class)
    public ApiDefinitionHandler urlDefinitionHandler(){
        return new ApiDefinitionHandler(properties);
    }

    @Bean
    @ConditionalOnMissingBean(AccessTokenHandler.class)
    public AccessTokenHandler accessTokenHandler(){
        return new AccessTokenHandler(properties, httpClientUtil());
    }

    @Bean
    @ConditionalOnMissingBean(ContactWayHandler.class)
    public ContactWayHandler contactWayHandler(){
        return new ContactWayHandler(accessTokenHandler(), urlDefinitionHandler(), httpClientUtil());
    }

    @Bean
    @ConditionalOnMissingBean(ExternalContactHandler.class)
    public ExternalContactHandler externalContactHandler(){
        return new ExternalContactHandler(accessTokenHandler(), urlDefinitionHandler(), httpClientUtil());
    }

    @Bean
    @ConditionalOnMissingBean(MessageHandler.class)
    public MessageHandler messageHandler(){
        return new MessageHandler(accessTokenHandler(), urlDefinitionHandler(), httpClientUtil());
    }

    @Bean
    @ConditionalOnMissingBean(Oauth2Handler.class)
    public Oauth2Handler oauth2Handler(){
        return new Oauth2Handler(accessTokenHandler(), urlDefinitionHandler(), httpClientUtil());
    }

    @Bean
    @ConditionalOnMissingBean(UserHandler.class)
    public UserHandler userHandler(){
        return new UserHandler(accessTokenHandler(), urlDefinitionHandler(), httpClientUtil());
    }

    @Override
    public void afterPropertiesSet() {
        this.accessTokenInitial();
    }

    private void accessTokenInitial() {
        accessTokenHandler().initial();
    }
}
