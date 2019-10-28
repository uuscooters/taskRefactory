package com.example.kotlinfundamental.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions
import com.example.kotlinfundamental.R
import com.example.kotlinfundamental.activity.data.ContactData
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    var arrayList: ArrayList<ContactData> = ArrayList()

    class ViewHolder(pivate val view: View) : RecyclerView.ViewHolder(view) {
        fun binData(contactData: ContactData) {
            Glide.with(view).load(contactData.image_url).apply(RequestOptions().circleCrop()).into(view.ivProfile)

            view.tvName.text = contactData.name
            view.tvNumber.text = contactData.phone

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int { return arrayList.size   }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binData(arrayList[position])
        holder.itemView.tvName.text = contactModel.name
        holder.itemView.tvNumber.text = contactModel.phone


        Glide.with(holder.itemView).load(listContact[position].image).apply(options).into(holder.itemView.ivProfile)
    }

    class OnListener {
        fun onDeletePost(int: Int) {}
        fun onCliskPost(int: Int) {}
    }


}
