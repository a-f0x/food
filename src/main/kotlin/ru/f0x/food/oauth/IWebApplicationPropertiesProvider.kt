package ru.f0x.food.oauth

interface IWebApplicationPropertiesProvider {

    /**Время жизни аксесс токена **/
    val accessTokenValiditySeconds: Int

    /**Время жизни рефреш токена **/
    val refreshTokenValiditySeconds: Int

    val secretAuthKey: String

}