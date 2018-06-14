package br.com.ms.moipchallenge.aspect

import br.com.ms.moipchallenge.modules.measureTime
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

        val (elapsed, response) = measureTime { point.proceed() }
        val entity = response as ResponseEntity<*>

        logger.info("{} {} returning with -> Status: {} in {}ms",
                req.request.method, req.request.servletPath, entity.statusCode, elapsed)

        return response
    }
}