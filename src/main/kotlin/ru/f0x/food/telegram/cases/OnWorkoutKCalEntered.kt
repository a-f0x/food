package ru.f0x.food.telegram.cases

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import ru.f0x.food.models.dto.users.Profile
import ru.f0x.food.services.events.IEventService
import ru.f0x.food.services.users.IUserService
import ru.f0x.food.telegram.UserInfo
import ru.f0x.food.telegram.onNFE
import ru.f0x.food.telegram.shouldBeAbove

@Suppress("UNCHECKED_CAST")
@Component
class OnWorkoutKCalEntered(
        private val userService: IUserService,
        private val eventService: IEventService
) : BaseMenuCase() {

    override val type: CaseType = CaseType.ON_WORKOUT_KCAL_ENTERED

    override fun <T : BotApiMethod<BotApiObject>> process(userInfo: UserInfo, messageText: String?, profile: Profile?): T? {
        checkNotNull(profile) { "need profile for this case: $type" }
        checkNotNull(messageText) { "need messageText for this case: $type" }
        val kCal = try {
            messageText.toInt()
        } catch (nfe: NumberFormatException) {
            return onNFE("кКал", userInfo.cid) as T
        }
        if (kCal <= 0)
            return shouldBeAbove("кКал", 1, userInfo.cid) as T
//        eventService.addWorkoutEvent(profile.userId, CreateEventForWorkoutDTO(
//
//        ))
//
//        userService.goToCase(profileId = profile.profileId, CaseType.MAIN_MENU)
        return onNFE("кКал", userInfo.cid) as T

    }


}