package uz.humoyun.webfluxdemo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import uz.humoyun.webfluxdemo.context.properties.ApplicationProperties;
import uz.humoyun.webfluxdemo.model.ConsumerDto;

@Service
public class MockApiService {
    private final WebClient webClient;
    private final ApplicationProperties applicationProperties;

    public MockApiService(final ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.webClient = WebClient.builder().build();
    }

    public Flux<ConsumerDto> findToMockApiResponse() {
        return webClient
                .get()
                .uri(applicationProperties.getMockApi())
                .retrieve()
                .bodyToFlux(ConsumerDto.class);
    }

}
