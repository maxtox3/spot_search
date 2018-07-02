package gusev.max.data.source.base

import gusev.max.data.entity.BaseEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


abstract class BaseRemoteDataStore<E : BaseEntity>(private val remote: BaseRemote<E>) :
        BaseDataStore<E> {

    override fun getEntities(): Flowable<List<E>> {
        return remote.getEntities()
    }

    override fun getEntityById(id: Long): Flowable<E> {
        return remote.getEntityById(id)
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun saveEntities(entities: List<E>): Completable {
       return remote.saveEntities(entities)
    }

    override fun saveEntity(entity: E): Completable {
        return remote.saveEntity(entity)
    }

    override fun clearEntities(): Completable {
        return remote.clearEntities()
    }
}