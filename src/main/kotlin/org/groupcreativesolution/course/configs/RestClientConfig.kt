package org.groupcreativesolution.course.configs

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class RestClientConfig {

    @Bean
    fun restClient(builder: RestClient.Builder): RestClient = builder
        .baseUrl("http://localhost:8087")
        .build()
}