package com.dimdimbjg.ufa.ui.formubah

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.databinding.ActivityFormPerubahanBinding
import com.dimdimbjg.ufa.utils.NoFilterArrayAdapter

class FormPerubahanActivity : AppCompatActivity() {

    private var items : Array<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFormPerubahanBinding = ActivityFormPerubahanBinding.inflate(layoutInflater)
        setContentView(activityFormPerubahanBinding.root)

        items = resources.getStringArray(R.array.items_array)

        val adapterItems = NoFilterArrayAdapter(this, R.layout.list_item,
            items as Array<out String>)
        activityFormPerubahanBinding.actvItem.setAdapter(adapterItems)
    }
}