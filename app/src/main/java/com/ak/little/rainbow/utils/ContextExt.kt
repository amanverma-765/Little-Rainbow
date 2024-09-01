package com.ak.little.rainbow.utils

import android.content.Context
import android.health.connect.datatypes.units.Length
import android.widget.Toast

fun Context.toast(msg: String?, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        this,
        msg,
        length
    ).show()
}
