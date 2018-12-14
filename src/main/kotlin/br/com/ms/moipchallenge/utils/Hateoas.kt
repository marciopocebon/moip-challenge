package br.com.ms.moipchallenge.utils

import org.springframework.hateoas.Link

interface Hateoas<T> {

    fun getLinks(instance: T): List<Link>
}




