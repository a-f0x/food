package ru.f0x.nutrients.configuration

interface IWebApplicationPropertiesProvider {

    /**Время жизни аксесс токена **/
    val accessTokenValiditySeconds: Int

    /**Время жизни рефреш токена **/
    val refreshTokenValiditySeconds: Int

    val secretAuthKey: String

}