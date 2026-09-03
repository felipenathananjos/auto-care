package com.github.felipenathananjos.autocare.di

import com.github.felipenathananjos.autocare.domain.login.LoginService
import com.github.felipenathananjos.autocare.domain.login.LoginServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    abstract fun providesLoginService(loginService: LoginServiceImpl): LoginService
}