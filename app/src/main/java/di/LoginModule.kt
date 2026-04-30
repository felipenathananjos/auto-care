package di

import com.github.felipenathananjos.autocare.model.login.LoginService
import com.github.felipenathananjos.autocare.model.login.LoginServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    abstract fun providesLoginService(loginService: LoginServiceImpl): LoginService
}