package com.fudie.core.app_settings

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object LocalRecipeSerializer : Serializer<LocalRecipeItem> {
    override val defaultValue: LocalRecipeItem
        get() = LocalRecipeItem()

    override suspend fun readFrom(input: InputStream): LocalRecipeItem {
        return try {
            Json.decodeFromString(
                deserializer = LocalRecipeItem.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: LocalRecipeItem, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = LocalRecipeItem.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}