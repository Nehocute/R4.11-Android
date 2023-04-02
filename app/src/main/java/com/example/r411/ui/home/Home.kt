package com.example.r411.ui.home

import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.lifecycleScope
import com.example.r411.*
import com.example.r411.databinding.FragmentHomeBinding
import com.example.r411.databinding.FragmentLoginBinding
import com.example.r411.persistance.database.AppDatabase
import com.example.r411.persistance.entity.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class Home : Fragment() {

    companion object {
        fun newInstance() = Home()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.syncDatabase(this.requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        println("a")
        val list = v.findViewById<ListView>(R.id.lv_session)
        val database = AppDatabase.getInstance(this.requireContext())
        val items = mutableListOf<String>()
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,items)

        lifecycleScope.launch {
            val sessions = withContext(Dispatchers.IO) {
                database.sessionDao().getAll()
            }
            sessions.forEach {
                val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a")
                val dateTime = OffsetDateTime.parse(it.date)
                items.add(dateTime.format(formatter))
            }
            adapter.notifyDataSetChanged()
        }

        list.adapter = adapter

        list.setOnItemClickListener { parent, view, position, id ->
            val selectedSession = parent.getItemAtPosition(position) as String
            // intent or something else ?
            //val intent = Intent(this.context, FormationListActivity::class.java)
            //startActivity(intent)
        }
        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.go_to_formation_list -> {
            val intent = Intent(this.context, FormationListActivity::class.java)
            startActivity(intent)
            true
        }

        R.id.go_to_profile -> {
            val intent = Intent(this.context, ProfileActivity::class.java)
            intent.putExtra("user_id", 1)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}