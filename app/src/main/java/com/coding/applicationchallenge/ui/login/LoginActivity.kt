package com.coding.applicationchallenge.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.coding.applicationchallenge.R
import com.coding.applicationchallenge.di.component.DaggerActivityComponent
import com.coding.applicationchallenge.di.module.ActivityModule
import com.coding.applicationchallenge.ui.list.ListActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        injectDependency()

        presenter.attach(this)
        presenter.subscribe()
    }

    override fun resetError() {
        loginUserNameTextInputLayout.error = null
        loginPasswordTextInputLayout.error = null
    }

    override fun showUserNameError(resId : Int) {
        loginUserNameTextInputLayout.error = getString(resId)
    }

    override fun showPasswordError(resId : Int) {
        loginPasswordTextInputLayout.error = getString(resId)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            loginProgress.visibility = View.VISIBLE
        } else {
            loginProgress.visibility = View.GONE
        }
    }

    fun onLoginClick(view : View) {
        presenter.login(loginUserNameTextInputET.text.toString(), loginPasswordTextInputET.text.toString())
    }

    override fun showValidationErrorMsg() {

    }

    override fun loginSuccessFully() {
        Toast.makeText(this, "Login SuccessFully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    override fun loginFail() {
        Toast.makeText(this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
    }

}