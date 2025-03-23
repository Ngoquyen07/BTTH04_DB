    package com.example.btth04_db.db.viewmodel

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.btth04_db.db.entities.Person
    import com.example.btth04_db.db.repostories.PersonRepo
    import kotlinx.coroutines.launch

    class PersonViewModel(private val personRepo: PersonRepo) : ViewModel()  {
        val getAll = personRepo.getAll()

        fun upsert(person: Person) {
            viewModelScope.launch {
                personRepo.upsert(person)
            }
        }

        fun delete(id: Int) {
            viewModelScope.launch {
                personRepo.delete(id)
            }
        }
    }