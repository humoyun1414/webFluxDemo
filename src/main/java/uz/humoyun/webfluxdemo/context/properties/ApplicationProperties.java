package uz.humoyun.webfluxdemo.context;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("application")
public record ApplicationProperties(@NestedConfigurationProperty MockApiProperties mockApi) {

}
