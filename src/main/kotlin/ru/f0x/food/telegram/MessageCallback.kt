package ru.f0x.food.telegram

sealed class MessageCallback {

    companion object {
        private const val DELIMITER = ';'
        private const val ACCEPT = "ACCEPT"
        private const val DECLINE = "DECLINE"

        fun getCallBack(data: String): MessageCallback {
            val arr = data.split(DELIMITER)
            val type = Type.valueOf(arr[0])
            return create(type, arr)

        }

        fun createCallbackForCancellation(): String = "${Type.CANCEL}"

        fun createForProfileConfirmationAccept(): ButtonWithCallback = ButtonWithCallback("Да", "${Type.PROFILE_CONFIRMATION}$DELIMITER$ACCEPT")

        fun createForProfileConfirmationDecline(): ButtonWithCallback = ButtonWithCallback("Нет", "${Type.PROFILE_CONFIRMATION}$DELIMITER$DECLINE")


        private fun create(type: Type, arr: List<String>): MessageCallback {
            return when (type) {
                Type.CANCEL -> CancelMessageCallback
                Type.PROFILE_CONFIRMATION -> {
                    val response = arr[1]
                    if (response == ACCEPT)
                        return ProfileAccept
                    return ProfileDecline
                }
            }
        }

    }

    private enum class Type {
        CANCEL,
        PROFILE_CONFIRMATION,
    }

}

object CancelMessageCallback : MessageCallback()

object ProfileAccept : MessageCallback()
object ProfileDecline : MessageCallback()