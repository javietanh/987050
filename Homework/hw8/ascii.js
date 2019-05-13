/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"strict"; 
var animationIndex = 0;
var animationLength = 0;
var rawAnimation = "";
var backupContent = "";
var tm = null;
var interval = 250;

window.onload = function(){
   initial(); 
};

function initial(){
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

function changeFontSize(){
    let fontSize = document.getElementById('cbFontSize').value;
    document.getElementById('tbContent').style.fontSize = fontSize;
}

function changeAnimation(){
    document.getElementById('tbContent').value = ANIMATIONS[document.getElementById('cbAnimation').value];
}

function changeSpeed(){
    interval = document.getElementById('cbTurbo').checked ? 50 : 250;
    // update the timer
    if(tm !== null){
        clearInterval(tm);
        tm = setInterval(animation, interval);
    }
}

function startAnimation(){
    document.getElementById('btStart').disabled = true;
    document.getElementById('cbAnimation').disabled = true;
    document.getElementById('btStop').disabled = false;
    if(tm === null){
        // back current content on display box.
        backupContent = document.getElementById('tbContent').value;
        // get the raw animation
        rawAnimation = ANIMATIONS[document.getElementById('cbAnimation').value];
        // setup timer to display animation
        tm = setInterval(animation, interval);
    }
}

function animation(){    
    let arr = rawAnimation.split('=====\n');    
    if(animationIndex === 0 && animationLength === 0){        
        animationIndex = 0;
        animationLength = arr.length;        
        document.getElementById('tbContent').value = arr[0];        
        animationIndex++;
    }else{
        document.getElementById('tbContent').value = arr[animationIndex];
        animationIndex++;
        if(animationIndex > animationLength - 1) 
            animationIndex = 0;        
    }
}

function stopAnimation(){
    document.getElementById('btStart').disabled = false;
    document.getElementById('cbAnimation').disabled = false;
    document.getElementById('btStop').disabled = true;
    if(tm !== null){
        clearInterval(tm);
        tm = null;
    }
    document.getElementById('tbContent').value = backupContent;
    backupContent = '';
    rawAnimation = '';
    animationIndex = 0;
    animationLength = 0;
}