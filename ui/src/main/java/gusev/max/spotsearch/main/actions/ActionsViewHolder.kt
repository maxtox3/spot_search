package gusev.max.spotsearch.main.actions

import android.view.View
import gusev.max.spotsearch.base.widgets.BaseViewHolder
import gusev.max.spotsearch.model.ActionUiModel
import gusev.max.spotsearch.utils.makeGradientBackGroundCardView
import kotlinx.android.synthetic.main.item_action_horizontal.view.*

/**
 * Created by v on 13/06/2018.
 */
class ActionsViewHolder(
    itemView: View,
    clickListener: ModelClickListener<ActionUiModel>
) : BaseViewHolder<ActionUiModel>(itemView, clickListener) {

    override fun bind(model: ActionUiModel) {
        super.bind(model)
        itemView.action_name.text = model.name
        if (model.chosen) {
            itemView.container.background = makeGradientBackGroundCardView(
                    model.primaryColor,
                    model.secondaryColor
            )
        } else {
            itemView.container.background = null
        }
    }
}