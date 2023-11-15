package org.threehundredtutor.di.components

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.utils.AccountManager
import org.threehundredtutor.common.utils.PublicDataSource
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.di.modules.AppModule
import org.threehundredtutor.di.modules.NetworkModule
import org.threehundredtutor.domain.AccountManagerRepository
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun getResourceProvider(): ResourceProvider
    fun getServiceGeneratorProvider(): ServiceGeneratorProvider
    fun getAccountManager(): AccountManager
    fun getPublicDataSource(): PublicDataSource
    fun getAccountManagerRepository(): AccountManagerRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getBuilder(context: Context): Builder
        fun getAppComponentBuild(): AppComponent
    }
}

