package gusev.max.spotsearch.base.widgets

import android.support.v7.widget.RecyclerView
import gusev.max.presentation.model.BaseModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by v on 13/06/2018.
 */
abstract class BaseAdapter<M : BaseModel, VIEW_HOLDER : BaseViewHolder<M>> :
        RecyclerView.Adapter<VIEW_HOLDER>(),
        BaseViewHolder.ModelClickListener<M> {

    protected val mainModelClickObservable: PublishSubject<M> = PublishSubject.create()
    protected var modelList: MutableList<M> = mutableListOf()

    override fun getItemCount(): Int {
        return modelList.size
    }

    fun setData(models: List<M>) {
        val oldSize = modelList.size
        modelList.clear()
        notifyItemRangeRemoved(0, oldSize)

        modelList.addAll(models)
        notifyItemRangeInserted(0, models.size)
    }

    override fun onMainModelClick(model: M, position: Int) {
        mainModelClickObservable.onNext(model)
    }

    fun getMainModelClickObservable(): Observable<M> {
        return mainModelClickObservable
    }
}