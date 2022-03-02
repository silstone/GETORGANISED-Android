package com.getorganized.model_classes

class SubTask {

    private var list_name = ""
    private var start_time = ""
    private var end_time = ""
    private var date = ""
    private var title = ""
    private var task_status = ""
    private var node_id = ""
    private var color = ""

    fun getList_name(): String? {
        return list_name
    }

    fun setList_name(list_name: String) {
        this.list_name = list_name
    }

    fun getStart_time(): String? {
        return start_time
    }

    fun setStart_time(start_time: String) {
        this.start_time = start_time
    }

    fun getEnd_time(): String? {
        return end_time
    }

    fun setEnd_time(end_time: String) {
        this.end_time = end_time
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getTask_status(): String? {
        return task_status
    }

    fun setTask_status(task_status: String) {
        this.task_status = task_status
    }

    fun getNodeid(): String? {
        return node_id
    }

    fun setNodeid(id: String) {
        this.node_id = id
    }

    fun getColor(): String? {
        return color
    }

    fun setColor(color: String) {
        this.color = color
    }


}