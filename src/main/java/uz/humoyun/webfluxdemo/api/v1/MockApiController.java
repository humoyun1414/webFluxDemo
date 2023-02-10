package uz.humoyun.webfluxdemo.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import uz.humoyun.webfluxdemo.model.ConsumerDto;
import uz.humoyun.webfluxdemo.service.MockApiService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/mock/v1")
public class MockApiController {

    private final MockApiService mockApiService;

    public MockApiController(MockApiService mockApiService) {
        this.mockApiService = mockApiService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Flux<ConsumerDto>all(){
        return mockApiService.findToMockApiResponse();
    }



}
