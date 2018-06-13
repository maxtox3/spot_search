package gusev.max.spotsearch.base.widgets

import android.support.v7.widget.RecyclerView
import android.view.View
import gusev.max.presentation.model.BaseModel

/**
 * Created by v on 13/06/2018.
 */
open class BaseViewHolder<M : BaseModel> constructor(
    itemView: View,
    private val clickListener: ModelClickListener<M>
) : RecyclerView.ViewHolder(itemView) {

    interface ModelClickListener<M : BaseModel> {
        fun onMainModelClick(model: M, position: Int)
    }

    open fun bind(model: M) {
        itemView.setOnClickListener({ v ->
            clickListener.onMainModelClick(
                    model,
                    this.adapterPosition
            )
        })
    }
}