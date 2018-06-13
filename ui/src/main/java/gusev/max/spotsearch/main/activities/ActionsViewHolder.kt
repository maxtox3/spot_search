package gusev.max.spotsearch.main.activities

import android.view.View
import gusev.max.presentation.model.ActionModel
import gusev.max.spotsearch.base.widgets.BaseViewHolder
import gusev.max.spotsearch.utils.getImageByActionName
import gusev.max.spotsearch.utils.makeGradientBackGroundCardView
import kotlinx.android.synthetic.main.item_action.view.*

/**
 * Created by v on 13/06/2018.
 */
class ActionsViewHolder(
    itemView: View,
    clickListener: ModelClickListener<ActionModel>
) : BaseViewHolder<ActionModel>(itemView, clickListener) {

    override fun bind(model: ActionModel) {
        super.bind(model)
        itemView.name.text = model.name
        itemView.description.text = model.description
        itemView.action_avatar.setImageDrawable(getImageByActionName(itemView.context, model.name))
        itemView.container.background = makeGradientBackGroundCardView(model.primaryColor,model.secondaryColor)
    }
}