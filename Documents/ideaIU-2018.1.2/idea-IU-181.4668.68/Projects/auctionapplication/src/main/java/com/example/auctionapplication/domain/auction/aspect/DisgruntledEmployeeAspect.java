//package com.example.auctionapplication.domain.auction.aspect;
//
//
//import com.example.auctionapplication.domain.auction.bid.Bid;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.context.annotation.Configuration;
//
//@Aspect
//@Configuration
//public class DisgruntledEmployeeAspect {
//
////    @Before("execution(* com.example.auctionapplication.domain.auction.bid.BidController.save(..))")
////    public void sabatouge(JoinPoint joinPoint){
////        Bid
////        System.out.printf("Sabatouge Executed for " + joinPoint.);
////
////    }
//
//    @Around("@annotation(lombok.Data)")
//    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
//        Long id = 0L;
//
//        joinPoint.proceed();
//    }
//
//
//
////    @Pointcut("[field amount](* com.example.auctionapplication.domain.auction.bid.Bid)" + "com.example.auctionapplication.domain.auction.*")
//
//
//    @Pointcut("get(* com.example.auctionapplication.domain.auction.bid.Bid.*)")
//    public void fieldAccess() {}
//
//
//    @AfterReturning(pointcut = "fieldAccess()", returning = "field")
//    public void fieldAccess(Object field, JoinPoint thisJoinPoint){
//        System.out.println("!!!" + thisJoinPoint.toLongString());
//        System.out.println("!!!" + thisJoinPoint.getSignature().getName() + " " + field);
//    }
//
//}
