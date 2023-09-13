package rs.alexleru.registrationcertificate.presentation.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import rs.alexleru.registrationcertificate.R
import java.util.Calendar

class DatePickerFragment(
    private val currentValueCalendar: Calendar?,
    private val viewId: Int,
    private val returnValue: (Calendar, Int) -> Unit //int - viewId
) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {


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
            return DatePickerFragment(calendar, viewId, returnValue)
        }
    }
}