package gusev.max.spotsearch.injection.module

import dagger.Binds
import dagger.Module
import gusev.max.data.executor.JobExecutor
import gusev.max.data.repository.*
import gusev.max.domain.executor.ThreadExecutor
import gusev.max.domain.repository.*

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

    @Binds
    abstract fun bindEventsRepository(eventDataRepository: EventDataRepository): EventsRepository

    @Binds
abstract fun bindCommentsRepository(commentDataRepository: CommentDataRepository): CommentsRepository
}