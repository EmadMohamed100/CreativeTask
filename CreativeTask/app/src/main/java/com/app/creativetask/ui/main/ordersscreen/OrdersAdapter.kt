package com.app.creativetask.ui.main.ordersscreen

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.creativetask.R
import com.app.creativetask.databinding.ItemOrdersBinding


/**
 * Created by Emad Mohamed Oun on 24/6/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

class OrdersAdapter constructor(val context: Context, val tabPosition: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemOrdersBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_orders, parent, false
        )
        return ViewHolderHash(binding)
    }

    override fun getItemCount(): Int = 2

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderHash).bind()
    }

    inner class ViewHolderHash(private val binding: ItemOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            fillViewWithData(binding)
        }

        private fun fillViewWithData(binding: ItemOrdersBinding) {
            when (tabPosition) {
                0 -> {
                    binding.cancelButton.visibility = View.GONE
                    binding.screenName.text = context.getString(R.string.canceled_tab_txt)
                }
                1 -> {
                    binding.screenName.text = context.getString(R.string.accepted_tab_txt)
                }
                2 -> {
                    binding.screenName.text = context.getString(R.string.waiting_tab_txt)
                }
            }
        }
    }
}