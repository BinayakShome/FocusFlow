package com.example.focusflow.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusflow.data.NetworkUtils
import com.example.focusflow.data.Subject
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _showNoInternet = MutableStateFlow(false)
    val showNoInternet: StateFlow<Boolean> = _showNoInternet

    private val _subjects = MutableStateFlow<List<Subject>>(emptyList())
    val subjects: StateFlow<List<Subject>> = _subjects

    fun checkInternetAvailability(context: Context) {
        viewModelScope.launch {
            _showNoInternet.value = !NetworkUtils.isInternetAvailable(context)
        }
    }

    fun addSubject(uid: String, subjectName: String) {
        Log.d("HomeViewModel", "addSubject called with uid=$uid and subjectName=$subjectName")

        val ref = FirebaseDatabase.getInstance()
            .getReference("users")
            .child(uid)
            .child(subjectName)

        val subjectData = mapOf("created_at" to System.currentTimeMillis())

        ref.setValue(subjectData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("HomeViewModel", "Subject '$subjectName' successfully added.")
                fetchSubjects(uid)
            } else {
                Log.e("HomeViewModel", "Failed to add subject: ${task.exception}")
            }
        }
    }

    fun fetchSubjects(uid: String) {
        Log.d("HomeViewModel", "fetchSubjects called with uid=$uid")

        val ref = FirebaseDatabase.getInstance().getReference("users").child(uid)
        ref.get().addOnSuccessListener { snapshot ->
            val subjectList = mutableListOf<Subject>()
            Log.d("HomeViewModel", "Data snapshot received: ${snapshot.childrenCount} children")
            for (child in snapshot.children) {
                val name = child.key ?: continue
                if (name == "email" || name == "name" || name == "created_at") continue

                val createdAt = child.child("created_at").getValue(Long::class.java) ?: 0L
                Log.d("HomeViewModel", "Subject found: $name created at $createdAt")
                subjectList.add(Subject(name = name, createdAt = createdAt))
            }
            _subjects.value = subjectList.sortedByDescending { it.createdAt }
            Log.d("HomeViewModel", "Subjects updated: ${_subjects.value}")
        }.addOnFailureListener { exception ->
            Log.e("HomeViewModel", "Failed to fetch subjects: ${exception.message}", exception)
        }
    }
}