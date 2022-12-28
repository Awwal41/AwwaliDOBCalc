package bgtap.babbangona.awwalidobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
   private var theSelectedDate: TextView? = null
    private var  ageInminutes: TextView? = null
    private var ageInyears: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateButton: Button = findViewById(R.id.btnDatePicker)
          theSelectedDate=findViewById(R.id.theSelectedDate)
          ageInminutes =findViewById(R.id.ageInminutes)
          ageInyears=findViewById(R.id.ageInyears)
        
                dateButton.setOnClickListener {
                    clickDatePicker()
                }

    }
  private fun clickDatePicker(){
        val myCalendo = Calendar.getInstance()
        val year= myCalendo.get(Calendar.YEAR)
        val month= myCalendo.get(Calendar.MONTH )
        val day= myCalendo.get(Calendar.DAY_OF_MONTH)
        val dpd= DatePickerDialog(this,
             { _, selectedyear, selectedmonth, selectedDayOfMonth ->

                Toast.makeText(this, "Your age in years and minutes has been calculated", Toast.LENGTH_SHORT).show( )

                         val selectedDate = "$selectedDayOfMonth/${selectedmonth+1}/$selectedyear"

                             theSelectedDate?.text=(selectedDate)
                 
                 val sdf =  java.text.SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                 val theDate = sdf.parse(selectedDate)
                 theDate?.let {
                     val selectedDateInMinutes = theDate.time/60000
                     val selectedDateInYear = selectedDateInMinutes/525600


                     val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                     currentDate?.let {
                     val currentDateInMinutes = currentDate.time/60000
                         val currentDateInYear = currentDateInMinutes/525600

                     val differenceInMinutes = currentDateInMinutes -selectedDateInMinutes
                         val differenceInYear = currentDateInYear -selectedDateInYear
                     ageInminutes?.text= differenceInMinutes.toString()
                         ageInyears?.text= differenceInYear.toString()
                     }
                 }

             },
            year,
            month,
            day,
        )
dpd.datePicker.maxDate= System.currentTimeMillis()
        dpd.show()
    }
}