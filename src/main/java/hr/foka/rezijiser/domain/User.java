package hr.foka.rezijiser.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Table
public class User extends CommonTable implements UserDetails{

    private String name;

    private String surname;

    private String email;

    private Boolean enabled;

    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("any"));
    }

    @Override
    //Will return user's email.
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

    public String getPassword(){
        return password;
    }

    public void setPassword(String passwordHash){
        this.password = passwordHash;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("User [");
        builder.append(super.toString());
        builder.append("name=").append(name);
        builder.append(", surname=").append(surname);
        builder.append(", email=").append(email);
        builder.append(", enabled=").append(enabled);
        builder.append("]");
        return builder.toString();
    }

}