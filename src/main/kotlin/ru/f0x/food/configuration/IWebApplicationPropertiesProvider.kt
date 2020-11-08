package ru.f0x.food.configuration

interface IWebApplicationPropertiesProvider {

    /**Время жизни аксесс токена **/
    val accessTokenValiditySeconds: Int

    /**Время жизни рефреш токена **/
    val refreshTokenValiditySeconds: Int

    val secretAuthKey: String

}