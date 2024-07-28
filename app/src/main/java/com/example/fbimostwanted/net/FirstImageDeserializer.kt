package com.example.fbimostwanted.net
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

class FirstImageDeserializer : JsonDeserializer<String?>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): String? {
        val node: JsonNode = p.codec.readTree(p)
        return if (node.isArray && node.size() > 0) {
            node[0]["large"].asText()
        } else {
            null
        }
    }
}