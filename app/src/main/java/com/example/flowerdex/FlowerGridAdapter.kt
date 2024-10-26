import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowerdex.R

data class FlowerItem(
    val id: Int,
    val name: String,
    val imageResId: Int
)

class FlowerGridAdapter : ListAdapter<FlowerItem, FlowerGridAdapter.ViewHolder>(FlowerDiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.flowerImage)
        val titleView: TextView = view.findViewById(R.id.flowerTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.flower_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.imageView.setImageResource(item.imageResId)
        holder.titleView.text = item.name
    }
}

// DiffUtil callback
class FlowerDiffCallback : DiffUtil.ItemCallback<FlowerItem>() {
    override fun areItemsTheSame(oldItem: FlowerItem, newItem: FlowerItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FlowerItem, newItem: FlowerItem): Boolean {
        return oldItem == newItem
    }
}