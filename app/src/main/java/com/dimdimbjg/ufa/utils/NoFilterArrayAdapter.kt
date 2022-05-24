package com.dimdimbjg.ufa.utils

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter

//this is class to solve current bug on Auto Complete
//Text View Adapter which not saving state after the device is rotated

class NoFilterArrayAdapter : ArrayAdapter<Any?> {

    constructor(context: Context, resource: Int) : super(context, resource)
    constructor(context: Context, resource: Int, objects: Array<out Any?>) : super(context,
        resource,
        objects)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                return null
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {}
        }
    }

}