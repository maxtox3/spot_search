package gusev.max.cache.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import gusev.max.cache.dao.CachedActionDao
import gusev.max.cache.dao.CachedCommentDao
import gusev.max.cache.dao.CachedEventDao
import gusev.max.cache.dao.CachedUserDao
import gusev.max.cache.model.CachedAction
import gusev.max.cache.model.CachedComment
import gusev.max.cache.model.CachedEvent
import gusev.max.cache.model.CachedUser
import javax.inject.Inject

/**
 * Created by v on 10/06/2018.
 */
@Database(
        entities = [
            (CachedUser::class),
            (CachedEvent::class),
            (CachedAction::class),
            (CachedComment::class)
        ], version = 1
)
abstract class SpotSearchDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedUserDao(): CachedUserDao

    abstract fun cachedEventsDao(): CachedEventDao

    abstract fun cachedActionsDao(): CachedActionDao

    abstract fun cachedCommentsDao(): CachedCommentDao

    private var INSTANCE: SpotSearchDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): SpotSearchDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            SpotSearchDatabase::class.java, "debug.db"
                    )
                        .build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }

}