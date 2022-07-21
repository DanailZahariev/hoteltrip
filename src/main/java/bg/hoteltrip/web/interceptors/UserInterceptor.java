//package bg.hoteltrip.web.interceptors;
//
//import bg.hoteltrip.service.HotelTripDetailsService;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@Component
//public class UserInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        addModelDetails(request.getSession());
//        return true;
//    }
//
//
//    private void addModelDetails(HttpSession session) {
//        String loggedUser = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();
//        session.setAttribute("loggedUserEmail", loggedUser);
//    }
//}
