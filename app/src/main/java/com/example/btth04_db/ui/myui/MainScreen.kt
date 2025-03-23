package com.example.btth04_db.ui.myui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.btth04_db.db.appdatabase.AppDatabase
import com.example.btth04_db.db.entities.Person
import com.example.btth04_db.db.repostories.PersonRepo
import com.example.btth04_db.db.viewmodel.PersonViewModel

@Composable
fun MainScreen() {
    var selectedPerson by remember { mutableStateOf<Person?>(null) }
    var showList by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // 🔹 Khởi tạo Repository và ViewModel
    val personRepo by lazy {
        val db = AppDatabase.getDatabase(context)
        PersonRepo(db.PersonDao())
    }
    val personViewModel = viewModel { PersonViewModel(personRepo) }
    val personsFlow = personViewModel.getAll

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center // 🔹 Căn giữa toàn bộ nội dung
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // 🔹 Giữ khoảng cách giữa các phần tử
        ) {
            // 🔹 Giao diện nhập liệu & nút Show All
            Input(
                selectedPerson = selectedPerson,
                personViewModel = personViewModel,
                onShowList = { showList = !showList },
                onPersonCleared = { selectedPerson = null },
                showList
            )
    
            // 🔹 Hiển thị danh sách nếu `showList` = true
            if (showList) {
                ListInfo(
                    selectedPerson = selectedPerson,
                    personsFlow = personsFlow,
                    onPersonSelected = { person -> selectedPerson = person },
                    onDeleteSelected = {
                        selectedPerson?.let { personViewModel.delete(it.id)
                        selectedPerson = null
                        }

                    }
                )
            }
        }
    }
}

