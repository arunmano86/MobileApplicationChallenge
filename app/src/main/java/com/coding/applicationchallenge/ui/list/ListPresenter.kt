package com.coding.applicationchallenge.ui.list

import android.util.Log
import com.coding.applicationchallenge.api.ApiServiceInterface
import com.coding.applicationchallenge.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListPresenter : ListContract.Presenter{
    private val subscriptions = CompositeDisposable()
    private lateinit var view: ListContract.View
    private val api: ApiServiceInterface = ApiServiceInterface.create()

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListContract.View) {
        Log.d("ContactListPresenter", "======attach==========>")
        this.view = view
        //view.showListFragment() // as default
    }

    override fun loadData() {
        Log.d("ContactListPresenter", "======loadData==========>")
        this.view.showProgress(true)
        var subscribe = api.getUsersList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ users: List<User> ->
                    view.showProgress(false)
                    view.loadDataSuccess(users)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun loadDataAll() {
    }

    override fun deleteItem(item: User) {

    }

    override fun updateItem(item: User) {

    }


//    override fun onDrawerOptionAboutClick() {
//        view.showAboutFragment()
//    }
}