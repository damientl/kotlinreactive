package org.example.infrastructure

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.mockserver.integration.ClientAndServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MockServerConfig {
    private lateinit var mockServer: ClientAndServer

    @PostConstruct
    fun startServer() {
        mockServer = ClientAndServer.startClientAndServer(8081)
    }

    @PreDestroy
    fun stopServer() {
        mockServer.stop()
    }

    @Bean
    open fun mockServerClient(): ClientAndServer = mockServer
}
