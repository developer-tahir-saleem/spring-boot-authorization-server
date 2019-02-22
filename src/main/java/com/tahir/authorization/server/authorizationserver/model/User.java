package com.tahir.authorization.server.authorizationserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tahir on 2/11/19.
 */

@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@DiscriminatorValue("childClasss")
@ToString
public class User extends BaseModel implements UserDetails {


    @NotNull
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    private String type;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;
    //    private boolean active;
//    private boolean status;
//    private int flag;
    private boolean accountNonExpired, accountNonLocked, credentialsNonExpired, enabled;

//
//    public boolean isFlag(int i){
//        if (i==1){
//            return  true;
//        }else{
//            return false;
//        }
//    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

//    public void grantAuthority(String authority) {
//        if ( roles == null ) roles = new ArrayList<>();
//        roles.add(authority.);
//    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

}
