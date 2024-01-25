package kr.ac.kumoh.ce.prof01.jet15card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.ac.kumoh.ce.prof01.jet15card.ui.theme.Jet15CardTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SongViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.add(Song("사랑은 늘 도망가", "임영웅"))
        viewModel.add(Song("소주 한 잔", "임창정"))
        viewModel.add(Song("화장을 고치고", "왁스"))
        viewModel.add(Song("멀어져 간 사람아", "박상민"))

        setContent {
            Jet15CardTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
//        Column {
//            SongItem()
//            SongItem()
//        }
        SongList()
    }
}

@Composable
fun SongList() {
    val viewModel: SongViewModel = viewModel()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 4.dp),
    ) {
        items(viewModel.songs) {song ->
            SongItem(song)
        }
    }
}

@Composable
fun SongItem(song: Song) {
    Card(
        Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            Modifier.height(IntrinsicSize.Min)
        ) {
            Box(
                Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "노래 앨범 이미지",
                    modifier = Modifier.size(100.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "노래 앨범 이미지",
                    modifier = Modifier.size(100.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
            Column(
                Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                SongTitle(song.title)
                SingerName(song.singer)
            }
        }
    }
}

@Composable
fun SongTitle(title: String) {
    Text(
        text = title,
        fontSize = 25.sp,
        lineHeight = 30.sp,
    )
}

@Composable
fun SingerName(name: String) {
    Text(name, fontSize = 20.sp)
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Jet15CardTheme {
        MainScreen()
    }
}