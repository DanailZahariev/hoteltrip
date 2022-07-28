package bg.hoteltrip.web.interceptors;

import org.apache.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if ("/users/login".equals(urlPathHelper.getLookupPathForRequest(request)) && isAuthenticated()) {
            String encodedRedirectURL = response.encodeRedirectURL(
                    request.getContextPath() + "/");
            response.setStatus(HttpStatus.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", encodedRedirectURL);

            return false;
        }

        if ("/users/register".equals(urlPathHelper.getLookupPathForRequest(request)) && isAuthenticated()) {
            String encodedRedirectURL = response.encodeRedirectURL(
                    request.getContextPath() + "/");
            response.setStatus(HttpStatus.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", encodedRedirectURL);

            return false;
        }
        return true;
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
