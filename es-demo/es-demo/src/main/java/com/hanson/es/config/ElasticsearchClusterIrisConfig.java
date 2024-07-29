package com.hanson.es.config;

import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

import java.time.Duration;


/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ElasticsearchClusterIrisConfig
 * @Description TODO
 * @date 2024/7/22 20:16
 **/
@Configuration
public class ElasticsearchClusterIrisConfig extends AbstractElasticsearchConfiguration{

    @Value("${spring.elasticsearch.rest.iris.uris}")
    private String uris;
    @Value("${spring.elasticsearch.rest.iris.username}")
    private String username;
    @Value("${spring.elasticsearch.rest.iris.password}")
    private String password;

    @Override
    @Bean(name = "irisElasticsearchClient", destroyMethod = "close")
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo(uris)
                .withBasicAuth(username, password)
                .withConnectTimeout(Duration.ofSeconds(60))
                .withSocketTimeout(Duration.ofSeconds(60))
                .withHttpClientConfigurer(httpClientBuilder -> httpClientBuilder
                        .setDefaultIOReactorConfig(IOReactorConfig.custom().setSoKeepAlive(true).build())
                        .setKeepAliveStrategy((httpResponse, httpContext) -> 1000 * 60 * 3)
                )
                .build();
        return RestClients.create(configuration).rest();
    }

    @Override
    @Bean(name = {"irisElasticsearchRestTemplate"})
    public ElasticsearchRestTemplate elasticsearchOperations(ElasticsearchConverter elasticsearchConverter,
                                                             @Qualifier("irisElasticsearchClient") RestHighLevelClient elasticsearchClient) {
        return new ElasticsearchRestTemplate(elasticsearchClient, elasticsearchConverter);
    }
}