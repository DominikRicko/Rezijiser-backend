package hr.foka.rezijiser;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rezijiser")
public class ApplicationProperties {

    private long access_token_validity_minutes;
    private String signing_key;
    private String token_prefix;
    private String header_string;

    public long getAccess_token_validity_minutes() {
        return access_token_validity_minutes;
    }
    public void setAccess_token_validity_minutes(long access_token_validity_minutes) {
        this.access_token_validity_minutes = access_token_validity_minutes;
    }
    public String getSigning_key() {
        return signing_key;
    }
    public void setSigning_key(String signing_key) {
        this.signing_key = signing_key;
    }
    public String getToken_prefix() {
        return token_prefix;
    }
    public void setToken_prefix(String token_prefix) {
        this.token_prefix = token_prefix;
    }
    public String getHeader_string() {
        return header_string;
    }
    public void setHeader_string(String header_string) {
        this.header_string = header_string;
    }
    public Long getAccess_token_validity_miliseconds(){
        return access_token_validity_minutes*60*1000;
    }

    
}
