package com.mickaelsouzadev.volcanoesfinder.service.http;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
abstract class HttpClientService implements HttpClientServiceInterface {
    protected WebClient client;

    protected HttpClientService() {
        this.setClient();
    }

    @Override
    public String get(String uri) {
        return client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    protected abstract void setClient();
}
