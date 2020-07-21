package com.milo.presentation.ui.utilities

import com.google.android.material.button.MaterialButton
import com.milo.app.R
import com.milo.presentation.ui.main.signup.SignUpFragment
import kotlinx.android.synthetic.main.terms_conditions_dialog.view.*


/**
 * Created by Emad Mohamed Oun on 2/4/2020.
 * Rytalo
 * emad.3oon@gmail.com
 */

fun SignUpFragment.showTermsAndConditionsDialog() {
    val dialogView = android.view.LayoutInflater.from(activity).inflate(R.layout.terms_conditions_dialog, null)
    dialogView.setBackgroundColor(android.graphics.Color.TRANSPARENT)
    val dialogBuild = androidx.appcompat.app.AlertDialog.Builder(activity!!).setView(dialogView)
    val dialogBuilder = dialogBuild.show()

    val accept_bt = dialogView.findViewById(R.id.accept_bt) as MaterialButton
    accept_bt.setOnClickListener {
        dialogBuilder.dismiss()
    }

    dialogView.terms_read_later_tv.setOnClickListener {
        dialogBuilder.dismiss()
    }

    dialogBuilder.show()
}