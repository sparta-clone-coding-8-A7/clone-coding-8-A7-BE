package hanghaeclone8a7.twotead.aop;

import hanghaeclone8a7.twotead.domain.Member;
import hanghaeclone8a7.twotead.exception.LoginFailException;
import hanghaeclone8a7.twotead.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Aspect
@RequiredArgsConstructor
@Component
public class LoginCheckAop {


    private final TokenProvider tokenProvider;

    @Before("@annotation(hanghaeclone8a7.twotead.annotation.LoginCheck)")
    public void loginCheck(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        System.out.println("Authorization = " + request.getHeader("Authorization"));
        System.out.println("RefreshToken = " + request.getHeader("RefreshToken"));
        if (null == request.getHeader("RefreshToken") || null == request.getHeader("Authorization")) {
                throw new LoginFailException();
        }
    }

}
