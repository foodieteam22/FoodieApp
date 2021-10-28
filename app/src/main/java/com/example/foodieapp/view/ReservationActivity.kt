package com.example.foodieapp.view

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.foodieapp.R
import java.util.*


class ReservationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val desks = arrayOf("Masa Seç","1","2","3")
        val personCount = arrayOf("Kişi Sayısı","1","2","3","4")
        val hours = arrayOfNulls<String>(24)

        val arrayAdapterDesks = ArrayAdapter(this, android.R.layout.simple_spinner_item,desks)
        val arrayAdapterPersonCount = ArrayAdapter(this, android.R.layout.simple_spinner_item,personCount)
        val arrayAdapterHours = ArrayAdapter(this, android.R.layout.simple_spinner_item,hours)

        val spinnerDesk = findViewById<Spinner>(R.id.spinnerDesk)
        val spinnerPersonCount = findViewById<Spinner>(R.id.spinnerPersonCount)
        val spinnerHour = findViewById<Spinner>(R.id.spinnerHour);

        for (i in 0..23) {
            hours[i] = Integer.toString(i).plus(":00").plus(" - ").plus(Integer.toString(i+1).plus(":00"))
        }


        spinnerDesk.adapter = arrayAdapterDesks
        spinnerDesk.onItemSelectedListener = this
        spinnerPersonCount.adapter = arrayAdapterPersonCount
        spinnerPersonCount.onItemSelectedListener = this
        spinnerHour.adapter = arrayAdapterHours
        spinnerHour.onItemSelectedListener = this
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        val toast = Toast.makeText(this,"You Selected:\n "+parent?.getItemAtPosition(position),Toast.LENGTH_SHORT)
        toast?.show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?)
    {

    }
}
