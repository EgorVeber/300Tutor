package com.v_points.app.di.common

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import dagger.BindsInstance
import dagger.Component
import com.v_points.app.data.common.data_source.AccountLocalDataSource
import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.data.common.data_source.DomainLocalDataSource
import com.v_points.app.data.common.data_source.PrivateDataSource
import com.v_points.app.data.common.data_source.PublicDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.settings_app.SettingsAppLocalDataSource
import com.v_points.app.data.subject_workspace.SubjectWorkspaceLocalDataSource
import com.v_points.app.di.subject_workspase.SubjectWorkspaceAppModule
import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.common.AccountAuthorizationInfoRepository
import com.v_points.app.domain.settings_app.SettingsAppRepository
import com.v_points.app.fiersote.FirestoreModule
import com.v_points.app.presentation.common.ResourceProvider
import javax.inject.Singleton

@Component(
    modules = [
        ResourceModule::class,
        NetworkModule::class,
        SubjectWorkspaceAppModule::class,
        DataSourceModule::class,
        FirestoreModule::class,
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
    fun getFirestoreInstance(): FirebaseFirestore

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getBuilder(context: Context): Builder
        fun getAppComponentBuild(): AppComponent
    }
}