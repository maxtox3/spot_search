package gusev.max.data.source.user

import gusev.max.data.entity.UserEntity
import gusev.max.data.source.base.BaseCache


interface UserCache : BaseCache<UserEntity>, UserDataStore