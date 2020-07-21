package com.app.creativetask.ui.main.ordersscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.creativetask.R
import com.app.creativetask.utils.Constants.KEY.SCREEN_POSITION
import kotlinx.android.synthetic.main.orders_fragment.*


/**
 * Created by Emad Mohamed Oun on 7/21/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

class OrdersFragment : Fragment() {

    private val ordersRecyclerView: RecyclerView get() = orders_list

    private var position: Int = 0

    companion object {
        fun newInstance(position: Int): OrdersFragment {
            val obj = OrdersFragment()
            val bundle = Bundle()
            bundle.putInt(SCREEN_POSITION, position)
            obj.arguments = bundle
            return obj
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orders_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiveArgs()


    }

    private fun receiveArgs() {
        position = arguments?.getInt(SCREEN_POSITION)!!

    }

    override fun onResume() {
        super.onResume()
        setUpOrderAdapter()
    }

    private fun setUpOrderAdapter() {
        ordersRecyclerView.adapter = OrdersAdapter(activity!!, position)
    }
}