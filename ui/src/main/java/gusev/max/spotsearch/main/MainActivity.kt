package gusev.max.spotsearch.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import dagger.android.AndroidInjection
import gusev.max.spotsearch.R
import gusev.max.spotsearch.base.activity.BaseActivityFragmentContainer
import gusev.max.spotsearch.main.activities.ActionsListFragment
import gusev.max.spotsearch.utils.Constants

class MainActivity : BaseActivityFragmentContainer(), MainActivityCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigateToActions()
        }
    }

    override fun navigateToMap() {

    }

    override fun navigateToActions() {
        navigateToFragment(Constants.ACTIONS_FRAGMENT, null, false)
    }

    override fun createFragment(tag: String, args: Bundle?): Fragment {
        when (tag) {
//            Constants.MAP_FRAGMENT -> return MapFragment()
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


}
