package bg.hoteltrip.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cloudinary")
public class CloudinaryProperties {

    private String cloudName;
    private String apiKey;
    private String apiSecret;


    public String getCloudName() {
        return cloudName;
    }

    /**
     * Sets the cloud name associated with the cloudinary account.
     *
     * @param cloudName the cloud associated with the cloudinary account.
     * @return this
     */
    public CloudinaryProperties setCloudName(String cloudName) {
        this.cloudName = cloudName;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public CloudinaryProperties setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public CloudinaryProperties setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
        return this;
    }
}
