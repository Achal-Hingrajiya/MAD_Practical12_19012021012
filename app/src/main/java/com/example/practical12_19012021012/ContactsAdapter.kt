package com.example.practical12_19012021012

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ContactsAdapter(private val context: Context, private val dataSource : ArrayList<User>) : BaseAdapter() {
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(p0: Int): User {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return getItem(p0).id
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val rowView = inflater.inflate(R.layout.contact_card, p2, false)

        val tvName = rowView.findViewById<TextView>(R.id.tv_name)
        val tvPhone = rowView.findViewById<TextView>(R.id.tv_phone)
        val tvAddress = rowView.findViewById<TextView>(R.id.tv_address)

        val user = getItem(p0)
        tvName.text = user.name.getName()
        tvPhone.text = user.phone
        tvAddress.text = user.address

        return  rowView
    }
}