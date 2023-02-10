package uz.humoyun.webfluxdemo.context.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "application")
@RefreshScope
public class ApplicationProperties {
    String mockApi;

    public String getMockApi() {
        return mockApi;
    }

    public void setMockApi(String mockApi) {
        this.mockApi = mockApi;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
                "mockApi=" + mockApi +
                '}';
    }


}
