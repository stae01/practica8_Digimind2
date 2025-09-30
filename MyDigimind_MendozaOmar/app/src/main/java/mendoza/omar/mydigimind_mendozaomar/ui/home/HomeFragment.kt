package mendoza.omar.mydigimind_mendozaomar.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import mendoza.omar.mydigimind_mendozaomar.Carrito
import mendoza.omar.mydigimind_mendozaomar.R
import mendoza.omar.mydigimind_mendozaomar.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private lateinit var homeViewModel: HomeViewModel

    companion object {
        val carrito: Carrito = Carrito()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, { })

        var gridview: GridView = root.findViewById<GridView>(R.id.gridView)

        var adaptador: RecordatorioAdapter = RecordatorioAdapter(container!!.context, carrito)
        gridview.adapter = adaptador

        return root
    }
}

class RecordatorioAdapter: BaseAdapter {
    var carrito = Carrito()
    var context: Context? = null

    constructor(context: Context, carrito: Carrito) {
        this.context = context
        this.carrito = carrito
    }

    override fun getCount(): Int {
        return carrito.recordatorios.size
    }

    override fun getItem(position: Int): Any {
        return carrito.recordatorios[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var recordatorio = carrito.recordatorios[position]
        var inflator =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.recordatorio, null)

        val tvNombreRecordatorio = vista.findViewById<TextView>(R.id.tvNombreRecordatorio)
        val tvDiasRecordatorio = vista.findViewById<TextView>(R.id.tvDiasRecordatorio)
        val tvTiempoRecordatorio = vista.findViewById<TextView>(R.id.tvTiempoRecordatorio)

        tvNombreRecordatorio.setText(recordatorio.nombre)
        tvDiasRecordatorio.setText(recordatorio.dias)
        tvTiempoRecordatorio.setText(recordatorio.tiempo)

        return vista
    }
}