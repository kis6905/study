(function() {
  const questionList = [
    {
      id: 1,
      title: '좋아하는 색깔은?',
      answers: [
        { value: '1', text: '빨강' },
        { value: '2', text: '파랑' },
        { value: '3', text: '초록' },
        { value: '4', text: '노랑' },
      ]
    },
    {
      id: 2,
      title: '좋아하는 음식 종류는?',
      answers: [
        { value: '1', text: '한식' },
        { value: '2', text: '중식' },
        { value: '3', text: '일식' },
        { value: '4', text: '양식' },
      ]
    },
    {
      id: 3,
      title: '좋아하는 스포츠는?',
      answers: [
        { value: '1', text: '축구' },
        { value: '2', text: '야구' },
        { value: '3', text: '농구' },
        { value: '4', text: '배구' },
      ]
    }
  ];

  const questionElem = document.getElementById('questionContents');
  let questionListIndex = 0;
  let selectList = [];

  function init() {
    renderHtml(questionListIndex);
    bindEvent();
  }

  function bindEvent() {
    questionElem.addEventListener('click', handleClick);
  }

  let selectedValue;
  function handleClick(e) {
    const target = e.target;

    if (target.tagName === 'INPUT') {
      document.getElementById('nextBtn').disabled = false;
      selectedValue = target.value;
    } else if (target.id === 'nextBtn') {
      // const checkedInput = Array.from(document.querySelectorAll('input')).find(inputEl => inputEl.checked);
      addSelectedAnswer({ id: questionList[questionListIndex].id, value: selectedValue });
      questionListIndex++;
      if (questionListIndex < questionList.length) {
        renderHtml(questionListIndex);
      } else {
        renderSelectedAnswer();
      }
    } else {
      return
    }

  }

  function renderHtml(questionIndex) {
    const question = questionList[questionIndex];
    let htmlStr = `
      <div>
        <span class="question">${question.title}</span>
      </div>
    `;

    for(let i = 0; i < question.answers.length; i++) {
      const answer = question.answers[i];
      htmlStr += `
        <div>
          <input type="radio" name="answer" value="${answer.value}">${answer.text}</input>
        </div>
      `
    }
    htmlStr += `<button id="nextBtn" disabled>다음</button>`
    questionElem.innerHTML = htmlStr;

  }

  function addSelectedAnswer(selectedValue) {
    selectList.push(selectedValue);
  }

  function renderSelectedAnswer() {
    questionElem.innerHTML = JSON.stringify(selectList);
  }


  init();
  

})();