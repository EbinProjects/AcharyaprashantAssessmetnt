import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.assessment_pvdt_bangalore.R
import com.example.assessment_pvdt_bangalore.viewmodel.PvavdtViewModel
import com.google.accompanist.coil.CoilImage


@Composable
fun LaunchView(modifier: Modifier = Modifier, viemodel: PvavdtViewModel){
    Column(modifier = modifier.fillMaxSize()) {
     EntryLists(viemodel)
    }
}


@Composable
fun Entries(modifier: Modifier= Modifier,
            entries: MediaCoverage){

    Box(
        modifier = modifier
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .background(
                Color.White
            ),
             contentAlignment = Alignment.Center){
    AsyncImage(
        model = entries.coverageURL,
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.we),
        placeholder = painterResource(R.drawable.error),
        modifier = modifier
            .size(120.dp)
            .align(Alignment.TopCenter)
    )
         }
}


@Composable
fun EntryLists(viewModel: PvavdtViewModel) {
    val context = LocalContext.current
    val list by remember { viewModel.pvadtLists }
    val isLoading by remember { viewModel.isLoading }
    val isError by remember { viewModel.loadError }
    val endReached by remember { viewModel.reachEnded }

    if (isLoading){
        Toast.makeText(context,"Loading.....",Toast.LENGTH_SHORT).show()
    }
    if (isError.isNotEmpty()){
        Toast.makeText(context,isError,Toast.LENGTH_SHORT).show()
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3) ,
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        val itemCount = list.size
        items(itemCount){
            if (it>= itemCount - 1 && !endReached){
                viewModel.loadApiContents()
            }
            EntriesRow(rouIndex =it , entries = list )
        }
    }

}

@Composable
fun EntriesRow(modifier: Modifier = Modifier,
               rouIndex : Int,
               entries : List<MediaCoverage>){
    Column {
        Row {
            Entries(entries = entries[rouIndex])
            Spacer(modifier = modifier.height(12.dp))
        }
    }

}