package gusev.max.domain.repository

import gusev.max.domain.model.Commentary
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by v on 27/06/2018.
 */
interface CommentsRepository: BaseRepository<Commentary> {

    fun getCommentsByEventId(eventId: Long): Flowable<List<Commentary>>

    fun likeCommentary(eventId: Long, commentaryId: Long): Completable

    fun dislikeCommentary(eventId: Long, commentaryId: Long): Completable
    
}