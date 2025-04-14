package com.mickaelsouzadev.volcanoesfinder.service.http;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GlobalVolcanismService extends HttpClientService {

    private static final String BASE_URI = "https://volcano.si.edu/";
    private static final String HOLOCENE_VOLCANOES_PAGE = "volcanolist_holocene.cfm";

    public String getHoloceneVolcanoesList() {
        return this.get(HOLOCENE_VOLCANOES_PAGE);
    }

    protected void setClient() {
        this.client = WebClient.builder()
                .baseUrl(BASE_URI)
                .codecs(config -> config.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)) // 10 MB
                .build();
    }
}