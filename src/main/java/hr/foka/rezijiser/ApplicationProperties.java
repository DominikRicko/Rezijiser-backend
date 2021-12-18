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
    private String issuer;

    public long getAccessTokenValidityMinutes() {
        return access_token_validity_minutes;
    }
    public void setAccessTokenValidityMinutes(long access_token_validity_minutes) {
        this.access_token_validity_minutes = access_token_validity_minutes;
    }
    public String getSigningKey() {
        return signing_key;
    }
    public void setSigningKey(String signing_key) {
        this.signing_key = signing_key;
    }
    public String getTokenPrefix() {
        return token_prefix;
    }
    public void setTokenPrefix(String token_prefix) {
        this.token_prefix = token_prefix;
    }
    public String getHeaderString() {
        return header_string;
    }
    public void setHeaderString(String header_string) {
        this.header_string = header_string;
    }
    public Long getAccessTokenValidityMiliseconds(){
        return access_token_validity_minutes*60*1000;
    }

    public String getIssuer() {
        return this.issuer.trim();
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
    
}
