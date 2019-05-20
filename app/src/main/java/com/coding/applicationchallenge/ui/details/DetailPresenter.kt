package com.coding.applicationchallenge.ui.details

import android.util.Log
import com.coding.applicationchallenge.models.User
import com.coding.applicationchallenge.ui.details.DetailContract
import io.reactivex.disposables.CompositeDisposable

class DetailPresenter : DetailContract.Presenter{
    private val subscriptions = CompositeDisposable()
    private lateinit var view: DetailContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailContract.View) {
        Log.d("ContactListPresenter", "======attach==========>")
        this.view = view
        //view.showListFragment() // as default
    }

    override fun loadData(user: User, position: Int) {
        Log.d("ContactListPresenter", "======loadData==========>")
        view.loadDataSuccess(user)
    }
}