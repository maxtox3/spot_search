package gusev.max.spotsearch.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import dagger.android.AndroidInjection
import gusev.max.spotsearch.R
import gusev.max.spotsearch.base.activity.BaseActivityFragmentContainer
import gusev.max.spotsearch.main.actions.ActionsListFragment
import gusev.max.spotsearch.main.event.EventFullInfoFragment
import gusev.max.spotsearch.main.event.MapFragment
import gusev.max.spotsearch.model.EventUIModel
import gusev.max.spotsearch.utils.Constants


class MainActivity : BaseActivityFragmentContainer(), MainActivityCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigateToMap()
        }
    }

    override fun navigateToMap() {
        navigateToFragment(Constants.MAP_FRAGMENT, null, false)
    }

    override fun navigateToActions() {
        navigateToFragment(Constants.ACTIONS_FRAGMENT, null, false)
    }

    override fun navigateToEventFullInfo(event: EventUIModel) {
        val fragmentTransaction = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                    R.animator.slide_up,
                    0,
                    0,
                    R.animator.slide_down
            ).addToBackStack(null)
        add(
                fragmentTransaction,
                EventFullInfoFragment.newInstance(event),
                Constants.FULL_INO_FRAGMENT
        )
    }


    override fun createFragment(tag: String, args: Bundle?): Fragment {
        when (tag) {
            Constants.MAP_FRAGMENT -> return MapFragment()
            Constants.ACTIONS_FRAGMENT -> return ActionsListFragment()
            else -> Log.i("createFragment: ", "you must add your fragment")
        }
        return Fragment()
    }

    override fun getContentViewResourceId(): Int {
        return R.layout.activity_main
    }

    override fun setContainerId() {
        setContainerId(R.id.fragment_container)
    }

    override fun setupWidgets() {
        val nav = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        nav.setOnNavigationItemSelectedListener(
                { item ->
                    when (item.itemId) {
                        R.id.map_button -> navigateToMap()
//                        R.id.top_button -> navigateToTop()
//                        R.id.favorites_button -> navigateToFavorites()
//                        R.id.profile_button -> navigateToProfile()
                    }
                    true
                })
    }
}
