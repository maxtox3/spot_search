package gusev.max.data.sourcefactory

import gusev.max.data.entity.CommentEntity
import gusev.max.data.source.event.comment.CommentRemoteDataStore
import gusev.max.data.source.event.comment.CommentCache
import gusev.max.data.source.event.comment.CommentCacheDataStore
import gusev.max.data.source.event.comment.CommentDataStore
import javax.inject.Inject

/**
 * Created by v on 27/06/2018.
 */
class CommentDataStoreFactory @Inject constructor(
    cacheStore: CommentCache,
    remoteDataStore: CommentRemoteDataStore,
    cacheDataStore: CommentCacheDataStore
) : BaseDataStoreFactory<CommentEntity, CommentDataStore, CommentCache>(
        cache = cacheStore,
        remoteStore = remoteDataStore,
        cacheStore = cacheDataStore
)