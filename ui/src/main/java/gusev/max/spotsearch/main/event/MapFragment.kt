package gusev.max.spotsearch.main.event

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import dagger.android.support.AndroidSupportInjection
import gusev.max.domain.model.map.LatLngModel
import gusev.max.presentation.main.map.MapIntent
import gusev.max.presentation.main.map.MapViewModel
import gusev.max.presentation.main.map.MapViewState
import gusev.max.presentation.model.ActionModel
import gusev.max.presentation.model.EventModel
import gusev.max.spotsearch.R
import gusev.max.spotsearch.base.fragment.BaseLceFragment
import gusev.max.spotsearch.main.MainActivityCallback
import gusev.max.spotsearch.main.actions.ActionsAdapter
import gusev.max.spotsearch.mapper.ActionMapper
import gusev.max.spotsearch.mapper.EventMapper
import gusev.max.spotsearch.model.EventUIModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.bottom_map_card_view.*
import kotlinx.android.synthetic.main.fragment_map.*
import javax.inject.Inject


/**
 * Created by v on 13/06/2018.
 */
class MapFragment :
        BaseLceFragment<MapIntent, MapViewState, MapViewModel, MainActivityCallback>(),
        OnMapReadyCallback, CreateEventCallback {

    private val getEventsPublisher: PublishSubject<MapIntent.BrowseEvents> = PublishSubject.create()
    private val getActionsPublisher: PublishSubject<MapIntent.BrowseActions> = PublishSubject.create()

    private val createMarkerPublisher: PublishSubject<MapIntent.NewMarker> = PublishSubject.create()
    private val createEventPublisher: PublishSubject<MapIntent.CreateNewEvent> = PublishSubject.create()

    private val tryLoadActionsAgainPublisher: PublishSubject<MapIntent.TryLoadActionAgain> = PublishSubject.create()
    private val tryLoadEventsAgainPublisher: PublishSubject<MapIntent.TryLoadEventsAgain> = PublishSubject.create()

    @Inject
    lateinit var eventMapper: EventMapper
    @Inject
    lateinit var actionMapper: ActionMapper
    @Inject
    lateinit var actionsAdapter: ActionsAdapter

    private var map: GoogleMap? = null
    private val dialog: CreateEventDialogFragment? = CreateEventDialogFragment()
    private lateinit var clusterManager: ClusterManager<EventUIModel>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment: SupportMapFragment? =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        getActionsPublisher.onNext(MapIntent.BrowseActions)
    }

    /**
     * Map
     */

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        clusterManager = ClusterManager<EventUIModel>(context, map)
        setupMap()
        getEvents()
    }

    private fun setupMap() {
        map?.setOnCameraIdleListener(clusterManager)
        map?.setOnMarkerClickListener(clusterManager)
        //todo map?.setOnClickListener({ тут надо чтобы скрывались все вьюхи })
        map?.setOnMapLongClickListener({ latLng ->
            createMarkerPublisher
                .onNext(MapIntent.NewMarker(eventMapper.transformGoogleLatLngToModel(latLng)))
        })
        map?.setOnMapClickListener {
            hideView(bottom_sheet, Techniques.SlideOutDown)
            hideView(recycler_view, Techniques.SlideOutUp)
        }
    }

    /**
     * BaseView impl
     */

    override fun intents(): Observable<MapIntent> {
        return Observable.mergeArray(
                Observable.just(MapIntent.InitialIntent),
                getActionsPublisher,
                getEventsPublisher,
                createMarkerPublisher,
                createEventPublisher,
                tryLoadEventsAgainPublisher,
                tryLoadActionsAgainPublisher
        )
    }

    override fun render(state: MapViewState) {
        when (state) {
            is MapViewState.InProgress -> setupScreenForLoading()
            is MapViewState.CreateInProgress -> dialog?.showLoading()
            is MapViewState.LoadActionsFailed -> setupScreenForError(resources.getString(R.string.general_error))
            is MapViewState.LoadEventsFailed -> setupScreenForError(resources.getString(R.string.general_error))
            is MapViewState.CreateEventFailed -> setupScreenForError(resources.getString(R.string.general_error))
            is MapViewState.ActionsLoaded -> setupForActionsSuccess(state.actionList)
            is MapViewState.EventsLoaded -> setupForEventsSuccess(state.eventList)
            is MapViewState.CreateEventDialogState -> setupForDialog(
                    state.latLngModel,
                    state.actions
            )
            is MapViewState.CreateEventSuccess -> setupForCreateEventSuccess()
        }
    }

    /**
     * render events and comments
     */

    private fun setupForEventsSuccess(events: List<EventModel>) {
        getLoadingView().visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
        if (events.isNotEmpty()) {
            addMarkers(events)
        } else {
            setupScreenForError(resources.getString(R.string.error_empty))
        }
    }

    private fun setupForActionsSuccess(actions: List<ActionModel>) {
        getLoadingView().visibility = View.GONE
        if (actions.isNotEmpty()) {
            actionsAdapter.setData(actions.map { actionMapper.mapToUIModel(it) })
        } else {
            setupScreenForError(resources.getString(R.string.error_empty))
        }
    }

    private fun addMarkers(events: List<EventModel>) {
        map?.clear()
        clusterManager.clearItems()
        events.forEach { event ->
            clusterManager.addItem(eventMapper.mapToUIModel(event))
            clusterManager.setOnClusterItemClickListener { eventUIModel ->
                if (eventUIModel != null) {
                    setupInfoCard(eventUIModel)
                    showView(bottom_sheet, Techniques.SlideInUp)
                    showView(recycler_view, Techniques.SlideInDown)
                    bottom_sheet.setOnClickListener {
                        hideView(bottom_sheet, Techniques.SlideOutDown)
                        activityCallback.navigateToEventFullInfo(eventUIModel)
                    }
                }
                true
            }
        }
    }

    private fun setupInfoCard(eventUIModel: EventUIModel) {
        event_name.text = eventUIModel.name
        like_count.text = eventUIModel.likesCount.toString()
        dislike_count.text = eventUIModel.dislikesCount.toString()
    }

    private fun getEvents() {
        getEventsPublisher.onNext(
                MapIntent.BrowseEvents(
                        actionId = actionsAdapter.getChosenId(),
                        visibleScreenBounds = eventMapper.transformGoogleBoundsToModel(
                                map?.projection?.visibleRegion?.latLngBounds!!
                        )
                )
        )
    }

    /**
     * render createEventDialog
     */

    private fun setupForDialog(latLngModel: LatLngModel, actions: List<ActionModel>?) {
        if (actions != null) {
            dialog?.hideLoading()
            dialog?.setupForActionsLoadSucces(
                    eventMapper.transformModelLatLngToGoogle(latLngModel),
                    actions
            )
        }
    }

    private fun setupForCreateEventSuccess() {
        dialog?.hideLoading()
        dialog?.onDestroy()
        getEvents()
    }

    /**
     * createEventCallback impl
     */

    override fun createEvent(eventUIModel: EventUIModel) {
        createEventPublisher.onNext(
                MapIntent.CreateNewEvent(
                        actionId = eventUIModel.actionId,
                        name = eventUIModel.name,
                        description = eventUIModel.description,
                        latLng = eventMapper.transformGoogleLatLngToModel(eventUIModel.latLng)
                )
        )
    }

    /**
     * BaseLceFragment impl
     */

    override fun initActivityCallback(context: Context?) {
        activityCallback = context as MainActivityCallback
    }

    override fun subscribeForUpdatesFromViewModel() {
        super.subscribeForUpdatesFromViewModel()
        compositeDisposable.add(actionsAdapter.mainModelClickObservable.subscribe { getEvents() })
    }

    override fun getContainerId(): Int {
        return R.layout.fragment_map
    }

    override fun setupWidgets() {
        recycler_view.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        recycler_view.adapter = actionsAdapter
    }

    override fun getViewModel(): MapViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)
    }

    override fun getLoadingView(): View {
        return progress_bar
    }

    private fun showView(view: View, technique: Techniques) {
        if (view.visibility == View.GONE) {
            view.visibility = View.VISIBLE
            YoYo.with(technique).duration(300).playOn(view)
        }
    }

    private fun hideView(view: View, technique: Techniques) {
        if (view.visibility == View.VISIBLE) {
            YoYo.with(technique).duration(300).playOn(view)
            view.visibility = View.GONE
        }
    }
}