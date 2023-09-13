package rs.alexleru.registrationcertificate.presentation.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class WarningDialogFragment(private val onDialogClick: (Int) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val alertDialog: AlertDialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("You have made changes. Do you want to discard or save them?") //TODO to string res
                setPositiveButton("Save") { _, which -> //TODO to string res
                    onDialogClick(which)
                }
                setNegativeButton("Discard") { _, which -> //TODO to string res
                    onDialogClick(which)
                }
                setNeutralButton("Cancel") { dialog, _ -> //TODO to string res
                    dialog.cancel()
                }
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
        return alertDialog
    }

    companion object {

        fun newInstance(onDialogClick: (Int) -> Unit) = WarningDialogFragment(onDialogClick)
    }
}