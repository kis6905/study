(function() {
  const movieList = window.movieList;
  const contentElem = document.querySelector('.contents');
  
  function init() {
    const playingMovieElem = document.getElementById('playingMovieList');
    const releasedMovieElem = document.getElementById('toBeReleaseMovieList');

    const palayingMovies = movieList.filter(movieItem => movieItem.status === '1');
    const releasedMovies = movieList.filter(movieItem => movieItem.status === '2');

    renderMovie(palayingMovies, playingMovieElem);
    renderMovie(releasedMovies, releasedMovieElem);
    bindEvent();
  }

  function bindEvent() {
    contentElem.addEventListener('click', handleClick);
  }

  function handleClick(e) {
    const target = e.target;
    const targetId = parseInt(target.dataset.id);
    
    if (target.className !== 'movie-card') {
      return
    }

    renderDetailPanel(targetId);
  }

  function renderMovie(movieItems, elem) {
    let htmlStr = '';

    movieItems.forEach(movieItem =>
      htmlStr += `
        <div class="movie-card" data-id="${movieItem.movieId}">
          <span>${movieItem.title}</span>
        </div> 
      `
    )

    elem.innerHTML = htmlStr;
  }

  function renderDetailPanel(currentId) {
    const movieDetailPanelElem = document.getElementById('movieDetail');
    movieDetailPanelElem.style.display = '';

    let htmlStr = '';
    const currentMovie = movieList.find(movieItem => movieItem.movieId === currentId);

    htmlStr += `
      <div>제목: ${currentMovie.title}</div>
      <div>개봉: ${currentMovie.releaseDate}</div>
      <div>등급: ${currentMovie.grade}</div>
      <div>국가: ${currentMovie.nation}</div>
      <div>장르: ${currentMovie.genre}</div>
      <div>배급: ${currentMovie.distributor}</div>
    `
    
    movieDetailPanelElem.innerHTML = htmlStr;
  }

  init();

})();