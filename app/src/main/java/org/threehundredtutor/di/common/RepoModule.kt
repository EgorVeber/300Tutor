package org.threehundredtutor.di.common

import dagger.Binds
import dagger.Module
import org.threehundredtutor.data.common.AccountAuthorizationInfoRepositoryImpl
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository

@Module
abstract class RepoModule {

    @Binds
    abstract fun bindsAccountAuthorizationInfoRepositoryImpl(accountAuthorizationInfoRepositoryImpl: AccountAuthorizationInfoRepositoryImpl): AccountAuthorizationInfoRepository
}