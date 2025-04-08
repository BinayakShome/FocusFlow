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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

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
        _isLoading.value = true
        val ref = FirebaseDatabase.getInstance().getReference("users").child(uid)
        ref.get().addOnSuccessListener { snapshot ->
            val subjectList = mutableListOf<Subject>()
            for (child in snapshot.children) {
                val name = child.key ?: continue
                if (name == "email" || name == "name" || name == "created_at") continue
                val createdAt = child.child("created_at").getValue(Long::class.java) ?: 0L
                subjectList.add(Subject(name = name, createdAt = createdAt))
            }
            _subjects.value = subjectList.sortedByDescending { it.createdAt }
            _isLoading.value = false
        }.addOnFailureListener {
            Log.e("HomeViewModel", "Failed to fetch subjects", it)
            _isLoading.value = false
        }
    }

    fun deleteSubject(uid: String, subjectName: String) {
        Log.d("HomeViewModel", "deleteSubject called with uid=$uid and subjectName=$subjectName")

        val ref = FirebaseDatabase.getInstance()
            .getReference("users")
            .child(uid)
            .child(subjectName)

        ref.removeValue().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("HomeViewModel", "Subject '$subjectName' successfully deleted.")
                // Refresh the subject list
                fetchSubjects(uid)
            } else {
                Log.e("HomeViewModel", "Failed to delete subject: ${task.exception}")
            }
        }
    }
}
