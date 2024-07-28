package com.example.fbimostwanted

import com.example.fbimostwanted.net.FirstImageDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import kotlinx.serialization.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
@Serializable
data class PersonItem(
    @JsonProperty("title") val name: String,
//    @JsonProperty("place_of_birth") val placeOfBirth :String,
    @JsonProperty("race", defaultValue = "Unknown") val race :String?,
    @JsonProperty("weight", defaultValue = "Unknown") val weight: String?,
    @JsonProperty("description") val description: String,
    @JsonProperty("sex", defaultValue = "Unknown") val gender: String?,

    @JsonProperty("images")
    @JsonDeserialize(using = FirstImageDeserializer::class)
    val imageBaseUrl : String?,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class WantedList(
    @JsonProperty("items") val items: List<PersonItem>
)
