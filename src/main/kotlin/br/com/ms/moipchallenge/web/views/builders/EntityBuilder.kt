package br.com.ms.moipchallenge.web.views.builders

class EntityBuilder {

    internal val entity: MutableMap<String, Any> = LinkedHashMap()

    fun add(key: String, value: Any) = this
            .apply { entity[key] = value }
}