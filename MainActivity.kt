import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tugas5.databinding.ActivityMainBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var dataList: MutableList<MyData> = mutableListOf()
    private lateinit var editTextId: EditText
    private lateinit var editTextDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

       
        adapter = MyAdapter(dataList)
        recyclerView.adapter = adapter

        
        editTextId = findViewById(R.id.editTextId)
        editTextDescription = findViewById(R.id.editTextDescription)

        
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        buttonAdd.setOnClickListener {
            addData()
        }
    }

    
    private fun addData() {
        
        val idInput = editTextId.text.toString()
        val descriptionInput = editTextDescription.text.toString()

        
        if (idInput.isEmpty() || descriptionInput.isEmpty()) {
            Toast.makeText(this, "Please enter both ID and Description", Toast.LENGTH_SHORT).show()
            return
        }

        
        val newItem = MyData(idInput.toInt(), descriptionInput)

        
        dataList.add(newItem)
        adapter.notifyItemInserted(dataList.size - 1)

        
        recyclerView.scrollToPosition(dataList.size - 1)

        
        editTextId.text.clear()
        editTextDescription.text.clear()
    }
}
