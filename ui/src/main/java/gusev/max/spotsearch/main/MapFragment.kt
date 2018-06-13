package gusev.max.spotsearch.main

/**
 * Created by v on 13/06/2018.
 */
//class MapFragment :
//        BaseLceFragment<BrowseEventsIntent, BrowseEventsViewState, BrowseEventsViewModel, MainActivityCallback>(),
//        OnMapReadyCallback, GoogleMap.OnMapLongClickListener,
//        GoogleMap.OnMarkerClickListener {
//
//    override fun onAttach(context: Context?) {
//        AndroidSupportInjection.inject(this)
//        super.onAttach(context)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val mapFragment: SupportMapFragment? = activity?.supportFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment?
//        mapFragment?.getMapAsync(this)
//    }
//
//    override fun onMapReady(googleMap: GoogleMap?) {
//        val bounds = googleMap?.projection?.visibleRegion?.latLngBounds
////        googleMap ?: return
////        with(googleMap) {
////            moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, ZOOM_LEVEL))
////            addMarker(MarkerOptions().position(SYDNEY))
////        }
//    }
//
//    override fun onMapLongClick(googleMap: LatLng?) {
//
//    }
//
//    override fun onMarkerClick(googleMap: Marker?): Boolean {
//
//    }
//
//    override fun intents(): Observable<BrowseEventsIntent> {
//
//    }
//
//    override fun render(state: BrowseEventsViewState) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun initActivityCallback(context: Context?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getContainerId(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun setupWidgets() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getViewModel(): BrowseEventsViewModel {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getLoadingView(): View {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun handleThrowable(throwable: Throwable) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun showError(message: String) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}