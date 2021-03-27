package musicdb.demo.aop;

import musicdb.demo.services.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* musicdb.demo.web.AlbumController.details(..))")
    public void detailsPointcut() {
    }

    @After("detailsPointcut()")
    public void afterAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
         Long albumId = (Long)args[0];
         String action = joinPoint.getSignature().getName();
         logService.createLog(albumId, action);
    }

    ;

}
