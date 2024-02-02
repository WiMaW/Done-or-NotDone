package com.wioletamwrobel.doneornotdone.ui.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wioletamwrobel.doneornotdone.data.DoneOrNotDoneDatabase
import com.wioletamwrobel.doneornotdone.data.ToDoDao
import com.wioletamwrobel.doneornotdone.databinding.FragmentToDoBinding
import kotlin.concurrent.thread

class ToDoFragment: Fragment() {

    private lateinit var fragmentBinding: FragmentToDoBinding

    //how to not duplicate it - dependency injection
    private val toDoDao: ToDoDao by lazy {
        DoneOrNotDoneDatabase.getDatabase(requireContext()).getToDoDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentToDoBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchAllTasks()
    }

    fun fetchAllTasks() {
        thread {
            val toDos = toDoDao.getAllToDo()

            requireActivity().runOnUiThread {
                fragmentBinding.recyclerView.adapter = ToDoAdapter(toDo = toDos)
            }
        }
    }
}