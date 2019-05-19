package com.coding.applicationchallenge.ui.list

import com.coding.applicationchallenge.base.BaseContract
import com.coding.applicationchallenge.models.User

class ListContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<User>)
    }

    interface Presenter: BaseContract.Presenter<ListContract.View> {
        fun loadData()
        fun loadDataAll()
        fun deleteItem(item: User)
        fun updateItem(item: User)
    }
}