package com.example.kotlinfundamental.activity.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.util.Util
import com.example.kotlinfundamental.R
import com.example.kotlinfundamental.activity.activity.ProfileActivity
import com.example.kotlinfundamental.activity.data.ContactData
import com.example.kotlinfundamental.activity.helper.Utils
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    var arrayList: ArrayList<ContactData> = ArrayList()

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun binData(contactData: ContactData) {
            Glide.with(view).load(contactData.image_url).apply(RequestOptions().circleCrop()).into(view.ivProfile)

            view.tvName.text = contactData.name
            view.tvNumber.text = contactData.phone

            view.llMain.setOnClickListener{
                view.context.startActivity(Intent(view.context, ProfileActivity::class.java)
                    .putExtra(Utils.ID_CONTACT, contactData.id))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)

        return ViewHolder(view)
    }


    override fun getItemCount(): Int { return arrayList.size   }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(arrayList[position])

    }

}
