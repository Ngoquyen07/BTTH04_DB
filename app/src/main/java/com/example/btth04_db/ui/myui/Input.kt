package com.example.btth04_db.ui.myui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewModelScope
import com.example.btth04_db.db.entities.Person
import com.example.btth04_db.db.viewmodel.PersonViewModel
import kotlinx.coroutines.launch
@Composable
fun Input(
    selectedPerson: Person?,
    personViewModel: PersonViewModel,
    onShowList: () -> Unit,
    onPersonCleared: () -> Unit, // 🆕 Callback để đặt `selectedPerson = null`
    showList: Boolean
) {
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    LaunchedEffect(selectedPerson) {
        name = selectedPerson?.name ?: ""
        phoneNumber = selectedPerson?.phoneNumber ?: ""
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") }
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Enter your phone number") }
        )
        Button(
            onClick = {
                onUpsert(personViewModel, selectedPerson, name, phoneNumber)
                name = ""
                phoneNumber = ""
                onPersonCleared() // ✅ Gọi callback để đặt `selectedPerson = null`
            }
        ) {
            Text(text = if (selectedPerson == null) "Add" else "Update")
        }
        Button(
            onClick =  onShowList
        ) {
            Text(text = if (showList) "Hide" else "Show")
        }
    }
}


fun onUpsert(personViewModel: PersonViewModel, selectedPerson: Person?, name: String, phoneNumber: String) {
    if(name.isEmpty() || phoneNumber.isEmpty()) return
    personViewModel.viewModelScope.launch {
        val person = selectedPerson?.apply {
            this.name = name
            this.phoneNumber = phoneNumber
        } ?: Person(name = name, phoneNumber = phoneNumber)
        personViewModel.upsert(person)
    }
}




