package gusev.max.presentation.main.entity

import gusev.max.domain.model.BaseDomainModel
import gusev.max.presentation.base.BaseResult
import gusev.max.presentation.base.model.TaskStatus

/**
 * Created by v on 23/06/2018.
 */
sealed class ModelResult<M : BaseDomainModel> : BaseResult {

    class BrowseModels<M : BaseDomainModel>(
        val taskStatus: TaskStatus,
        val models: List<M>? = null
    ) : ModelResult<M>() {

        companion object {

            internal fun <M : BaseDomainModel> success(models: List<M>): BrowseModels<M> {
                return BrowseModels(
                        TaskStatus.SUCCESS,
                        models
                )
            }

            internal fun <M : BaseDomainModel> failure(): BrowseModels<M> {
                return BrowseModels(
                        TaskStatus.FAILURE,
                        null
                )
            }

            internal fun <M : BaseDomainModel> inFlight(): BrowseModels<M> {
                return BrowseModels(
                        TaskStatus.IN_WORK
                )
            }
        }
    }

    class CreateModel<M : BaseDomainModel>(
        val taskStatus: TaskStatus
    ) : ModelResult<M>() {

        companion object {

            internal fun <M : BaseDomainModel> success(): CreateModel<M> {
                return CreateModel(TaskStatus.SUCCESS)
            }

            internal fun <M : BaseDomainModel> failure(): CreateModel<M> {
                return CreateModel(TaskStatus.FAILURE)
            }

            internal fun <M : BaseDomainModel> inFlight(): CreateModel<M> {
                return CreateModel(TaskStatus.IN_WORK)
            }
        }
    }

    class UpdateModel<M : BaseDomainModel>(
        val taskStatus: TaskStatus
    ) : ModelResult<M>() {

        companion object {

            internal fun <M : BaseDomainModel> success(): UpdateModel<M> {
                return UpdateModel(TaskStatus.SUCCESS)
            }

            internal fun <M : BaseDomainModel> failure(): UpdateModel<M> {
                return UpdateModel(TaskStatus.FAILURE)
            }

            internal fun <M : BaseDomainModel> inFlight(): UpdateModel<M> {
                return UpdateModel(TaskStatus.IN_WORK)
            }
        }
    }

    class DeleteModel<M : BaseDomainModel>(
        val taskStatus: TaskStatus
    ) : ModelResult<M>() {

        companion object {

            internal fun <M : BaseDomainModel> success(): DeleteModel<M> {
                return DeleteModel(TaskStatus.SUCCESS)
            }

            internal fun <M : BaseDomainModel> failure(): DeleteModel<M> {
                return DeleteModel(TaskStatus.FAILURE)
            }

            internal fun <M : BaseDomainModel> inFlight(): DeleteModel<M> {
                return DeleteModel(TaskStatus.IN_WORK)
            }
        }
    }

}