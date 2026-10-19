package org.v_points.di.common

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import org.v_points.data.common.data_source.AccountLocalDataSource
import org.v_points.data.common.data_source.ConfigLocalDataSource
import org.v_points.data.common.data_source.DomainLocalDataSource
import org.v_points.data.common.data_source.PrivateDataSource
import org.v_points.data.common.data_source.PublicDataSource
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.settings_app.SettingsAppLocalDataSource
import org.v_points.data.subject_workspace.SubjectWorkspaceLocalDataSource
import org.v_points.di.subject_workspase.SubjectWorkspaceAppModule
import org.v_points.domain.account.AccountRepository
import org.v_points.domain.common.AccountAuthorizationInfoRepository
import org.v_points.domain.settings_app.SettingsAppRepository
import org.v_points.presentation.common.ResourceProvider
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