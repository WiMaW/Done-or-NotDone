package com.wioletamwrobel.doneornotdone.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.wioletamwrobel.doneornotdone.data.DoneOrNotDoneDatabase
import com.wioletamwrobel.doneornotdone.data.ToDo
import com.wioletamwrobel.doneornotdone.data.ToDoDao
import com.wioletamwrobel.doneornotdone.databinding.ActivityMainBinding
import com.wioletamwrobel.doneornotdone.databinding.AlertDialogAddToDoBinding
import com.wioletamwrobel.doneornotdone.ui.todo.ToDoFragment
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DoneOrNotDoneDatabase
    private val toDoDao: ToDoDao by lazy {
        database.getToDoDao()
    }
    private val toDoFragment: ToDoFragment = ToDoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = PagerAdapter(this)
        setTabLayout()
        binding.fab.setOnClickListener { fabOnClickListener() }

        database = DoneOrNotDoneDatabase.getDatabase(this)

    }

    private fun fabOnClickListener() {
        val dialogBinding = AlertDialogAddToDoBinding.inflate(layoutInflater)

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(dialogBinding.root)

        dialogBinding.buttonShowDetails.setOnClickListener {
            dialogBinding.textLayoutAddDetails.visibility =
                if (dialogBinding.textLayoutAddDetails.isVisible) View.GONE else View.VISIBLE
        }

        dialogBinding.buttonSave. setOnClickListener{
            val newToDo: ToDo = ToDo(
                title = dialogBinding.textInputAddTodo.text.toString(),
                description = dialogBinding.textInputDetails.text.toString())
            thread {
                toDoDao.createToDo(newToDo)
            }
            dialog.dismiss()
            toDoFragment.fetchAllTasks()
        }
        dialog.show()
    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = "To Do"
        }.attach()
    }

    private inner class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 1

        override fun createFragment(position: Int): Fragment {
            return toDoFragment
        }
    }
}