package com.kennysexton.fetch.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kennysexton.fetch.FetchViewModel
import com.kennysexton.fetch.modal.FetchData

@Composable
fun ListRetrieval(modifier: Modifier, vm: FetchViewModel) {

    val dataList by vm.dataList.collectAsState()

    LazyColumn(modifier = modifier) {
        items(dataList) { item ->
            ListRow(item)

        }

    }
}


@Composable
fun ListRow(item: FetchData) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        item.name?.let { Text(it) }
        Text(item.listId.toString())
        Text(item.id.toString())
    }

}