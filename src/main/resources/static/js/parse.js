( function() {
    let url = window.location.href;
    let send_url = url + "send";
    let submit_button = document.getElementsByClassName('button submit');
    let send_button = document.getElementsByClassName('button send');
    submit_button[0].addEventListener("click", onReadyClick);
    send_button[0].addEventListener("click", onSendClick);
    console.log()
    if(window.sessionStorage.author != undefined) {
        showMessageWindow();
    } else {
        showLoginWindow();
    }

    function onReadyClick() {
        let input = document.getElementsByClassName('login_field');
        let login = input[0].value;
        if(login != '') {
            window.sessionStorage.author = login;
            hideLoginWindow()
        }
    }

    function showMessageWindow() {
        showAllMessages(url);
        let messenger_window = document.getElementsByClassName('messenger_window');
        messenger_window[0].classList.toggle('invisible');
        setInterval(()=> updateMessages(url), 1000)
    }

    function showLoginWindow() {
        let login_window = document.getElementsByClassName('login_window');
        let up = document.getElementsByClassName('up');
        up[0].classList.toggle('invisible');
        login_window[0].classList.toggle('invisible');
    }

    async function onSendClick() {
        let input = document.getElementsByClassName('message_field');
        let message = {
            id: '0',
            author: window.sessionStorage.author,
            message: input[0].value
        };
        await fetch(send_url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(message)
        });
        input[0].value = "";
    }

    async function showAllMessages(url) {
        let response = await fetch(url + "messages");
        let json = await response.json();
        let hist = document.getElementsByClassName('message_history');
        for (let note of json) {
            let new_div = document.createElement('div');
            new_div.textContent = note.author + " : " + note.message;
            new_div.classList.add("message_bubble");
            new_div.dataset.id = note.id;
            hist[0].append(new_div);
        }
    }

    async function updateMessages(url) {
        let response = await fetch(url + "messages");
        let json = await response.json();
        let bubbles_id = Array.from(document.getElementsByClassName('message_bubble')).reduce((acc, val) => {acc.push(val.dataset.id); return acc;}, []);
        let hist = document.getElementsByClassName('message_history');
        let skip = false;
        for (let note of json) {
            if(skip || bubbles_id.indexOf(note.id.toString()) === -1) {
                skip = true;
                let new_div = document.createElement('div');
                new_div.textContent = note.author + " : " + note.message;
                new_div.classList.add("message_bubble");
                new_div.dataset.id = note.id;
                hist[0].append(new_div);
            }
        }
    }
} ());