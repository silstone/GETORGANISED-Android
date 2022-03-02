package com.getorganized.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.getorganized.model_classes.TaskList
import java.util.*

class Constant {

    var swiped: Boolean = false


    val USERNAME = "USERNAME"
    val USERS = "USERS"
    val USER_EMAIL = "USER_EMAIL"

    val LIFE_TASK = "LIFE_TASK"
    val USER_LOGIN = "USER_LOGIN"
    val TASKS = "TASKS"
    val SUBTASKS = "SUBTASKS"



    val Unscheduled = "Unscheduled"
    val Scheduled = "Scheduled"
    val completed = "Completed"
    val list_name = "list_name"
    val start_time = "start_time"
    val end_time = "end_time"
    val date = "date"
    val color = "color"
    val task_status = "task_status"
    val title = "title"
    val inbox = "inbox"
    val node_id = "node_id"



    // lateinit var USER_EMAIL :String

    var task_list: List<TaskList?> = ArrayList<TaskList?>()




}