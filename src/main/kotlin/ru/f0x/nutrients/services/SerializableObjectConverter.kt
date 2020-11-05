package ru.f0x.nutrients.services

import org.apache.commons.codec.binary.Base64
import org.springframework.util.SerializationUtils


class SerializableObjectConverter {

    companion object {

        fun serialize(obj: Any): String {
            try {
                val bytes = SerializationUtils.serialize(obj)
                return Base64.encodeBase64String(bytes)
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }

        }

        fun <T : Any> deserialize(encodedObject: String?): T {
            try {
                val bytes = Base64.decodeBase64(encodedObject)
                return SerializationUtils.deserialize(bytes) as T
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }

        }
    }
}
