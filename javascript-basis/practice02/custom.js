(function() {
  const calcElem = document.querySelector('.calc-wrap');
  const resultElem = document.getElementById('result');
  
  let numOne = '';
  let numTwo = ''; 
  let operator = ''; 

  function init() {
    bindEvent();
  }

  function bindEvent() {
    calcElem.addEventListener('click', handleClick);
  }

  function handleClick(e) {
    const target = e.target;

    if (target.className === 'btn') {
      if (!operator) { 
        numOne += target.value;
        resultElem.textContent = numOne;

      } else {
        numTwo += target.value;
        resultElem.textContent = numTwo;
      }
    }

    if (target.className === 'operator') {
      if (numOne) {
        operator = target.value;
      }

    } else if (target.className === 'btn-2') {
      if (numTwo) {
        switch (operator) {
          case '/':
            resultElem.textContent = numOne / numTwo;
            break;

          case '*':
            resultElem.textContent = numOne * numTwo;
            break;

          case '+':
            resultElem.textContent = parseInt(numOne) + parseInt(numTwo);
            break;

          case '-':
            resultElem.textContent = numOne - numTwo;
            break;
        }
      }

    } else if (target.className === 'reset') {
      numOne = '';
      numTwo = '';
      operator = '';
      resultElem.textContent = 0;
    }
  }

  init();

})();