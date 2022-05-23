package com.dimdimbjg.ufa.ui.form

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.dimdimbjg.ufa.R
import com.dimdimbjg.ufa.databinding.ActivityFormPerubahanBinding

class FormPerubahanActivity : AppCompatActivity() {

    private var items: Array<String>? = null
    private var adapterItems: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFormPerubahanBinding = ActivityFormPerubahanBinding.inflate(layoutInflater)
        setContentView(activityFormPerubahanBinding.root)
        items = if (savedInstanceState != null) {
            savedInstanceState.getStringArray("Items")
        } else {
            this.resources.getStringArray(R.array.items_array)
        }
        adapterItems = ArrayAdapter(this, R.layout.list_item, items as Array<String>)
        activityFormPerubahanBinding.actvItem.setAdapter(adapterItems)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArray("Items", items)
    }
}