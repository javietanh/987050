/* global ANIMATIONS */

(function () {
    "use strict";
    let animationIndex = 0;
    let animationLength = 0;
    let rawAnimation = "";
    let backupContent = "";
    let tm = null;
    const NORMAL_SPEED = 250;
    const TURBO_SPEED = 50;
    let interval = NORMAL_SPEED;

    /**
     * main
     * @returns {void}
     */
    window.onload = function () {
        initial();
    };

    /**
     * initial
     * @returns {void}
     */
    function initial() {
        // setting button start
        document.getElementById('btStart').disabled = false;
        document.getElementById('btStart').onclick = startAnimation;
        // setting button stop
        document.getElementById('btStop').disabled = true;
        document.getElementById('btStop').onclick = stopAnimation;
        // register event handler on change font size
        document.getElementById('cbFontSize').onchange = changeFontSize;
        // register event handler on change animation
        document.getElementById('cbAnimation').onchange = changeAnimation;
        // register event handler turbo speed.
        document.getElementById('cbTurbo').onclick = changeSpeed;
    }

    /**
     * change font size
     * @returns {void}
     */
    function changeFontSize() {
        let fontSize = document.getElementById('cbFontSize').value;
        document.getElementById('tbContent').style.fontSize = fontSize + 'pt';
    }

    /**
     * change animation style
     * @returns {void}
     */
    function changeAnimation() {
        let selectedAnimation = ANIMATIONS[document.getElementById('cbAnimation').value];
        document.getElementById('tbContent').value = selectedAnimation;
    }

    /**
     * change animation speed
     * @returns {void}
     */
    function changeSpeed() {
        if (document.getElementById('cbTurbo').checked) {
            interval = TURBO_SPEED;
        } else {
            interval = NORMAL_SPEED;
        }
        // update the timer
        if (tm !== null) {
            clearInterval(tm);
            tm = setInterval(animation, interval);
        }
    }

    /**
     * start animation
     * @returns {void}
     */
    function startAnimation() {
        document.getElementById('btStart').disabled = true;
        document.getElementById('cbAnimation').disabled = true;
        document.getElementById('btStop').disabled = false;
        if (tm === null) {
            // back current content on display box.
            backupContent = document.getElementById('tbContent').value;
            // get the raw animation
            rawAnimation = ANIMATIONS[document.getElementById('cbAnimation').value];
            // setup timer to display animation
            tm = setInterval(animation, interval);
        }
    }

    /**
     * animation function
     * @returns {void}
     */
    function animation() {
        let arr = rawAnimation.split('=====\n');
        if (animationIndex === 0 && animationLength === 0) {
            animationIndex = 0;
            animationLength = arr.length;
            document.getElementById('tbContent').value = arr[0];
            animationIndex++;
        } else {
            document.getElementById('tbContent').value = arr[animationIndex];
            animationIndex++;
            if (animationIndex > animationLength - 1) {
                animationIndex = 0;
            }
        }
    }

    /**
     * stop animation function
     * @returns {void}
     */
    function stopAnimation() {
        document.getElementById('btStart').disabled = false;
        document.getElementById('cbAnimation').disabled = false;
        document.getElementById('btStop').disabled = true;
        if (tm !== null) {
            clearInterval(tm);
            tm = null;
        }
        document.getElementById('tbContent').value = backupContent;
        backupContent = '';
        rawAnimation = '';
        animationIndex = 0;
        animationLength = 0;
    }
}());