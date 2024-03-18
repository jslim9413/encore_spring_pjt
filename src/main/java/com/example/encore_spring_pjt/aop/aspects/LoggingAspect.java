package com.example.encore_spring_pjt.aop.aspects;
/*
AOP 용어정리(횡단관심모듈(cross cutting)을 이용한 공통의사항을 처리하는것이 AOP) 

Aspect : 공통의 기능을 가지고 있는 모듈
Target : Aspect가 적용될 대상(클래스, 메서드등이 해당) 
JoinPoint : Aspect가 적용될 시점(메서드 실행전, 후) 
Advice : 시점에 실행코드
PointCut : Advice를 적용할 메서드의 범위를 지정  

시점에 관련된 어노테이션으로
@Before : 대상의 메서드가 실행되기 전에 실행
@After  : 대상의 메서드가 실행된   후에 실행
@AfterReturning : 대상의 메서드가 정상으로 실행되고 반환된 후에 실행
@AfterThrowing  : 대상의 메서가 예외가 발생되었을 때 실행
@Around : 대상의 메서드가 실행전,후, 예외발생시에  실행
*/

import org.aspectj.lang.JoinPoint ;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.internal.ExceptionConverterImpl;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    // point cut pattern [접근지정][패키지][클래스][메서드()]
    @Pointcut("execution(* com.example.encore_spring_pjt.ctrl..*.*(..))")
    // @Pointcut("execution(com.example.encore_spring_pjt.aop.ctrl.AopTestController.test*(..))")
    private void cut() {

    }
    
    @Before("cut()") 
    public void beforeLog(JoinPoint joinpoint) {
        String name = getMethod(joinpoint);
        System.out.println("debug >>> LoggingAspect beforeLog ") ; 
        System.out.println("debug >>> name , " + name) ;  

        // 파라미터 정보를 얻어와서 파라미터값을 로그로 출력 .... 
        Object[] args = joinpoint.getArgs();
        if( args.length <= 0 ) {
            log.info(">>>> no parameter ") ;     
        } else {
            for(Object arg : args) {
                log.info("prameter type  = {} ", arg.getClass().getSimpleName());
                log.info("prameter value = {} ", arg);
            }
        }

    }

    @After("cut()") 
    public void afterLog(JoinPoint joinpoint) {
        System.out.println("debug >>> LoggingAspect afterLog ") ;  
    }
    

    @AfterReturning(pointcut = "cut()" , returning = "param")
    public void returningLog(JoinPoint joinpoint, Object param) {
        String name = getMethod(joinpoint);
        System.out.println("debug >>> LoggingAspect returningLog ") ; 
        System.out.println("debug >>> name , " + name) ;  

        // 리턴타입과 린터되는 값을 확인 할 수 있다. 

        System.out.println("debug >>> return type  , " + param.getClass().getSimpleName());
        System.out.println("debug >>> return value , " + param ); 
        

    }

    @AfterThrowing(pointcut = "cut()" , throwing = "e")
    public void throwingLog(JoinPoint joinPoint, Throwable e) {
        System.out.println("debug >>> throwingLog method , " + joinPoint.getSignature().getName());
        System.out.println("debug >>> throwingLog msg , " + e.getMessage()); 
    }

    // 메서드 실행 전,후 예외발생 시 Advice 실행
    /* 
    @Around("cut()") 
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("debug >>> around before , " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed() ; 
        System.out.println("debug >>> around after , " + joinPoint.getSignature().getName());
        return result ; 
    } 
    */

    

    // joinpoint 관련 정보를 얻어오기 위해서 
    public String getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        return signature.getName() ; 
    }
}







