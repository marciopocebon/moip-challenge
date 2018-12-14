package br.com.ms.moipchallenge.controllers

import br.com.ms.moipchallenge.models.Buyer
import br.com.ms.moipchallenge.services.BuyerService
import br.com.ms.moipchallenge.utils.annotation.PageableMethod
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/buyers")
class BuyerController(
        private val buyerService: BuyerService
) {

    @PostMapping
    fun save(@Valid @RequestBody buyer: Buyer) = ResponseEntity(buyerService.save(buyer), CREATED)

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = ResponseEntity(buyerService.findById(id), OK)

    @GetMapping
    @PageableMethod
    fun findAll(pageable: Pageable) = ResponseEntity(buyerService.findAll(pageable), OK)
}