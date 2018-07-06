package br.com.ms.moipchallenge.builder

class EntityBuilder {

    internal val entity: MutableMap<String, Any> = LinkedHashMap()

    fun add(key: String, value: Any): EntityBuilder = this
            .apply { entity[key] = value }
}