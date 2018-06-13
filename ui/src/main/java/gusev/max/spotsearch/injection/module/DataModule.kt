package gusev.max.spotsearch.injection.module

import dagger.Binds
import dagger.Module
import gusev.max.data.executor.JobExecutor
import gusev.max.data.repository.ActionDataRepository
import gusev.max.data.repository.AuthDataRepository
import gusev.max.data.repository.UserDataRepository
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.repository.ActionsRepository
import gusev.max.domain.repository.AuthRepository
import gusev.max.domain.repository.UserRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    /**
     * Auth
     */

    @Binds
    abstract fun bindUserRepository(userDataRepository: UserDataRepository): UserRepository

    @Binds
    abstract fun bindAuthRepository(authDataRepository: AuthDataRepository): AuthRepository

    /**
     * Main
     */

    @Binds
    abstract fun bindActionRepository(actionDataRepository: ActionDataRepository): ActionsRepository
}