package br.com.ms.moipchallenge.utils.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.CodeSignature
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Configuration
class LoggerAspect {

    private val logger = LoggerFactory.getLogger(LoggerAspect::class.java)

    @Around("execution(* br.com.ms.moipchallenge.web.controllers.*.*(..))")
    fun aroundControllers(point: ProceedingJoinPoint): Any? {
        val req = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val target = "${point.target::class.simpleName}.${point.signature.name}"
        val args = getArgs(point.signature as CodeSignature, point.args)

        logger.info("${req.method} ${req.servletPath} -> Starting execution of $target with args $args")

        val startTime = System.currentTimeMillis()
        val output = point.proceed() as ResponseEntity<*>
        val elapsed = System.currentTimeMillis() - startTime

        logger.info("${req.method} ${req.servletPath} -> Execution of $target completed in ${elapsed}ms " +
                "with status ${output.statusCode.name} and output [${output.body}]")

        return output
    }

    fun getArgs(codeSignature: CodeSignature, args: Array<Any>) = codeSignature.parameterNames
            .mapIndexed { index, parameterName -> parameterName to args[index] }
            .joinToString(",", "[", "]") { "${it.first}=${it.second}" }
}