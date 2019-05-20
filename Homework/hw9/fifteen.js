$(document).ready(function () {
    'use strict';
    var puzzle = (function () {
        let empty_x = 300;
        let empty_y = 300;
        /**
         * init puzzle game
         * @returns {any}
         */
        var init = function () {
            $('#puzzlearea')
                    .find('div')
                    .each(function (index) {
                        // calculate x and y for this piece
                        let x = ((index % 4) * 100);
                        let y = (Math.floor(index / 4) * 100);
                        // set basic style and background
                        $(this).addClass("puzzlepiece")
                                .css('left', x + 'px')
                                .css('top', y + 'px')
                                .css('background-image', 'url("background.jpg")')
                                .css('background-position', -x + 'px ' + (-y) + 'px')
                                .bind('mouseover', mouseOver);
                    });
        };
        var shuffle = function () {
            // reset.
            init();

            //shuffle the pieces randomly
            var pieces = $('#puzzlearea').find('div');

            // internal function helper for shuffle
            function shuffleHelper(array) {
                var i = array.length;
                if (i === 0) {
                    return false;
                }
                while (--i) {
                    var j = Math.floor(Math.random() * (i + 1)),
                            tempi = array[i],
                            tempj = array[j];
                    // swapping        
                    array[i] = tempj;
                    array[j] = tempi;
                }
            }
            shuffleHelper(pieces);

            // display
            $('#puzzlearea')
                    .find('div')
                    .each(function (i) {
                        // get piece at position i
                        let square = pieces.eq(i);
                        let tempX = square.css('left');
                        let tempY = square.css('top');
                        // update square
                        $(this)
                                .animate({
                                    top: tempY,
                                    left: tempX
                                }, 400);
                    });

            // reset empty place.
            empty_x = 300;
            empty_y = 300;
        };

        var canMove = function (elm) {
            let elm_x = parseInt(elm.css('left'));
            let elm_y = parseInt(elm.css('top'));
            let new_x = parseInt(empty_x);
            let new_y = parseInt(empty_y);

            // clicked square is north of empty space
            if (elm_x === new_x && elm_y === new_y - 100)
                return true;

            // clicked square is south of empty space
            if (elm_x === new_x && elm_y === new_y + 100)
                return true;

            // clicked square is west of empty space
            if (elm_x - 100 === new_x && elm_y === new_y)
                return true;

            // clicked square is east of empty space
            if (elm_x + 100 === new_x && elm_y === new_y)
                return true;

            // other case, can't move
            return false;

        };
        var mouseOver = function () {
            if (canMove($(this))) {
                // unbind event first then attach new event.
                $(this).addClass('movablepiece')
                        .bind('click', move);
            } else {
                $(this).unbind('click')
                        .removeClass('movablepiece');
            }
        };
        var move = function () {
            if (canMove($(this))) {
                let newX = empty_x;
                let newY = empty_y;
                let oldX = $(this).css('left');
                let oldY = $(this).css('top');

                // swap this element with empty space            
                $(this).animate({left: newX, top: newY}, 400);
                empty_x = oldX;
                empty_y = oldY;
            }
        };
        /**
         * main function
         * @returns {any}
         */
        return {
            init: init,
            shuffle: shuffle
        };
    })();

    /**
     * init puzzle game. 
     */
    puzzle.init();

    /**
     * handle shuffle puzzle
     */
    $('#shufflebutton').click(puzzle.shuffle);
});