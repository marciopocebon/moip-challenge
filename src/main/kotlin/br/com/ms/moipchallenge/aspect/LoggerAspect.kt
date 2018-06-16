package br.com.ms.moipchallenge.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Configuration
class LoggerAspect {

    private val logger = LoggerFactory.getLogger(LoggerAspect::class.java)

    @Around("execution(* br.com.ms.moipchallenge.controllers.*.*(..))")
    fun aroundControllers(point: ProceedingJoinPoint): Any? {
        val req = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
        logger.info("Starting... {} {}", req.request.method, req.request.servletPath)

        val startTime = System.currentTimeMillis()
        val entity = point.proceed() as ResponseEntity<*>
        val elapsed = System.currentTimeMillis() - startTime

        logger.info("{} {} returning with -> Status: {} in {}ms",
                req.request.method, req.request.servletPath, entity.statusCode, elapsed)

        return entity
    }
}