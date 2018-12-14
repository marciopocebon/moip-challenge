package br.com.ms.moipchallenge.controllers

import br.com.ms.moipchallenge.models.Client
import br.com.ms.moipchallenge.services.ClientService
import br.com.ms.moipchallenge.utils.annotation.PageableMethod
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/clients")
class ClientController(
        private val clientService: ClientService
) {

    @PostMapping
    fun save(@Valid @RequestBody client: Client) = ResponseEntity(clientService.save(client), CREATED)

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = ResponseEntity(clientService.findById(id), OK)

    @GetMapping
    @PageableMethod
    fun findAll(pageable: Pageable) = ResponseEntity(clientService.findAll(pageable), OK)
}