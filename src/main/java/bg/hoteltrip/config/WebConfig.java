//package bg.hoteltrip.config;
//
//import bg.hoteltrip.web.interceptors.UserInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//
//    private final UserInterceptor userInterceptor;
//
//    public WebConfig(UserInterceptor userInterceptor) {
//        this.userInterceptor = userInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(userInterceptor);
//    }
//}
