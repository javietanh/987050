(function () {
    'use strict';
    let banking = (function () {
        let account = {
            name: null,
            balance: null
        };
        let accountInfoLis = [];
        /**
         * add new bank account
         * @param {Object} acc {name, balance}
         * @returns {void}
         */
        function add(acc) {
            accountInfoLis.push(acc);
        }
        /**
         * format number with comma
         * @param {Number} x balance
         * @returns {Number} x balance with comma
         */
        function numberWithCommas(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }

        /**
         * display the bank accounts
         * @returns {void}
         */
        function display() {
            let html = '';
            accountInfoLis.forEach(function (acc) {
                html += `Account Name: ${acc.name}   Balance: ${numberWithCommas(acc.balance)} \n`;
            });
            document.getElementById('tbDisplay').value = html;
        }

        return {
            createNewAccount: function () {
                let name = document.getElementById('tbName').value;
                let balance = document.getElementById('tbDeposit').value;
                let newAcc = Object.create(account);
                newAcc.name = name;
                newAcc.balance = balance;
                // insert new account.
                add(newAcc);
                // reset dislay.
                document.getElementById('tbName').value = '';
                document.getElementById('tbDeposit').value = '';
                // display
                display();
            }
        };
    })();

    window.onload = function () {
        document.getElementById('btSubmit').onclick = banking.createNewAccount;
    };
})();