package br.com.ms.moipchallenge.utils.page

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.Link
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import java.lang.reflect.Method

object PageLinks {

    fun buildLinks(page: Page<*>, method: Method): List<Link>? {
        val links = mutableListOf<Link>()
        if (!page.isFirst) {
            links.add(createLink(method, "first", page.pageable.first()))
        }
        if (page.hasPrevious() && page.previousPageable().pageNumber != 0) {
            links.add(createLink(method, "previous", page.previousPageable()))
        }
        if (page.hasNext() && page.nextPageable().pageNumber != page.totalPages - 1) {
            links.add(createLink(method, "next", page.nextPageable()))
        }
        if (!page.isLast) {
            links.add(createLink(method, "last", PageRequest.of(page.totalPages - 1, page.size)))
        }
        return if (links.isNotEmpty()) links else null
    }

    private fun createLink(method: Method, rel: String, pageable: Pageable) =
            Link(createConfiguredUriBuilder(method, pageable).toUriString(), rel).withType("GET")

    private fun createConfiguredUriBuilder(method: Method, pageable: Pageable) = linkTo(method).toUriComponentsBuilder()
            .replaceQueryParam("page", pageable.pageNumber)
            .replaceQueryParam("size", pageable.pageSize)
}