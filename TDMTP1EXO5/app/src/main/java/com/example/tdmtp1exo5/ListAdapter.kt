import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tdmtp1exo5.Contact
import com.example.tdmtp1exo5.R

class ListAdapter(private val contacts: ArrayList<Contact>):
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.contact_data, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var contact:Contact = contacts[position]
        holder.name.text = contact.name
        holder.phone.text = contact.phone
    }
    class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        var name:TextView = item.findViewById(R.id.name)
        var phone:TextView = item.findViewById(R.id.phone)
    }
}