package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.f0x.food.models.dto.users.CreateUserProfileDTO
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.models.entity.ActivityEnum
import ru.f0x.food.models.entity.SexEnum
import ru.f0x.food.models.entity.TargetEnum
import ru.f0x.food.telegram.*

@Suppress("UNCHECKED_CAST")
@Component
class OnProfileCase(private val repository: ITelegramUserRegistrationStateRepository) : ITelegramCase {

    private companion object {
        private val ageRange = (18..100)
        private val heightRange = (150..220)
        private val weightRange = (40..170)
        private val activityRange = (1..5)
        private val targetRange = (1..3)
        private const val NOT_ALL_PARAMETERS_PASSED = "Не все параметры переданы.\n$PROFILE_PATTERN"
        private const val INVALID_NUMBER = "В параметре %s допустимо только число"
        private const val INVALID_SEX = "Неверно указан пол. Допустимо только М или Ж"
        private const val SHOULD_BE_BETWEEN_IN = "%s должен быть в диапазоне %d..%d"
        private const val PROFILE_CONFIRMATION_MESSAGE = "Возраст - %d лет\n" +
                "Рост - %s см\n" +
                "Вес - %s кг\n" +
                "Пол - %s \n" +
                "Активность - %s \n" +
                "Цель - %s"


    }

    override val type: CaseType = CaseType.ON_PROFILE

    /**
     * Возраст/Рост/Вес/Пол/Активность/Цель
     * 30/180/90/М/2/4
     * */

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        checkNotNull(messageText) { "messageText is null for case$type" }
        val cid = userInfo.cid
        val profile = messageText
        val arr = profile.split('/')

        if (arr.size != 6)
            return SendMessage(cid, NOT_ALL_PARAMETERS_PASSED) as T

        val age = try {
            arr[0].toInt()
        } catch (nfe: NumberFormatException) {
            return onNFE("Возраст", cid) as T
        }

        if (age !in ageRange)
            return shouldBeInRange("Возраст", ageRange, cid) as T
        val height = try {
            arr[1].toFloat()

        } catch (nfe: NumberFormatException) {
            return onNFE("Рост", cid) as T
        }

        if (height.toInt() !in heightRange) {
            return shouldBeInRange("Рост", heightRange, cid) as T
        }

        val weight = try {
            arr[2].toFloat()
        } catch (nfe: NumberFormatException) {
            return onNFE("Вес", cid) as T
        }

        if (weight.toInt() !in weightRange)
            return shouldBeInRange("Вес", weightRange, cid) as T

        val sex = arr[3].toUpperCase()
        if (sex != "М" && sex != "Ж")
            return SendMessage(cid, INVALID_SEX) as T

        val activity = try {
            arr[4].toInt()
        } catch (nfe: NumberFormatException) {
            return onNFE("Активность", cid) as T
        }

        if (activity !in activityRange)
            return shouldBeInRange("Активность", activityRange, cid) as T
        val target = try {
            arr[5].toInt()
        } catch (nfe: NumberFormatException) {
            return onNFE("Цель", cid) as T
        }

        if (target !in targetRange)
            return shouldBeInRange("Цель", targetRange, cid) as T
        val state = repository.getState(userInfo)
        val activityEnum = ActivityEnum.values()[activity - 1]
        val targetEnum = TargetEnum.values()[target - 1]
        val sexEnum = getSex(sex)
        repository.saveState(state.goTo(CaseType.ON_PROFILE_CONFIRMATION).apply {
            this.profile = CreateUserProfileDTO(
                    sex = sexEnum,
                    weight = weight,
                    height = height,
                    age = age,
                    activity = activityEnum,
                    target = targetEnum

            )
        })

        return buildMessageWithButtons(
                cid,
                String.format(
                        PROFILE_CONFIRMATION_MESSAGE,
                        age,
                        height,
                        weight,
                        sexEnum.simpleName,
                        activityEnum.simpleName,
                        targetEnum.simpleName
                ),
                listOf(
                        MessageCallback.createForProfileConfirmationAccept(),
                        MessageCallback.createForProfileConfirmationDecline(),
                )
        ) as T

    }

    private fun onNFE(param: String, cid: String) = SendMessage(cid, String.format(INVALID_NUMBER, param))

    private fun shouldBeInRange(param: String, range: IntRange, cid: String) = SendMessage(
            cid,
            String.format(SHOULD_BE_BETWEEN_IN, param, range.first, range.last)
    )


    private fun getSex(sex: String): SexEnum {
        return when (sex) {
            "М" -> SexEnum.MALE
            "Ж" -> SexEnum.FEMALE
            else -> throw IllegalStateException("invalid sex")
        }
    }

}