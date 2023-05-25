package com.sample.simpsonsviewer.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Network response class
 */
class Response {
    @JsonClass(generateAdapter = true)
    data class CharacterResult(
        @Json(name = "Abstract") val abstract: String,
        @Json(name = "AbstractSource") val abstractSource : String,
        @Json(name = "AbstractText") val abstractText : String,
        @Json(name = "AbstractURL") val abstractUrl: String,
        @Json(name = "Answer") val answer: String,
        @Json(name = "AnswerType") val answerType: String,
        @Json(name = "Definition") val definition: String,
        @Json(name = "DefinitionSource") val definitionSource: String,
        @Json(name = "DefinitionURL") val definitionUrl: String,
        @Json(name = "Entity") val entity: String,
        @Json(name = "Heading") val heading: String,
        @Json(name = "Image") val image: String,
        @Json(name = "ImageHeight") val imageHeight: Int,
        @Json(name = "ImageIsLogo") val imageIsLogo: Int,
        @Json(name = "ImageWidth") val imageWidth: Int,
        @Json(name = "Infobox") val infobox: String,
        @Json(name = "Redirect") val redirect: String,
        @Json(name = "RelatedTopics") val results: List<CharacterItem>
    )

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class CharacterItem(
        @Json(name = "FirstURL") val firstUrl: String,
        @Json(name = "Icon") val iconInfo: IconInfo,
        @Json(name = "Result") val result: String,
        @Json(name = "Text") val text: String
    ): Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class IconInfo(
        @Json(name = "Height") val height: String,
        @Json(name = "URL") val url: String,
        @Json(name = "Width") val width: String,
    ): Parcelable
}