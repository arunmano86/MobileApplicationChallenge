package com.coding.applicationchallenge.ui.details

import com.coding.applicationchallenge.base.BaseContract
import com.coding.applicationchallenge.models.User

class DetailContract {
    interface View: BaseContract.View {
        //fun showProgress(show: Boolean)
        //fun showErrorMessage(error: String)
        fun loadDataSuccess(user: User)
    }

    interface Presenter: BaseContract.Presenter<DetailContract.View> {
        fun loadData(user: User, position: Int)
    }
}