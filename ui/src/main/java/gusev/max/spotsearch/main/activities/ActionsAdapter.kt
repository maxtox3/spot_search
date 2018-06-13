package gusev.max.spotsearch.main.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import gusev.max.presentation.model.ActionModel
import gusev.max.spotsearch.R
import gusev.max.spotsearch.base.widgets.BaseAdapter
import javax.inject.Inject

/**
 * Created by v on 13/06/2018.
 */
class ActionsAdapter @Inject constructor() : BaseAdapter<ActionModel, ActionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ActionsViewHolder {
        val itemView = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.item_action, parent, false)
        return ActionsViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: ActionsViewHolder?, position: Int) {
        holder?.bind(modelList[position])
    }

}