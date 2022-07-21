package bg.hoteltrip.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "admin")
public class AdminProperties {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    /**
     * @param email the email for the admin entity.
     * @return this
     */
    public AdminProperties setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @param password the password for the admin entity.
     * @return this
     */
    public AdminProperties setPassword(String password) {
        this.password = password;
        return this;
    }
}
