package br.com.ms.moipchallenge.views.responses

import br.com.ms.moipchallenge.utils.page.PageResponse
import org.springframework.hateoas.Link

class Response(
        val _embedded: Any?,
        val _links: List<Link>?,
        val page: PageResponse?
)