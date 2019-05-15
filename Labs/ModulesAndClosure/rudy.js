"use strict";
var rudyTimer = (function () {
    let timer = null;
    function rudy() {
        document.getElementById('output').innerHTML += "Rudy!";
    }
    return {
        start: function () {
            if (timer === null) {
                timer = setInterval(rudy, 1000);
            } else {
                clearInterval(timer);
                timer = null;
            }
        }
    };
})();