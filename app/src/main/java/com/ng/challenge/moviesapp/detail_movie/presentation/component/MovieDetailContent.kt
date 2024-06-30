package com.ng.challenge.moviesapp.detail_movie.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.ng.challenge.moviesapp.R
import com.ng.challenge.moviesapp.core.domain.model.Movie
import com.ng.challenge.moviesapp.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieDetailContent(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?,
    pagingMoviesSimilar: LazyPagingItems<Movie>,
    isLoading: Boolean,
    isError: String,
    navigateToDetailMovie: (Int) -> Unit
) {
    if (movieDetails == null) {
        MovieDetailEmpty()
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MovieDetailBackdropImage(
                    backdropImageUrl = movieDetails?.backdropPathUrl.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movieDetails?.title.toString(),
                    color = MaterialTheme.colors.primary,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                FlowRow(
                    mainAxisSpacing = 10.dp,
                    mainAxisAlignment = MainAxisAlignment.Center,
                    crossAxisSpacing = 10.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    movieDetails?.genres?.forEach { genre ->
                        MovieDetailGenreTag(genre = genre)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                MovieInfoContent(
                    movieDetails = movieDetails,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                MovieDetailRating(
                    rating = (movieDetails?.voteAvg?.toFloat()?.div(2f) ?: 0f),
                    modifier = Modifier.height(15.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                MovieDetailOverview(
                    overview = movieDetails?.overview.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.similar),
                    color = MaterialTheme.colors.primary,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(horizontal = 8.dp)
                )
                MovieDetailSimilarMovies(
                    pagingMoviesSimilar = pagingMoviesSimilar,
                    modifier = Modifier
                        .fillMaxWidth(),
                    navigateToDetailMovie = navigateToDetailMovie
                )
            }
            if (isError.isNotEmpty()) {
                Text(
                    text = isError,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .align(Alignment.TopCenter)
                )
            }
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsContentPreview() {
    MovieDetailContent(
        movieDetails = MovieDetails(
            id = 1,
            title = "Homem Aranha",
            genres = listOf("Ação", "Aventura", "Aventura", "Aventura", "Aventura", "Aventura"),
            overview = "Depois do nosso herói ter sido desmascarado pelo seu inimigo Mysterio, Peter Parker já não é capaz de separar a sua vida normal do seu estatuto de super-herói, pelo que só lhe resta pedir ajuda ao Mestre das Artes Místicas, o Doutor Estranho, para que apague a sua real identidade da cabeça de todos. No entanto, esse feitiço leva-o a uma realidade que não é a sua e onde terá de enfrentar novos inimigos, ainda mais perigosos, forçando-o a descobrir o que realmente significa ser o Homem-Aranha.",
            backdropPathUrl = "",
            releaseDate = "25/05/2022",
            voteAvg = 2.7
        ),
        pagingMoviesSimilar = flowOf<PagingData<Movie>>(PagingData.from(emptyList())).collectAsLazyPagingItems(),
        isError = "Error",
        isLoading = false,
    ){}
}