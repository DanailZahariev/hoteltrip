package bg.hoteltrip.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class HotelTripUserDetails implements UserDetails {


    private final Long id;
    private final String password;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Collection<GrantedAuthority> authorities;

    public HotelTripUserDetails(Long id, String password,
                                String email,
                                String firstName,
                                String lastName,
                                Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
