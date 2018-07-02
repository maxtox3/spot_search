package gusev.max.spotsearch.main.event

import android.content.Context
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.gms.maps.model.LatLng
import gusev.max.presentation.model.ActionModel
import gusev.max.spotsearch.R
import gusev.max.spotsearch.base.fragment.dialog.BaseDialogFragment
import gusev.max.spotsearch.mapper.EventMapper
import kotlinx.android.synthetic.main.dialog_create_event.*
import javax.inject.Inject

/**
 * Created by v on 15/06/2018.
 */
class CreateEventDialogFragment : BaseDialogFragment() {

    private lateinit var listener: CreateEventCallback

    @Inject
    lateinit var mapper: EventMapper

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = parentFragment as CreateEventCallback
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement createEventCallback")
        }
        super.onAttach(context)
    }

    internal fun setupForActionsLoadSucces(latLng: LatLng, actions: List<ActionModel>) {
        progress_bar.visibility = View.GONE
        action_spinner.setOnClickListener { v ->
            MaterialDialog.Builder(context!!)
                .title(R.string.choose_type)
                .items(actions.map { it.name })
                .itemsCallback({ dialog, _, which, text ->
                    action_type.text = text
                    create_event_btn.setOnClickListener {
                        listener.createEvent(
                                mapper.mapToCreateModel(
                                        actionId = actions[which].id,
                                        name = action_type.text.toString(),
                                        description = event_description.text.toString(),
                                        latLng = latLng
                                )
                        )
                    }
                    dialog.dismiss()
                })
                .show()
        }
    }

    override fun getContainerId(): Int {
        return R.layout.dialog_create_event
    }

    override fun setupWidgets() {

    }

    fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    fun hideLoading() {
        progress_bar.visibility = View.GONE
    }
}