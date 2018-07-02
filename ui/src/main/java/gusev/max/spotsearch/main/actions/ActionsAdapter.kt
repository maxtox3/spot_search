package gusev.max.spotsearch.main.actions

import android.view.LayoutInflater
import android.view.ViewGroup
import gusev.max.spotsearch.R
import gusev.max.spotsearch.base.widgets.BaseAdapter
import gusev.max.spotsearch.model.ActionUiModel
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionsAdapter @Inject constructor() : BaseAdapter<ActionUiModel, ActionsViewHolder>() {

    private var previousChosenPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ActionsViewHolder {
        val itemView = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.item_action_horizontal, parent, false)
        return ActionsViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: ActionsViewHolder?, position: Int) {
        holder?.bind(modelList[position])
    }

    override fun onMainModelClick(model: ActionUiModel, position: Int) {
        modelList[position].chosen = true
        if (previousChosenPosition != -1) {
            modelList[previousChosenPosition].chosen = false
            notifyItemChanged(previousChosenPosition)
        }
        notifyItemChanged(position)
        previousChosenPosition = position
        super.onMainModelClick(model, position)
    }

    fun getChosenId(): Long {
        if (modelList.isNotEmpty()) {
            if (previousChosenPosition != -1) return modelList[previousChosenPosition].id
        }
        return previousChosenPosition.toLong()
    }

}