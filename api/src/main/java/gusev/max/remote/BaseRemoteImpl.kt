package gusev.max.remote

import gusev.max.data.entity.BaseEntity
import gusev.max.data.source.base.BaseRemote
import gusev.max.remote.mapper.Mapper
import gusev.max.remote.model.BaseRemoteModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by v on 11/06/2018.
 */
abstract class BaseRemoteImpl<E : BaseEntity, M : BaseRemoteModel>(
    val mapper: Mapper<M, E>
) : BaseRemote<E> {

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun saveEntities(entities: List<E>): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveEntity(entity: E): Completable {
        throw UnsupportedOperationException()
    }

    override fun clearEntities(): Completable {
        throw UnsupportedOperationException()
    }

    override fun getEntities(): Flowable<List<E>> {
        throw UnsupportedOperationException()
    }

    override fun getEntityById(id: Long): Flowable<E> {
        throw UnsupportedOperationException()
    }
}