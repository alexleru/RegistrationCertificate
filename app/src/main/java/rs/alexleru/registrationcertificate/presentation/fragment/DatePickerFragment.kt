package rs.alexleru.registrationcertificate.presentation.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import rs.alexleru.registrationcertificate.R
import java.util.Calendar
import kotlin.properties.Delegates

class DatePickerFragment(

) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    private var currentValueCalendar: Calendar? = null
    private var viewId by Delegates.notNull<Int>()//TODO is it ok?
    private lateinit var returnValue: (Calendar, Int) -> Unit //int - viewId
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = currentValueCalendar ?: Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            R.style.MySpinnerDatePickerStyle,
            this,
            year,
            month,
            day
        )
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val c = Calendar.getInstance()
        c.set(year, month, day)
        returnValue(c, viewId)
    }

    companion object {
        fun newInstance(
            calendar: Calendar?,
            viewId: Int,
            returnValue: (Calendar, Int) -> Unit
        ): DatePickerFragment {
            return DatePickerFragment().apply {
                this.currentValueCalendar = calendar
                this.viewId = viewId
                this.returnValue = returnValue
            }
        }
    }
}