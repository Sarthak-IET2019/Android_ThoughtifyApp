package io.realworld.quotes_app

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel() {

    private var quotelist: Array<Quote> = emptyArray()
    private var index = 0

    init{
        quotelist = loadQuotesfromAssets()
    }

    private fun loadQuotesfromAssets() : Array<Quote>{

        val inputString = context.assets.open("quotes.json")
        val size: Int = inputString.available()
        val buffer = ByteArray(size)
        inputString.read(buffer)
        inputString.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)

    }
    fun getQuote() = quotelist[index]

    fun nextQuote() = quotelist[++index]

    fun previousQuote() = quotelist[--index]


}