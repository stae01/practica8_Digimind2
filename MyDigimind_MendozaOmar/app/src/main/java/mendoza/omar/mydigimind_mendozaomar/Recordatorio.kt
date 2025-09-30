package mendoza.omar.mydigimind_mendozaomar
import java.io.Serializable

data class Recordatorio(var dias: String,
                         var tiempo: String,
                         var nombre: String) : Serializable