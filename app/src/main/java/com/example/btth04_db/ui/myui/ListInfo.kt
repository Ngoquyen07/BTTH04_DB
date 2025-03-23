package com.example.btth04_db.ui.myui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.btth04_db.db.entities.Person
import com.example.btth04_db.db.viewmodel.PersonViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun ListInfo(
    selectedPerson: Person?,
    personsFlow: Flow<List<Person>>?,
    onPersonSelected: (Person) -> Unit, // ✅ Callback khi chọn một item
    onDeleteSelected: (Person) -> Unit?
) {
    val persons = personsFlow?.collectAsState(initial = emptyList())?.value ?: emptyList()

    LazyColumn {
        items(persons) { person ->
            PersonCard(person = person, isSelected = person == selectedPerson, onClick = {
                onPersonSelected(person) // ✅ Khi nhấn, cập nhật `selectedPerson`
            } , onDeleteSelected = onDeleteSelected , onPersonSelected = onPersonSelected , selectedPerson = selectedPerson)
        }
    }
}
@Composable
fun PersonCard(person: Person, isSelected: Boolean, onClick: () -> Unit ,  onDeleteSelected: (Person) -> Unit? , onPersonSelected: (Person) -> Unit? , selectedPerson: Person?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }, // ✅ Nhấn vào item
        shape = RoundedCornerShape(12.dp), // ✅ Bo góc đẹp hơn
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFE3F2FD) else Color.White // ✅ Đổi màu khi được chọn
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // ✅ Căn giữa và đẩy nút xóa sang phải
        ) {
            Column(
                modifier = Modifier.weight(1f) // ✅ Để cột thông tin mở rộng, tránh bị đẩy vào giữa
            ) {
                Text(text = "Name: ${person.name}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Phone: ${person.phoneNumber}", style = MaterialTheme.typography.bodyMedium)
            }

            IconButton(
                onClick = {
                    onPersonSelected(person) // ✅ Khi nhấn, cập nhật `selectedPerson`
                    onDeleteSelected(person)
                          },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red
                )
            }
        }
    }
}