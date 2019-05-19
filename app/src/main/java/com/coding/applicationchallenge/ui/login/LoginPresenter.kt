package com.coding.applicationchallenge.ui.login

import android.text.TextUtils
import com.coding.applicationchallenge.R
import com.coding.applicationchallenge.models.Login
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter : LoginContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: LoginContract.View

    override fun login(userName: String, password: String) {
        this.view.showProgress(true)

        this.view.resetError()
        if (TextUtils.isEmpty(userName)){
            this.view.showUserNameError(R.string.empty_user_name)
        } else if (TextUtils.isEmpty(password)) {
            this.view.showPasswordError(R.string.empty_password)
        } else {
            if (userName.equals("username") && password.equals("123456")) {
                this.view.loginSuccessFully();
            } else {
                this.view.loginFail();
            }
        }
        this.view.showProgress(false)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: LoginContract.View) {
        this.view = view
    }
}