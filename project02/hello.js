const readline = require('readline');

class QueueManager {
    constructor() {
        this.printQueue = [];
        this.printerHistory = [];
    }

    addJob(fileName) {
        this.printQueue.push(fileName);
        console.log(`Added to Queue: ${fileName}`);
    }

    printJob() {
        if (this.printQueue.length === 0) {
            console.log('No document in queue');
            return;
        }

        const document = this.printQueue.shift();
        console.log(`Printing: ${document}`);
        this.printerHistory.push(document);
    }

    reprintLastDocument() {
        if (this.printerHistory.length === 0) {
            console.log('No document available');
            return;
        }

        const lastPrinted = this.printerHistory[this.printerHistory.length - 1];
        console.log(`Reprinting: ${lastPrinted}`);
    }

    showQueue() {
        console.log('\n================ Print Queue =================');

        if (this.printQueue.length === 0) {
            console.log('Queue is empty');
        } else {
            this.printQueue.forEach((doc, index) => {
                if (index === 0) console.log(`-> ${doc} (Next to Print)`);
                else console.log(` ${doc}`);
            });
        }

        console.log('=============================================\n');
    }

    showHistory() {
        console.log('\n================ Print History =================');

        if (this.printerHistory.length === 0) {
            console.log('No printed documents');
        } else {
            for (let i = this.printerHistory.length - 1; i >= 0; i--) {
                if (i === this.printerHistory.length - 1) {
                    console.log('top -> ' + this.printerHistory[i]);
                } else {
                    console.log(' ' + this.printerHistory[i]);
                }
            }
        }

        console.log('=============================================\n');
    }
}

const queueManager = new QueueManager();

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

console.log('==================================');
console.log('Welcome to the Printer Manager!');
console.log('==================================');
console.log('\nAvailable Commands:');
console.log('print <filename> - Add a document to the print queue');
console.log('print - Print the next document in the queue');
console.log('reprint - Reprint the last printed document');
console.log('queue - Show the current print queue');
console.log('history - Show the print history');
console.log('exit - Close the printer manager');


function start() {
    rl.question('Enter a command: ', (input) => {
        input = input.trim();
        const command = input.split(' ')[0].toLowerCase();
        const args = input.substring(command.length).trim();

        if (command === 'exit') {
            console.log('Closing the printer manager');
            rl.close();
            return;
        }
        else if (command === 'print' && args.length > 0) {
            queueManager.addJob(args);
        }
        else if (command === 'print') {
            queueManager.printJob();
        }
        else if (command === 'reprint') {
            queueManager.reprintLastDocument();
        }
        else if (command === 'queue') {
            queueManager.showQueue();
        }
        else if (command === 'history') {
            queueManager.showHistory();
        }
        else {
            console.log('Invalid command. Please try again.');
        }

        start();
    });
}

start();