package gusev.max.data.source.base

import gusev.max.data.entity.BaseEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


interface BaseDataStore<E : BaseEntity> {

    fun getEntities(): Flowable<List<E>>

    fun getEntityById(id: Long): Flowable<E>

    fun isCached(): Single<Boolean>

    fun saveEntities(entities: List<E>): Completable

    fun saveEntity(entity: E): Completable

    fun clearEntities(): Completable
}
