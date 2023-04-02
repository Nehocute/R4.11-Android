package com.example.r411.ui.home

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.r411.*
import com.example.r411.databinding.FragmentHomeBinding
import com.example.r411.databinding.FragmentLoginBinding

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
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val list = v.findViewById<ListView>(R.id.lv_session)
        //TOOD: Get data from database
        val data = listOf("Session 1", "Session 2", "Session 3")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, data)
        list.adapter = adapter

        list.setOnItemClickListener { parent, view, position, id ->
            val selectedSession = parent.getItemAtPosition(position) as String
            // intent or something else ?
            val intent = Intent(this.context, FormationListActivity::class.java)
            startActivity(intent)
        }
        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.go_to_home -> {
            val intent = Intent(this.context, HomeActivity::class.java)
            startActivity(intent)
            true
        }

        R.id.go_to_formation_list -> {
            val intent = Intent(this.context, FormationListActivity::class.java)
            startActivity(intent)
            true
        }

        R.id.go_to_profile -> {
            val intent = Intent(this.context, ProfileActivity::class.java)
            startActivity(intent)
            true
        }

        R.id.go_to_students -> {
            val intent = Intent(this.context, StudentsActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}