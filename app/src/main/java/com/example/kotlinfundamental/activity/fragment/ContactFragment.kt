package com.example.kotlinfundamental.activity.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinfundamental.BuildConfig
import com.example.kotlinfundamental.R
import com.example.kotlinfundamental.activity.activity.ProfileActivity
import com.example.kotlinfundamental.activity.adapter.ContactAdapter
import com.example.kotlinfundamental.activity.data.ResponseData
import com.example.kotlinfundamental.activity.helper.Utils
import kotlinx.android.synthetic.main.fragment_contact.*
import retrofit2.Call
import retrofit2.Response

class ContactFragment : Fragment() {

    private val contactAdapter = ContactAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecycleView()
        initEvent()
    }

    override fun onResume() {
        super.onResume()

        getData()
    }

    private fun getData() {
        srlMain.isRefreshing = true

        API.getContacts(object: retrofit2.Callback<ResponseData>{
            override fun onFailure(call: Call<ResponseData>, t: Throwable){
                srlMain.isRefreshing = false

                if (BuildConfig.DEBUG) {
                    Utils.showToast(activity!!, t.message!!)
                }
            }

            override fun onResponse(call: Call<ResponseData>, response : Response<ResponseData>) {

                srlMain.isRefreshing = false

                if (response.isSuccessful) {
                    contactAdapter.arrayList = response.body()!!.data
                    contactAdapter.notifyDataSetChanged()
                }else {
                    if (BuildConfig.DEBUG) {
                        Utils.showToast(activity!!, response.message())
                    }
                }
            }
        })
    }

    private fun initRecycleView() {

        val liniearLayoutManager = LinearLayoutManager(activity!!)
        liniearLayoutManager.orientation = LinearLayoutManager.VERTICAL


        rvContact.apply {
            setHasFixedSize(true)
            adapter = contactAdapter
            layoutManager = liniearLayoutManager

        }
    }


    private fun initEvent() {
        fabadd.setOnClickListener { startActivity(Intent(activity!!, ProfileActivity::class.java)) }

        srlMain.setOnRefreshListener { getData() }
    }
}
