(function() {
  const storeList = window.storeList;
  // TODO

  const storeListEl = document.querySelector('.store-list-view'); 
  const menuViewEl = document.getElementById('menuView');
  const menuListEl = document.getElementById('menuList');
  const reviewViewEl = document.getElementById('reviewView');
  const reviewListEl = document.getElementById('reviewList');
  const menuTitleEl = document.getElementById('menuTitle');
  const reviewTitleEl = document.getElementById('reviewTitle');
  
  let initStoreIndex = 0;

  function init() {
    renderStore();
    bindEvent();
  }

  function bindEvent() {
    storeListEl.addEventListener('click', handleClick);
  }

  function handleClick(e) {
    const target = e.target;

    if (target.className === 'menu-btn') {
      const store = storeList.find(store => store.storeId === target.value);
      renderMenu(store);
    } else if (target.className === 'review-btn') {
      const store = storeList.find(store => store.storeId === target.value);
      renderReviews(store);
    } else {
      return
    }
  }

  function renderReviews(store) {
    let htmlStr = '';

    for (let review of store.reviews) {
      htmlStr += `
        <div class="display-flex pa-2">
          <div class="flex-5">${review.contents}</div>
          <div class="flex-3 ml-2">${review.userId}</div>
          <div class="flex-2 ml-2">${review.stars}</div>
        </div>
      `
    }
    reviewListEl.innerHTML = htmlStr;

    renderDetailTitle(store.name);
    showEl(reviewViewEl);
    hideEl(menuViewEl);
  }

  function renderMenu(store) {
    let htmlStr = '';
    
    for (let menu of store.menus) {
      htmlStr += `
        <div class="display-flex pa-2">
          <div class="flex-7">${menu.name}</div>
          <div class="flex-3 ml-2">${menu.price}</div>
        </div>
      `;
    }
    menuListEl.innerHTML = htmlStr;

    renderDetailTitle(store.name);
    showEl(menuViewEl);
    hideEl(reviewViewEl);
  }

  function renderDetailTitle(title) {
    reviewTitleEl.innerHTML = title;
    menuTitleEl.innerHTML = title;
  }

  function showEl(el) {
    el.style.display = '';
  }

  function hideEl(el) {
    el.style.display = 'none';
  }

  function renderStore() {
    let htmlStr = '';
    
    for(let i = 0; i < storeList.length; i++) {
      htmlStr += `
        <div class="mb-5">
          <span>${storeList[i].name}</span>
          <button class="menu-btn" value="${storeList[i].storeId}">메뉴</button>
          <button class="review-btn" value="${storeList[i].storeId}">리뷰</button>
        </div>
      `
    }

    storeListEl.innerHTML = htmlStr;
  }


  init();

})();