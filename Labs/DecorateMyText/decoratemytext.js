/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function load(){
    alert("Hello, world!");
}

var tm = null;
function larger(){    
    if(tm === null){
        tm = setInterval(function(){            
            var size = parseInt(document.getElementById('myText').style.fontSize);
            size = isNaN(size) ? 12 : size + 2;
            document.getElementById('myText').style.fontSize = size + "pt";
        }, 500);
    }else{
        clearInterval(tm);
        tm = null;
    }
}

function onChecked(){
    var cb = document.getElementById('bling');    
    var mytext = document.getElementById('myText');
    if(cb.checked){
        mytext.style.fontWeight = 'bold';
        mytext.style.color = 'green';
        mytext.style.textDecoration = 'underline';
        document.body.style.background = "url('http://www.cs.washington.edu/education/courses/190m/CurrentQtr/labs/6/hundred-dollar-bill.jpg') top left no-repeat";
    }else{
        mytext.style.fontWeight = 'normal';        
        mytext.style.color = 'initial';
        mytext.style.textDecoration = 'initial';
        document.body.style.backgroundImage = 'none';
    }
}

function igpayAtinlay(){
    var txt = document.getElementById('myText').value;
    var words = txt.split(' ');
    let final = "";    
    let d = /[aieuo]/;
    for(let i = 0; i < words.length; i++) {
        let temp = "";
        let word = words[i].toLowerCase();
        if(word.length <= 0) continue;
        if(word[0].match(d)) {
            // vowel, have -ay tacked on the end.
            temp = word + "ay";
        } else {
            // consonant
            if(word.match(d)){
                let tempIndex = (word.indexOf(word.match(d)[0]));
                temp = word.substr(tempIndex) + word.substr(0, tempIndex) + "ay";
            }else{
                temp = word + "ay";
            }
        }
        final += " " + temp;
    }
    document.getElementById('myText').value = final;
}

function malkovitch(){
    var txt = document.getElementById('myText').value;        
    var words = txt.split(' ');
    let final = "";
    for(let i = 0; i < words.length; i++){
        let temp = words[i];
        if(words[i].split('').length >= 5){
            temp = "Malkovich";
        }
        final += " " + temp;    
    }
    document.getElementById('myText').value = final;
}
