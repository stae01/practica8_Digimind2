package mendoza.omar.mydigimind_mendozaomar.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mendoza.omar.mydigimind_mendozaomar.R
import mendoza.omar.mydigimind_mendozaomar.Recordatorio
import mendoza.omar.mydigimind_mendozaomar.ui.home.HomeFragment.Companion.carrito

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, { })

        val btnSetTime: Button = root.findViewById(R.id.btnRegister)
        val tvMensaje: TextView = root.findViewById(R.id.mensaje)
        val btnHora: Button = root.findViewById(R.id.btnTime)
        val tvTiempo: TextView = root.findViewById(R.id.txtTiempo)

        btnHora.setOnClickListener { v ->

            val timePickerDialog = TimePickerDialog(context, { timePicker, hourOfDay, minutes ->
                tvTiempo.setText(hourOfDay.toString().padStart(2, '0') + ":" + minutes.toString().padStart(2, '0'))
            }, 0, 0, false)

            timePickerDialog.setOnShowListener { dialog ->
                val positiveButton =
                    (dialog as TimePickerDialog).getButton(TimePickerDialog.BUTTON_POSITIVE)


                positiveButton.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.black
                    )
                )

                positiveButton.setText("Aceptar")



            }
                timePickerDialog.show()
        }

        val chbMonday: CheckBox = root.findViewById(R.id.chbMonday)
        val chbTuesday: CheckBox = root.findViewById(R.id.chbTuesday)
        val chbWednesday: CheckBox = root.findViewById(R.id.chbWednesday)
        val chbThursday: CheckBox = root.findViewById(R.id.chbThursday)
        val chbFriday: CheckBox = root.findViewById(R.id.chbFriday)
        val chbSaturday: CheckBox = root.findViewById(R.id.chbSaturday)
        val chbSunday: CheckBox = root.findViewById(R.id.chbSunday)

        btnSetTime.setOnClickListener {
            if (!tvMensaje.text.isNullOrEmpty() && !tvTiempo.text.isNullOrEmpty() &&
                (chbMonday.isChecked || chbTuesday.isChecked || chbWednesday.isChecked ||
                        chbThursday.isChecked || chbFriday.isChecked || chbSaturday.isChecked || chbSunday.isChecked)) {

                var dias = ""

                // Si todos los días están seleccionados, asignar "Everyday"
                if (chbMonday.isChecked && chbTuesday.isChecked && chbWednesday.isChecked &&
                    chbThursday.isChecked && chbFriday.isChecked && chbSaturday.isChecked &&
                    chbSunday.isChecked) {
                    dias = "Everyday"
                } else {
                    // Si no, agregar los días seleccionados
                    if (chbMonday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Monday" else ", Monday"
                    }

                    if (chbTuesday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Tuesday" else ", Tuesday"
                    }

                    if (chbWednesday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Wednesday" else ", Wednesday"
                    }

                    if (chbThursday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Thursday" else ", Thursday"
                    }

                    if (chbFriday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Friday" else ", Friday"
                    }

                    if (chbSaturday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Saturday" else ", Saturday"
                    }

                    if (chbSunday.isChecked) {
                        dias += if (dias.isNullOrEmpty()) "Sunday" else ", Sunday"
                    }
                }

                // Crear el recordatorio
                val recordatorio: Recordatorio = Recordatorio(dias, tvTiempo.text.toString(), tvMensaje.text.toString())

                carrito.agregar(recordatorio)

                // Limpiar campos
                tvMensaje.setText("")
                tvTiempo.setText("")

                // Desmarcar los días
                chbMonday.setChecked(false)
                chbTuesday.setChecked(false)
                chbWednesday.setChecked(false)
                chbThursday.setChecked(false)
                chbFriday.setChecked(false)
                chbSaturday.setChecked(false)
                chbSunday.setChecked(false)

                // Mostrar mensaje
                Toast.makeText(context, "Registered", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(context, "One of the fields is missing: write the reminder, select day(s) and set time", Toast.LENGTH_LONG).show()
            }

        }

        return root
    }

}