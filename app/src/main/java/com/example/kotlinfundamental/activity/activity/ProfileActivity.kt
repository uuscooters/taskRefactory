package com.example.kotlinfundamental.activity.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlinfundamental.BuildConfig
import com.example.kotlinfundamental.R
import com.example.kotlinfundamental.activity.data.ContactData
import com.example.kotlinfundamental.activity.fragment.API
import com.example.kotlinfundamental.activity.helper.Utils
import com.example.kotlinfundamental.activity.helper.Utils.Companion.ID_CONTACT
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ProfileActivity : AppCompatActivity() {

    private val contactData = ContactData
    private var id: Int = -1
    private var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initData()
        initView()
        initEvent()
    }

    private fun initEvent() {
        ivProfileEdit.setOnClickListener { finish() }
        tvSubmit.setOnClickListener { handleContact() }

    }

    private fun handleContact() {
        if (isUpdate){
            contactData.name
        }else {

        }
    }

    private fun initData() {
        if (isUpdate) {
            API.getDetail(id, object: retrofit2.Callback<ContactData> {
                override fun onResponse(call: Call<ContactData>, response: Response<ContactData>) {


                    if (response.isSuccessful) {

                        etInputName.setText(response.body()!!.name)
                        etInputPhone.setText(response.body()!!.phone)
                        etInputEmail.setText("kinetsunoyaiba@japan.com")

                        Glide.with(this@ProfileActivity).load(response.body()!!.image_url)
                            .apply(RequestOptions().circleCrop()).into(ivProfileEdit)
                    }else {
                        if (BuildConfig.DEBUG) {
                            Utils.showToast(this@ProfileActivity, response.message())
                        }
                    }

                }

                override fun onFailure(call: Call<ContactData>, t: Throwable) {
                    if (BuildConfig.DEBUG) {
                        Utils.showToast(this@ProfileActivity, t.message!!)
                    }

                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {

        id = intent.getIntExtra(Utils.ID_CONTACT, -1)

        if (id == -1) {
            tvTitle.text = "Create"
            tvSubmit.text = "Add"
            isUpdate = false
        }else {
            tvTitle.text = "Edit"
            tvSubmit.text = "Save"
            isUpdate = true
        }
    }

}
