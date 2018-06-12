package gusev.max.domain.executor

import io.reactivex.Scheduler

/**
 * Created by v on 10/06/2018.
 */
interface PostExecutionThread {
    val scheduler: Scheduler
}