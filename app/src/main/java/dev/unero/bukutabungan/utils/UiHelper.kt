package dev.unero.bukutabungan.utils

import android.content.Context
import android.widget.Toast
import java.text.NumberFormat
import java.util.*

object UiHelper {
    fun convertCurrency(number: Int): String =
        NumberFormat.getCurrencyInstance(Locale(LANGUAGE, COUNTRY)).format(number)

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}