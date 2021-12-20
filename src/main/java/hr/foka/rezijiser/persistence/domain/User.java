package hr.foka.rezijiser.persistence.domain;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hr.foka.rezijiser.persistence.service.ZonedDateTimeConverter;

@Entity
public class User implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3376147386529824339L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String email;

    private Boolean enabled;

    private String password;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(nullable = true, insertable = false, updatable = false)
    private ZonedDateTime timeCreated;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(nullable = true, insertable = false, updatable = false)
    private ZonedDateTime timeModified;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public ZonedDateTime getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(ZonedDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public ZonedDateTime getTimeModified() {
        return this.timeModified;
    }

    public void setTimeModified(ZonedDateTime timeModified) {
        this.timeModified = timeModified;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("any"));
    }

    @Override
    // Will return user's email.
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(User.class.getName());
        builder.append(" [");
        builder.append("name=").append(name);
        builder.append(", surname=").append(surname);
        // builder.append(", password=").append(password);
        builder.append(", email=").append(email);
        builder.append(", enabled=").append(enabled);
        builder.append(", timeCreated").append(timeCreated);
        builder.append(", timeModified").append(timeModified);
        builder.append("]");
        return builder.toString();
    }

}