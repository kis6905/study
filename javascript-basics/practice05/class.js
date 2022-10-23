App.movieApp = class movieApp {
  constructor(contents) {
    this.movieList = window.movieList;

    this.contentElem = contents;
    console.log(this.movieList, this.container);
  
    this.init();
   
  }

  init() {
    this.playingMovieElem = document.getElementById('playingMovieList');
    this.releasedMovieElem = document.getElementById('toBeReleaseMovieList');
    this.palayingMovies = movieList.filter(movieItem => movieItem.status === '1');
    this.releasedMovies = movieList.filter(movieItem => movieItem.status === '2');

    this.renderMovie(this.palayingMovies, this.playingMovieElem);
    this.renderMovie(this.releasedMovies, this.releasedMovieElem);

    this.bindEvent();
  }

  bindEvent() {
    this.contentElem.addEventListener('click', this.handleClick.bind(this));
  }

  handleClick(e) {
    const target = e.target;
    const targetId = parseInt(target.dataset.id);
    
    if (target.className !== 'movie-card') {
      return
    }

    this.renderDetailPanel(targetId);
  }

  renderMovie(movieItems, elem) {
    this.htmlStr = '';

    movieItems.forEach(movieItem =>
      this.htmlStr += `
        <div class="movie-card" data-id="${movieItem.movieId}">
          <span>${movieItem.title}</span>
        </div> 
      `
    )

    elem.innerHTML = this.htmlStr;
  }

  renderDetailPanel(currentId) {
    this.movieDetailPanelElem = document.getElementById('movieDetail');
    this.movieDetailPanelElem.style.display = '';

    this.htmlStr = '';
    this.currentMovie = this.movieList.find(movieItem => movieItem.movieId === currentId);

    this.htmlStr += `
      <div>제목: ${this.currentMovie.title}</div>
      <div>개봉: ${this.currentMovie.releaseDate}</div>
      <div>등급: ${this.currentMovie.grade}</div>
      <div>국가: ${this.currentMovie.nation}</div>
      <div>장르: ${this.currentMovie.genre}</div>
      <div>배급: ${this.currentMovie.distributor}</div>
    `
    
    this.movieDetailPanelElem.innerHTML = this.htmlStr;
  }
}
