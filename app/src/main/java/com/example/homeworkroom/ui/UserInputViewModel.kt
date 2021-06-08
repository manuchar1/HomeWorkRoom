package com.example.homeworkroom.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkroom.data.User
import com.example.homeworkroom.data.UserDao
import com.example.homeworkroom.data.UserDataBase
import com.example.homeworkroom.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserInputViewModel(application: Application) : AndroidViewModel(application) {

    private val readData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        repository = UserRepository(userDao)
        readData = repository.readData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}