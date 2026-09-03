package com.github.felipenathananjos.autocare.di

import com.github.felipenathananjos.autocare.domain.registration.RegistrationService
import com.github.felipenathananjos.autocare.domain.registration.RegistrationServiceImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RegistrationModule {

    @Binds
    abstract fun providesRegistrationService(registrationServiceImpl: RegistrationServiceImpl): RegistrationService

    companion object {
        @Provides
        fun providesGoogleAuth(): FirebaseAuth = Firebase.auth
    }
}