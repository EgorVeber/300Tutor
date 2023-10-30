package org.threehundredtutor.di.components

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.utils.PrefsCookie
import org.threehundredtutor.common.utils.PrefsSettingsDagger
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.di.modules.AppModule
import org.threehundredtutor.di.modules.NetworkModule
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun getResourceProvider(): ResourceProvider
    fun getServiceGeneratorProvider(): ServiceGeneratorProvider
    fun getPrefsSettingsDagger(): PrefsSettingsDagger
    fun getPrefsCookie(): PrefsCookie

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getBuilder(context: Context): Builder
        fun getAppComponentBuild(): AppComponent
    }
}

