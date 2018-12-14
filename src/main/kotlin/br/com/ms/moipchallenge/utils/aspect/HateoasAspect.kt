package br.com.ms.moipchallenge.utils.aspect

import br.com.ms.moipchallenge.utils.annotation.PageableMethod
import br.com.ms.moipchallenge.utils.page.PageLinks
import br.com.ms.moipchallenge.utils.page.PageResponse
import br.com.ms.moipchallenge.views.responses.Response
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.data.domain.Page
import org.springframework.hateoas.Link
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class HateoasAspect {

    @Around("execution(* br.com.ms.moipchallenge.controllers.*.*(..))")
    fun aroundMonitorMethods(point: ProceedingJoinPoint): Any? {
        val entity = point.proceed() as ResponseEntity<*>
        val status = entity.statusCode
        val method = (point.signature as MethodSignature).method
        return if (method.isAnnotationPresent(PageableMethod::class.java)) {
            buildPageableResponse(entity, method, status)
        } else {
            buildResponse(entity.body, status)
        }
    }

    private fun buildPageableResponse(entity: ResponseEntity<*>, method: Method, status: HttpStatus): ResponseEntity<Response> {
        val page = entity.body as Page<*>
        val pageResponse = PageResponse(page.size, page.totalElements, page.totalPages, page.number)
        return buildResponse(page.content, status, PageLinks.buildLinks(page, method), pageResponse)
    }

    fun buildResponse(content: Any?, status: HttpStatus, links: List<Link>? = null, page: PageResponse? = null) =
            ResponseEntity(Response(content, links, page), status)
}