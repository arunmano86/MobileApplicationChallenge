package com.coding.applicationchallenge.ui.login

import com.coding.applicationchallenge.base.BaseContract
import com.coding.applicationchallenge.db.DatabaseHandler
import com.coding.applicationchallenge.models.Login

class LoginContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showValidationErrorMsg()
        fun loginSuccessFully()
        fun loginFail()

        fun showUserNameError(resId : Int)
        fun showPasswordError(resId : Int)
        fun resetError()
    }

    interface Presenter: BaseContract.Presenter<LoginContract.View> {
        fun login(userName: String, password: String, dbHandler : DatabaseHandler)
    }
}