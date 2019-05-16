$(function () {
    'use strict';
    var begin = false;
    $('.boundary').mouseover(function () {
        if (begin) {
            $('.boundary').addClass('youlose');
            $('#status').html('You lose!');
            begin = false;
        }
    });

    // prevent cheating
    $('#maze').mouseleave(function () {
        if (begin) {
            $('#status').html('Cheater!');
            $('.boundary').addClass('youlose');
            begin = false;
        }
    });

    // start the game.
    $('#start').click(function () {
        begin = true;
        $('.boundary').removeClass('youlose');
        $('#status').html('Click the "S" to begin.');
    });

    // won the game
    $('#end').mouseenter(function () {
        if (begin) {
            $('#status').html('You win! :]');
            begin = false;
        }
    });
});