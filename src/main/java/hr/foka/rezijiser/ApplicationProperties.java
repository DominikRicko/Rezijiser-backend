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
    private Integer reminderDays;
    private String reminderTitle;
    private String reminderMessageTemplate;
    private String lateTitle;
    private String lateMessageTemplate;

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

    public Long getAccessTokenValidityMiliseconds() {
        return access_token_validity_minutes * 60 * 1000;
    }

    public String getIssuer() {
        return this.issuer.trim();
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public long getAccess_token_validity_minutes() {
        return this.access_token_validity_minutes;
    }

    public void setAccess_token_validity_minutes(long access_token_validity_minutes) {
        this.access_token_validity_minutes = access_token_validity_minutes;
    }

    public String getSigning_key() {
        return this.signing_key;
    }

    public void setSigning_key(String signing_key) {
        this.signing_key = signing_key;
    }

    public String getToken_prefix() {
        return this.token_prefix;
    }

    public void setToken_prefix(String token_prefix) {
        this.token_prefix = token_prefix;
    }

    public String getHeader_string() {
        return this.header_string;
    }

    public void setHeader_string(String header_string) {
        this.header_string = header_string;
    }

    public Integer getReminderDays() {
        return this.reminderDays;
    }

    public void setReminderDays(Integer reminderDays) {
        this.reminderDays = reminderDays;
    }

    public String getReminderTitle() {
        return this.reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public String getReminderMessageTemplate() {
        return this.reminderMessageTemplate;
    }

    public void setReminderMessageTemplate(String reminderMessageTemplate) {
        this.reminderMessageTemplate = reminderMessageTemplate;
    }

    public String getLateTitle() {
        return this.lateTitle;
    }

    public void setLateTitle(String lateTitle) {
        this.lateTitle = lateTitle;
    }

    public String getLateMessageTemplate() {
        return this.lateMessageTemplate;
    }

    public void setLateMessageTemplate(String lateMessageTemplate) {
        this.lateMessageTemplate = lateMessageTemplate;
    }

}