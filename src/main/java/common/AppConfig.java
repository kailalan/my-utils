package common;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="sync.ldap")
public class AppConfig {
    private String authentication;
    private String principal;
    private String credentials;
    private String url;

}
