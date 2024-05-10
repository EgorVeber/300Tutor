package org.threehundredtutor.di.app

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.data.common.ConfigRepository
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.PublicDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.data.subject_workspace.SubjectWorkspaceLocalDataSource
import org.threehundredtutor.di.subject_workspase.SubjectWorkspaceAppModule
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, SubjectWorkspaceAppModule::class])
@Singleton
interface AppComponent {
    fun getResourceProvider(): ResourceProvider
    fun getServiceGeneratorProvider(): ServiceGeneratorProvider
    fun getAccountLocalDataSource(): AccountLocalDataSource
    fun getPublicDataSource(): PublicDataSource
    fun getAccountAuthorizationInfoRepository(): AccountAuthorizationInfoRepository
    fun getConfigRepository(): ConfigRepository
    fun getConfigLocalDataSource(): ConfigLocalDataSource
    fun getSubjectWorkspaceLocalDataSource(): SubjectWorkspaceLocalDataSource

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getBuilder(context: Context): Builder
        fun getAppComponentBuild(): AppComponent
    }
}

