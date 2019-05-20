package com.coding.applicationchallenge.ui.details

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.coding.applicationchallenge.R
import com.coding.applicationchallenge.di.component.DaggerActivityComponent
import com.coding.applicationchallenge.di.module.ActivityModule
import com.coding.applicationchallenge.models.User
import com.coding.applicationchallenge.util.Constants
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_details.*
import javax.inject.Inject



class DetailsActivity : AppCompatActivity(), DetailContract.View, OnMapReadyCallback {

    @Inject
    lateinit var presenter: DetailContract.Presenter

    lateinit var user: User
    var position: Int = -1
    var isDataUpdated : Boolean = false
    private lateinit var mMap: GoogleMap
    //private var mMap: GoogleMap? = null
    private lateinit var location : LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_details)
        supportActionBar?.hide()

        Log.d(TAG, "======onCreate==========>")
        injectDependency()

        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    private fun initView() {
        Log.d(TAG, "======initView==========>$presenter")
        var bundle :Bundle ?=intent.extras
        position = intent.getIntExtra(Constants.DATA_USER_POSITION, -1)
        user  = bundle!!.getParcelable(Constants.DATA_USER_OBJECT)
        presenter.loadData(user, position)


    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    override fun loadDataSuccess(user: User) {
        personUserName.text = user.username
        personName.text = user.name
        detailsUserPhone.text = user.phone
        detailsUserEmail.text = user.email
        detailsUserWebsite.text = user.website

        if (user.company != null) {
            detailsUserCompany.text = user.company.name
            detailsUserCatchPhrase.text = user.company.catchPhrase
            detailsUserBusiness.text = user.company.bs
        }

        if (user.address != null) {
            var sb : StringBuffer = StringBuffer().
                    append(user.address.street).
                    append("\n").
                    append(user.address.suite).
                    append("\n").
                    append(user.address.city).
                    append("\n").
                    append(user.address.zipcode)
            detailsUserAddress.text = sb.toString()
            if (user.address.geo != null) {
                location = LatLng(user.address.geo.lat.toDouble(), user.address.geo.lng.toDouble())
                Log.d(TAG, "======user.address==========>"+location)

                val mapFragment = supportFragmentManager
                        .findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)

            }
        }

        Picasso.with(this).load(R.drawable.default_user).into(profileContactPhoto)
    }

    fun onClickBackContact(view: View) {
        finish()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onBackPressed() {
        if(isDataUpdated) {
            val result = Intent()
            result.putExtra(Constants.DATA_USER_POSITION, position);
            result.putExtra(Constants.DATA_USER_OBJECT, user)
            setResult(Activity.RESULT_OK, result)
        } else {
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }

    companion object {
        val TAG: String = "DetailsActivity"
    }

}