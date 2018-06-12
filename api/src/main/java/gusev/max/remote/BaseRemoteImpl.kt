package gusev.max.remote

import gusev.max.data.entity.BaseEntity
import gusev.max.data.source.base.BaseRemote
import gusev.max.remote.mapper.Mapper
import gusev.max.remote.model.BaseRemoteModel
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by v on 11/06/2018.
 */
abstract class BaseRemoteImpl<E : BaseEntity, M : BaseRemoteModel>(
    val mapper: Mapper<M, E>
) : BaseRemote<E> {

//    val authErrorTransformer: FlowableTransformer<P, P> = FlowableTransformer {
//        it.switchMap {
//            if (it.error?.errorCode != null) {
//                Flowable.error(AuthError())
//            } else {
//                Flowable.just(it)
//            }
//        }
//    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun saveEntities(entities: List<E>): Completable {
        throw UnsupportedOperationException()
    }

    override fun clearEntities(): Completable {
        throw UnsupportedOperationException()
    }
}