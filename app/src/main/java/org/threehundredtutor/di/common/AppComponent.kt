package org.threehundredtutor.di.common

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.data_source.DomainLocalDataSource
import org.threehundredtutor.data.common.data_source.PrivateDataSource
import org.threehundredtutor.data.common.data_source.PublicDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.data.settings_app.SettingsAppLocalDataSource
import org.threehundredtutor.data.subject_workspace.SubjectWorkspaceLocalDataSource
import org.threehundredtutor.di.subject_workspase.SubjectWorkspaceAppModule
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import org.threehundredtutor.domain.settings_app.SettingsAppRepository
import org.threehundredtutor.presentation.common.ResourceProvider
import javax.inject.Singleton

@Component(
    modules = [
        ResourceModule::class,
        NetworkModule::class,
        SubjectWorkspaceAppModule::class,
        DataSourceModule::class,
        RepoModule::class]
)
@Singleton
interface AppComponent {
    fun getResourceProvider(): ResourceProvider
    fun getConfigLocalDataSource(): ConfigLocalDataSource
    fun getSubjectWorkspaceLocalDataSource(): SubjectWorkspaceLocalDataSource
    fun getServiceGeneratorProvider(): ServiceGeneratorProvider
    fun getAccountLocalDataSource(): AccountLocalDataSource
    fun getSettingsAppLocalDataSource(): SettingsAppLocalDataSource
    fun getPublicDataSource(): PublicDataSource
    fun getAccountAuthorizationInfoRepository(): AccountAuthorizationInfoRepository
    fun getSettingsAppRepository(): SettingsAppRepository
    fun getAccountRepository (): AccountRepository
    fun getPrivateDataSource(): PrivateDataSource
    fun getDomainLocalDataSource(): DomainLocalDataSource

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getBuilder(context: Context): Builder
        fun getAppComponentBuild(): AppComponent
    }
}